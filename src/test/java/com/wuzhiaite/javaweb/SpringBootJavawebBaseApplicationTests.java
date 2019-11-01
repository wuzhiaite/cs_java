package com.wuzhiaite.javaweb;

import com.wuzhiaite.javaweb.base.dao.BaseMapper;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
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

    @Test
//    @Transactional
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
            boolean flag = ListUtil.isNotNull(logList);
            //如果为false，则进行记录表初始化
            if(!flag){
                String initFile = path + File.separator + baseProperties.getInitscriptname();
                log.info("初始化表格文件地址："+initFile);
                executeScript(new File(initFile));
            }
            for(File file : files){
                if(file.getName().indexOf("sql") != -1){
                    log.info("遍历的文件名称为：" + file.getName());
                    //init.sql跳过
                    if(file.getName().equals(baseProperties.getInitscriptname())){continue;}
                    if(!flag || flag && !logList.contains(file.getName()) ){
                        try {
                            executeScript(file);
                            int count = persistentLog(file.getName());
                            System.out.println(count);
                            if(count > 0){
                                log.info("sql脚本："+file.getName()+"，已经记录在表格中");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            destory();
        }
    }


    private void executeScript (File  file) throws FileNotFoundException {
        Reader read = new InputStreamReader(new FileInputStream(file));
        runner.runScript(read);
    }
    private int persistentLog(String name) {
        String sql = new SQL() {{
            INSERT_INTO("INIT_LOGGER");
            INTO_COLUMNS("ID,INIT_SCRIPT_NAME,CREATE_TIME");
            INTO_VALUES("'"+UUID.randomUUID()+"'","'"+name+"'", "'"+DateUtil.getCurrentDate()+"'");
        }}.toString();
        log.info("日志记录表中插入数据:"+sql);
       return baseMapper.insert(sql);
    }

    private void destory() {
        try{
//            runner.closeConnection();
        }catch (Exception e) {
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
        log.info("查看是否有初始化日志记录表SQL："+sql);
        Long count = (Long) baseMapper.get(sql).get(0).get("COUNT");
        log.info("数据库中初始化表数量："+count);
        if(count == 0 ){ return null;}
        //查找已经初始化的数据
         sql = new SQL(){{
            SELECT("INIT_SCRIPT_NAME");
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
