package com.mvc.dao;

import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public UserBean getUser(String userKey) {
        Connection con = null;
        Statement statement = null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

            ResultSet result = statement.executeQuery(
                    "select * from users where user_key =\'" + userKey + "\'");

            UserBean userBean = new UserBean();
            if(result.next()) {
                userBean.setId(result.getInt("id"));
                userBean.setLogin(result.getString("login"));
                userBean.setPassword(result.getString("password"));
                userBean.setRole(result.getInt("role"));
                userBean.setQualification(result.getString("qualification"));
                userBean.setUserKey(result.getString("user_key"));
            }

            return userBean;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
