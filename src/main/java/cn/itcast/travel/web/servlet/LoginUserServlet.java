package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();

        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserServiceImpl userService = new UserServiceImpl();
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
