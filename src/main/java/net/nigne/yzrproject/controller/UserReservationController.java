package net.nigne.yzrproject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.service.ReservationService;

@Controller
public class UserReservationController {
	
	@Autowired
	ReservationService reservation_service;
	
	@RequestMapping(value = "/user/reservation", method = RequestMethod.GET)
	public ModelAndView userReservationPage(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		
		Map<String,Object> reservation = reservation_service.getReservation(member_id);
		long reservationTotal = reservation_service.getReservationTotal(member_id);
		
		ModelAndView view=new ModelAndView();
		
		view.addObject("reservation", reservation);
		view.addObject("reservationTotal", reservationTotal);
		
		view.setViewName("user/reservation");
		return view;
	}
}