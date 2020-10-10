package com.mvc.dao;

import com.mvc.bean.ProjectBean;
import com.mvc.bean.SpecificationBean;
import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class ProjectDao {
    public ArrayList<ProjectBean> getAllSpecificationsAndProjects(int user_id) {

        Connection con = null;
        Statement statement = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

            ResultSet result = statement.executeQuery("" +
                    "SELECT specifications.name, specifications.description, projects.cost, specifications.id AS id " +
                    "FROM specifications " +
                    "LEFT JOIN projects " +
                    "ON specifications.id=projects.specification_id " +
                    "WHERE specifications.user_creator_id="+user_id);

            ArrayList<ProjectBean> resultList = new ArrayList<ProjectBean>();
            while (result.next()) {
                ProjectBean projectBean = new ProjectBean();
                SpecificationBean specificationBean = new SpecificationBean();
                specificationBean.setName(result.getString("name"));
                specificationBean.setDescription(result.getString("description"));
                projectBean.setId(result.getInt("id"));
                projectBean.setSpecification(specificationBean);
                projectBean.setCost(result.getInt("cost"));

                resultList.add(projectBean);
            }

            return resultList;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
