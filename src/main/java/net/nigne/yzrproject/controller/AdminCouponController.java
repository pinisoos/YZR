package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCouponController {
	
	@RequestMapping(value = "/admin/usercoupon", method = RequestMethod.GET)
	public ModelAndView AdminUsercouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/usercoupon");
		return view;
	}
	@RequestMapping(value = "/admin/clubcoupon", method = RequestMethod.GET)
	public ModelAndView AdminClubcouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/clubcoupon");
		return view;
	}
}