package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoController {
	
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public ModelAndView userUpdatePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/update");
		return view;
	}
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView userDeletePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/delete");
		return view;
	}
}