package com.wuzhiaite.javaweb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class SpringBootJavawebBaseApplicationTests {

    @Test
    public void contextLoads() {

        try {
            Reader read = Resources.getResourceAsReader("sql/init.sql");
            // jdbc 连接信息: 注: 现在版本的JDBC不需要配置driver，因为不需要Class.forName手动加载驱动
//            String url = "jdbc:mysql://localhost:3306/cmbi_user?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
//            String username = "";
//            String password = "";
//            Connection conn = DriverManager.getConnection(url, username, password);
//             // 创建ScriptRunner，用于执行SQL脚本
//            ScriptRunner runner = new ScriptRunner(conn);
//            runner.setErrorLogWriter(null);
//            runner.setLogWriter(null);
//            // 执行SQL脚本
//            runner.runScript(read);
//            // 关闭连接
//            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
