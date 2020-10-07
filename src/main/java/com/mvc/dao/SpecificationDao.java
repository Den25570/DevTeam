package com.mvc.dao;

import com.mvc.bean.SpecificationBean;
import com.mvc.util.DBConnection;

import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpecificationDao {
    public int addSpecification(SpecificationBean specificationBean, int userId) {
        String name = specificationBean.getName();
        String description = specificationBean.getDescription();

        Connection con = null;
        Statement statement = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            
            ResultSet result = statement.executeQuery("insert into specifications " +
                    "(name, description, user_creator_id)" +
                    "values(\'"+name+"\', \'" + description + "\', " + Integer.toString(userId) + ")" +
                    "returning id");

            System.out.println("Specification insert in id:" + result);
            result.next();
            return result.getInt(1);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
