package com.my.action;


import com.my.service.OrderService;
import com.my.service.ProductService;
import com.my.vo.OrderInfoVO;
import com.my.vo.ProductVO;
import com.my.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/addCart/{productId}",method = RequestMethod.GET)
    public String addCart(@PathVariable String productId,ModelMap model,HttpServletRequest request ){
        UserVO use = (UserVO)request.getSession().getAttribute("user");
        //check prductExist
        if(productService.isProductExist(productId)){
            this.orderService.addToShoppingCart(use.getUserName(), productId);
            //return "redirect:/common/shoppingCart.html";

            return "forward:/common/shoppingCart.html";
        }else{
            model.put("message", "Product is not exist");
            return "message";
        }
    }

    @RequestMapping(value="/delete/{productId}",method = RequestMethod.GET)
    public String delItemFromCart(@PathVariable String productId,ModelMap model,HttpServletRequest request ){
        UserVO user = (UserVO)request.getSession().getAttribute("user");
        this.orderService.delItemFromShoppingCart(user.getUserName(), productId);
        return "forward:/common/shoppingCart.html";
    }

    @RequestMapping(value="/placeOrder",method = RequestMethod.GET)
    public String placeOrder(ModelMap model,HttpServletRequest request ){
        UserVO user = (UserVO)request.getSession().getAttribute("user");
        String orderId=orderService.placeOrder(user.getUserName());
        model.put("message", "You Order Id Is " + orderId);
        return "orderSuccess";
    }

    @RequestMapping(value="/viewOrder",method = RequestMethod.GET)
    public String viewOrder(ModelMap model,HttpServletRequest request ){
        UserVO user = (UserVO)request.getSession().getAttribute("user");
        List<OrderInfoVO> orderInfos=this.orderService.findUserAllOrder(user);
        model.put("orderInfos",orderInfos);
        return "myOrders";
    }

}
