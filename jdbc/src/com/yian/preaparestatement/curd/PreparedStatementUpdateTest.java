package com.yian.preaparestatement.curd;

import com.yian.connection.ConnectionTest;
import com.yian.util.JDBCUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;

/**
 * @author zyt
 * @create 2022-02-15 15:33
 */
public class PreparedStatementUpdateTest {
    @Test
    public void testCommonUpdate()  {
        String sql = "update `order` set order_name = ? where order_id = ?";
        update(sql,"XX",2);
    }
    //通用增删改
    public void update(String sql,Object ...args)  {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库链接
            connection = JDBCUtil.getConnection();
            //2.预编译sql，返回PrepareStatement的实例
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length ; i++){
                ps.setObject(i + 1,args[i]);
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭
            JDBCUtil.closeResource(connection,ps);
        }

    }
    //删除
    @Test
    public void testDel(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库链接
            connection = JDBCUtil.getConnection();
            //2.预编译sql，返回PrepareStatement的实例
            String sql = "delete from customers where id = ?";
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1,5);
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭
            JDBCUtil.closeResource(connection,ps);
        }
    }
    //向customer表中更新记录
    @Test
    public void testUpdate()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库链接
            conn = JDBCUtil.getConnection();
            //2.预编译sql语句，返回PrepareStatement的实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
//        3.填充占位符
            ps.setString(1,"王老菊");
            ps.setInt(2,1);

//        4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//        5.资源的关闭
            JDBCUtil.closeResource(conn,ps);
        }
    }

    //向customer表中插入记录
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
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
            //3.获取链接
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);

            //4.预编译sql返回PrepareStatement的实例
            String  sql = "insert into customers(name,email,birth)value(?,?,?)";
            ps = connection.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1,"哪吒");
            ps.setString(2,"nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new Date(date.getTime()));
            //6.执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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

    }
}
