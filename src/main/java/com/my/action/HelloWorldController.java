package com.my.action;


import com.my.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/welcome")
public class HelloWorldController {


	@RequestMapping(value="/hello")
	public String printWelcome(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	@RequestMapping(value="/sayhello",method = RequestMethod.GET)
	public String sayhello(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		return "hello";
	}
	@RequestMapping(value="/no/{no}/age/{age}",method = RequestMethod.GET)
	public
	@ResponseBody
	String findMessage(@PathVariable Long no,@PathVariable Long age,HttpServletRequest request,HttpServletResponse response){

		System.out.println("no:" + no + " age:" + age);
		request.getSession().setAttribute("no", no);
		request.getSession().setAttribute("age", age);



		return "success";
	}

	@RequestMapping(value="/getMessage",method = RequestMethod.GET)
	public
	@ResponseBody
	String getMessage(HttpServletRequest request,HttpServletResponse response){
		String no=request.getSession().getAttribute("no").toString();
		String age=request.getSession().getAttribute("age").toString();
		return "no:"+no+" age:"+age;
	}

}