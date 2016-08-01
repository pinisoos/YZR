package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserStoreController {
	
	@RequestMapping(value = "/user/store", method = RequestMethod.GET)
	public ModelAndView userStorePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/store");
		return view;
	}
}