package net.nigne.yzrproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.service.SupportQnaService;
import net.nigne.yzrproject.service.SupportRentService;

@Controller
public class SupportQnaController {
	
	@Autowired
	private SupportQnaService service;
	
	@RequestMapping(value = "/support/qna", method = RequestMethod.GET)
	public ModelAndView supportQnaPage(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		ModelAndView view=new ModelAndView();
		String member_id=(String)session.getAttribute("member_id");
		if(member_id==null){
			view.setViewName("login");
			return view;
		}else{
			view.addObject("member", service.getMember(member_id));
			view.setViewName("support/qna");
			return view;
		}
	}
	@RequestMapping(value = "/support/qna/new", method = RequestMethod.POST)
	public ModelAndView supportWriteQna(QnaVO vo, @RequestParam("qnatype") String qnatype) throws Exception {
		
		vo.setQnatype(qnatype);
		
		service.persist(vo);

		return new ModelAndView("redirect:/support");
	}
}