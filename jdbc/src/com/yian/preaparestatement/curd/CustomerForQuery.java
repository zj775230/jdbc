package com.yian.preaparestatement.curd;
import com.yian.bean.Customer;
import com.yian.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对customers表进行查询
 * @author zyt
 * @create 2022-02-17 16:53
 */
public class CustomerForQuery {
    @Test
    public void testQueryForCustomer() {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = queryForCustomer(sql,10);
        System.out.println(customer);
    }
    /**
     * 针对customers表的通用操作
     * @param sql
     * @throws Exception
     */
    public Customer queryForCustomer(String sql,Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JDBCUtil.getConnection();
            //2.预编译sql，返回ps实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length; i++){
                ps.setObject(i + 1,args[i]);
            }
            //4.执行,返回结果集
            rs = ps.executeQuery();
            //5.处理数据
            // 获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData来获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //通过获取到的列数来循环得到数据
            if(rs.next()){
                Customer cust = new Customer();
                //处理结果集一行数据中的每一列
                for(int i = 0;i < columnCount ; i++){
                    //得到值
                    Object columValue = rs.getObject(i + 1);
                    //获取每个列的列名
                    //String columName = rsmd.getCatalogName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //给cust指定columName属性，赋值为columValue,通过反射

                    Field field = Customer.class.getDeclaredField(columnLabel);//调类名的属性拿到field
                    field.setAccessible(true);//保证能访问true
                    field.set(cust,columValue);//把field属性的值赋值为cust，columValue

                }
                return cust;//返回的不止对象，还有通过反射获取的属性
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return null; //返回null，防止查询完没有结果
    }



    @Test
    public void testQuery1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            //执行，返回结果集
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);
                //将数据封装成一个对象Customer
                Customer cust = new Customer(id,name,email,birth);
                System.out.println(cust);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
    }
}
