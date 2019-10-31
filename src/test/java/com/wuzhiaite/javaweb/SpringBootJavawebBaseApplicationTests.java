package com.wuzhiaite.javaweb;

import com.wuzhiaite.javaweb.base.BaseMapper;
import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.base.utils.DateUtil;
import com.wuzhiaite.javaweb.base.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
class SpringBootJavawebBaseApplicationTests {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private BaseProperties baseProperties;
    @Autowired
    private BaseMapper baseMapper;

    private ScriptRunner runner;
    private Reader read = null;

    @Test
    public void dataSourceTest() throws Exception {
        init();
        try{
            String sqlScriptPath = baseProperties.getSqlScriptPath();
            Assert.notNull(sqlScriptPath,"sql脚本配置地址不能为空");
            String path = Resources.getResourceURL(sqlScriptPath).getPath();
            Assert.notNull(path,"路径地址不能为空，请重新确认");
            File filePath = new File(path);
            File[] files = filePath.listFiles();
            Assert.notNull(files,"没找到初始化sql脚本文件");
            //获取已经执行脚本地址
            List<String> logList = this.getScriptLog();
            Arrays.asList(files).forEach(file -> {
                if(file.getName().indexOf("sql") != -1){
                    log.info("遍历的文件名称为：" + file.getName());
                    if(ListUtil.isNotNull(logList) && !logList.contains(file.getName())){
                        try {
                            read = new InputStreamReader(new FileInputStream(file));
                            runner.runScript(read);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       int count = persistentLog(file.getName());
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            destory();
        }
    }

    private int persistentLog(String name) {
        String sql = new SQL() {{
            INSERT_INTO("INIT_LOGGER");
            INTO_COLUMNS("ID,INIT_SCRIPT_NAME,CREATE_TIME");
            INTO_VALUES(UUID.randomUUID()+"",name, DateUtil.getCurrentDate());
        }}.toString();
       return baseMapper.insert(sql);
    }

    private void destory() {
        try{
            runner.closeConnection();
            read.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        // 创建ScriptRunner，用于执行SQL脚本
        try {
            runner = new ScriptRunner(dataSource.getConnection());
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> getScriptLog() {
        //查看是否生成记录表
        String sql = new SQL(){{
            SELECT(" COUNT(1) COUNT ");
            FROM("information_schema.tables");
            WHERE("table_name = 'INIT_LOGGER'");
        } }.toString();
        int count = (int) baseMapper.get(sql).get(0).get("COUNT");
        if(count == 0){
            return null;
        }
        //查找已经初始化的数据
         sql = new SQL(){{
            SELECT("INIT_SCRIPT_NAME");
            FROM("INIT_LOGGER");
            FROM("INIT_LOGGER");
        }}.toString();
        List<Map<String,Object>> logger = baseMapper.get(sql);

        return ListUtil.isNotNull(logger) ? ListUtil.getValueList(logger,"INIT_SCRIPT_NAME",String.class) : null;
    }


    @Test
    public void getFile() throws IOException {
//        String path = ClassLoader.getSystemClassLoader().getResource("").getPath();
//        System.out.println(path);
//        String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println(path1);
//        String path2 = this.getClass().getClassLoader().getResource("").getPath();
//        System.out.println(path2);
//        URL ru = Resources.getResourceURL("");
//        System.out.println("*************" + ru.getPath() + "**********************");
        URL resourceURL = Resources.getResourceURL("sql");
        System.out.println("++++++++++++++" + resourceURL.getPath() + "**********************");
        URL sru = Resources.getResourceURL("sql/init.sql");
        System.out.println(sru.getPath());

    }

    @Test
    public void contextLoads() {

        try {

            // jdbc 连接信息: 注: 现在版本的JDBC不需要配置driver，因为不需要Class.forName手动加载驱动
            String url = "jdbc:mysql://localhost:3306/cmbi_user?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
            String username = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, username, password);
             // 创建ScriptRunner，用于执行SQL脚本
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
//            Resources.
            Reader read = Resources.getResourceAsReader("sql/init.sql");
            String path = Resources.getResourceAsFile("sql/init.sql").getPath();

            System.out.println("**************" + path );
            // 执行SQL脚本
            runner.runScript(read);


            // 关闭连接
            conn.close();
            // 若成功，打印提示信息
            System.out.println("====== SUCCESS ======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
