package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

public class LoginDao {
    public String authenticateUser(LoginBean loginBean)
    {
        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userNameDB = "";
        String passwordDB = "";
        int roleDB = 0;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select login, password, role from users");

            while(resultSet.next())
            {
                userNameDB = resultSet.getString("login");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getInt("role");

                System.out.println(userNameDB);
                System.out.println(passwordDB);
                System.out.println(roleDB);

                if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB == 0)
                    return "Customer";
                else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB == 1)
                    return "Manager";
                else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB == 2)
                    return "Developer";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
}
