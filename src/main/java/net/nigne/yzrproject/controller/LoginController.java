package net.nigne.yzrproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("login");
		return view;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("member_id") String member_id, @RequestParam("member_pw") String member_pw, HttpServletRequest request) throws Exception {
		MemberVO vo=service.memberLogin(member_id, member_pw);
		HttpSession session= request.getSession();
		if(vo==null){
			session.setAttribute("error", "loginerror");
			return new ModelAndView("redirect:/login");
		}else{
			session.setAttribute("member_id", vo.getMember_id());
			session.removeAttribute("error");
			return new ModelAndView("redirect:/index");
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws Exception {
		HttpSession session= request.getSession();
		session.removeAttribute("member_id");
		return new ModelAndView("redirect:/index");
	}
}