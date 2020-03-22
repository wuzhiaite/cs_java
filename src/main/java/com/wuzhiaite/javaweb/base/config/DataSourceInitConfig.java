package com.wuzhiaite.javaweb.base.config;

import com.wuzhiaite.javaweb.base.dao.BaseMapper;
import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.base.utils.DateUtil;
import com.wuzhiaite.javaweb.base.utils.ListUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 初始化系统，执行初始化SQL脚本文件
 */
@Configuration
@Slf4j
public class DataSourceInitConfig implements InitializingBean {

    @Autowired
    private BaseProperties baseProperties;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BaseMapper baseMapper;

    private static ScriptRunner runner;

    @Transactional
    @Override
    public void afterPropertiesSet() throws Exception {
        if(true){ return ; }
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
            INTO_VALUES("'"+ UUID.randomUUID()+"'","'"+name+"'", "'"+ DateUtil.getCurrentDate()+"'");
        }}.toString();
        log.info("日志记录表中插入数据:"+sql);
        return baseMapper.insertBySQL(sql);
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
            WHERE("table_name = 'INIT_LOGGER' AND TABLE_SCHEMA='"+baseProperties.getDatabaseName()+"'");
        } }.toString();
        log.info("查看是否有初始化日志记录表SQL："+sql);
        Long count = (Long) baseMapper.getBySQL(sql).get(0).get("COUNT");
        log.info("数据库中初始化表数量："+count);
        if(count == 0 ){ return null;}
        //查找已经初始化的数据
        sql = new SQL(){{
            SELECT("INIT_SCRIPT_NAME");
            FROM("INIT_LOGGER");
        }}.toString();
        List<Map<String,Object>> logger = baseMapper.getBySQL(sql);

        return ListUtil.isNotNull(logger) ? ListUtil.getValueList(logger,"INIT_SCRIPT_NAME",String.class) : null;
    }






}
