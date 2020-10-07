package com.mvc.dao;

import com.mvc.bean.JobBean;
import com.mvc.bean.SpecificationBean;
import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JobDao {
    public void addJob(JobBean jobBean, int specificationId) {
        String name = jobBean.getName();
        int devNum = jobBean.getRequiredDevNumber();
        String devQual = jobBean.getRequiredQualification();

        Connection con = null;
        Statement statement = null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            statement.executeUpdate("insert into jobs " +
                    "(specification_id, required_dev_number, required_qualification, name)" +
                    "values(" + specificationId +", \'" + devNum + "\', \'"+ devQual + "\', \'"+ name + "\')");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
