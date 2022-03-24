package com.first.utils;
import java.sql.*;
    //连接数据库
    public class DBUtil {
       private  String dbUr1="jdbc:mysql://localhost:3306/xx";
       private  String dbUserName="root";
       private  String dbPassword="123456";
       private String jdbcName="com.mysql.jdbc.Driver";
       public Connection getConn() throws Exception{
           Class.forName(jdbcName);
           Connection conn=DriverManager.getConnection(dbUr1,dbUserName,dbPassword);
           return conn;
       }
       public void closeConn(Connection conn)throws Exception{
           if(conn!=null){
               conn.close();
           }
       }


    }

