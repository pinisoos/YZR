package net.nigne.yzrproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.service.SupportLostService;
import net.nigne.yzrproject.service.SupportNoticeService;

@Controller
public class SupportController {
	
	@Autowired
	private SupportNoticeService noticeService;
	
	@RequestMapping(value = "/support/faq", method = RequestMethod.GET)
	public ModelAndView supportFaqPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("support/faq");
		return view;
	}
}