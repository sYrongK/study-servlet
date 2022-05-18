package main.java.controller;

import main.java.dto.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "*.user")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        try {
            if (uri.equals("/join.user")) {
                String id = req.getParameter("id");
                String password = req.getParameter("password");
                String name = req.getParameter("name");

                Member member = new Member(id, password, name);
            }
            if (uri.equals("/loginForm.user")) {
                resp.sendRedirect("/user/naverlogin.jsp");
                System.out.println("dsdsd");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
