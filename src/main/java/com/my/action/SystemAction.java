package com.my.action;


import com.my.service.SystemService;
import com.my.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/system")
public class SystemAction {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/register/{userName}/{pwd}", method = RequestMethod.GET)
    public String register(@PathVariable String userName, @PathVariable String pwd, ModelMap model) {
        UserVO newUser = new UserVO();
        newUser.setUserName(userName);
        newUser.setPwd(pwd);
        String key = systemService.createNewUser(newUser);
        if (key == null) {
            model.put("message", "Account is exist");
            return "message";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(UserVO user, ModelMap model, HttpServletRequest request) {
        UserVO dbUser = systemService.searchUserByAccount(user.getUserName());
        if (dbUser == null || !dbUser.getPwd().equals(user.getPwd())) {
            model.put("message", "Account is not exist or password is wrong");
            return "message";
        } else {
            request.getSession().setAttribute("user",dbUser);
            return "forward:/product/detail/all ";
        }
    }


}
