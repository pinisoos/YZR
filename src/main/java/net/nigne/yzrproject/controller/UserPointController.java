package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPointController {
	
	@RequestMapping(value = "/user/point", method = RequestMethod.GET)
	public ModelAndView userPointPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/point");
		return view;
	}
}