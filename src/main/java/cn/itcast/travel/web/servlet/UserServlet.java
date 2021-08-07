package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();

        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User loginReturnUser = userService.loginUser(loginUser);

        ResultInfo resultInfo = new ResultInfo();

        if(loginReturnUser == null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("Username or Password WRONG!");
        }
        if(loginReturnUser !=null && loginReturnUser.getStatus().equals("N")){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("User is NOT Activated!");
        }

        if(loginReturnUser != null && loginReturnUser.getStatus().equals("Y")){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("loginUser",loginReturnUser);
        }

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("login.html");

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String genCheckCode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();

        if(!check.equalsIgnoreCase(genCheckCode)){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("The Verification inserted is wrong!");
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();

        /*String[] emails = map.get("email");
        System.out.println(emails[0]);*/

        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = userService.register(user);

        if(flag){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("The register doesnt success!");
        }

        String json = mapper.writeValueAsString(resultInfo);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void activate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean flag = false;

        if (code != null) {
            flag = userService.activateUser(code);


            if (flag) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("Activated successfully please <a href ='/travel/login.html'> LOGIN HERE </a>");
            } else {
                response.getWriter().write("Activation Fail, Please Contact Admin!");
            }
        }
    }
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user =  request.getSession().getAttribute("loginUser");

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);
    }
}
