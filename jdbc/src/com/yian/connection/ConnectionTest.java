package com.yian.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zyt
 * @create 2022-02-14 13:57
 */
public class ConnectionTest {
    //方式一
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/fruitdb";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        Connection connect = driver.connect(url,info);
        System.out.println(connect);
    }
    @Test
    public void testConnection2() throws Exception {
        //1.读取配置文件的四个基本信息
        InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
