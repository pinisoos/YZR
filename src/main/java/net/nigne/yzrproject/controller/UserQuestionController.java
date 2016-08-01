package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserQuestionController {
	
	@RequestMapping(value = "/user/qna", method = RequestMethod.GET)
	public ModelAndView userQnaPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/qna");
		return view;
	}
	@RequestMapping(value = "/user/lost", method = RequestMethod.GET)
	public ModelAndView userLostPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/lost");
		return view;
	}
	@RequestMapping(value = "/user/rent", method = RequestMethod.GET)
	public ModelAndView userRentPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/rent");
		return view;
	}
}