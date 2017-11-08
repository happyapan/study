package com.my.action;

import com.my.service.OrderService;
import com.my.vo.ProductVO;
import com.my.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/common")
public class CommonAction {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/toLogin")
    public String printWelcome(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception {
        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "login";
    }

    @RequestMapping(value="/shoppingCart")
         public String shoppingCart(ModelMap model,HttpServletRequest request){

        Object user = request.getSession().getAttribute("user");
        if(user!=null){
            List<ProductVO> cartProducts= this.orderService.fetchShoppingCartItems(((UserVO)user).getUserName());
            model.put("cartProducts",cartProducts);
            model.put("user",user);
        }

        return "shoppingCart";
    }
//
//    @RequestMapping(value="/showMessage")
//    public String showMessage(ModelMap model,HttpServletRequest request){
//        return "message";
//    }

//    @RequestMapping(value="/userLogin")
//    public String userLogin(UserVO user,HttpServletRequest request)throws Exception {
//        request.getSession().setAttribute("user",user.getUserName());
//
//        return "login";
//    }
}
