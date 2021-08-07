package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activateUserServlet")
public class ActivateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = false;

        if(code!=null) {
            flag = userService.activateUser(code);


            if (flag) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("Activated successfully please <a href = 'login.html'> LOGIN HERE </a>");
            }else{
                response.getWriter().write("Activation Fail, Please Contact Admin!");
            }


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
