package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;

public class LoginServlet extends HttpServlet {

    public LoginServlet()
    {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();

        loginBean.setUserName(userName);
        loginBean.setPassword(password);

        LoginDao loginDao = new LoginDao();

        try
        {
            String userValidate = loginDao.authenticateUser(loginBean);

            if(userValidate.equals("Customer"))
            {
                System.out.println("Customer's Home");

                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("Customer", userName); //setting session attribute
                request.setAttribute("userName", userName);

                request.getRequestDispatcher("/JSP/Customer.jsp").forward(request, response);
            }
            else if(userValidate.equals("Manager"))
            {
                System.out.println("Manager's Home");

                HttpSession session = request.getSession();
                session.setAttribute("Manager", userName);
                request.setAttribute("userName", userName);

                request.getRequestDispatcher("/JSP/Manager.jsp").forward(request, response);
            }
            else if(userValidate.equals("Developer"))
            {
                System.out.println("Developer's Home");

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("Developer", userName);
                request.setAttribute("userName", userName);

                request.getRequestDispatcher("/JSP/Developer.jsp").forward(request, response);
            }
            else
            {
                System.out.println("Error message = "+userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("/JSP/Login.jsp").forward(request, response);
            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
        }
    }
}