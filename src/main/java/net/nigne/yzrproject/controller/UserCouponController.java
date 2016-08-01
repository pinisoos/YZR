package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserCouponController {
	
	@RequestMapping(value = "/user/coupon", method = RequestMethod.GET)
	public ModelAndView userCouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/coupon");
		return view;
	}
}