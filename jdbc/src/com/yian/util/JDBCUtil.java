package com.yian.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author zyt
 * @create 2022-02-17 13:24
 */
public class JDBCUtil {
    /**
     * 获取数据库链接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //1.读取配置文件的四个基本信息
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
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
        return connection;
    }
    public static void closeResource(Connection connection, Statement ps){
        //7.资源关闭
        try {
            if(ps != null);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection != null);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection connection, Statement ps, ResultSet rs){
        //7.资源关闭
        try {
            if(ps != null);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection != null);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
