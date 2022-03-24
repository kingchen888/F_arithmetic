package com.first.dao;

import com.first.model.User;
import com.first.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * µÇÂ½ÑéÖ¤
 */
public class UserDao {
    public User login(Connection conn, User user)throws Exception{
         User resultUser=null;
         String sql ="select * from user where loginName=? and loginPwd =?";
         PreparedStatement ps=conn.prepareStatement(sql);
         ps.setString(1,user.getLoginName());
         ps.setString(2,user.getLoginPwd());
         ResultSet rs =ps.executeQuery();
       if (rs.next()){
         resultUser = new User();
         resultUser.setId(rs.getInt("id"));
         resultUser.setLoginName(rs.getString("loginName"));
         resultUser.setLoginPwd(rs.getString("loginPwd"));
       }
       return resultUser;
    }
}
