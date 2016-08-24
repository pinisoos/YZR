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
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.service.SupportLostService;
import net.nigne.yzrproject.service.SupportNoticeService;

@Controller
public class SupportLostController {
	
	@Autowired
	private SupportLostService service;
	
	@RequestMapping(value = "/support/lost", method = RequestMethod.GET)
	public ModelAndView supportLostPage(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		ModelAndView view=new ModelAndView();
		String member_id=(String)session.getAttribute("member_id");
		if(member_id==null){
			view.setViewName("login");
			return view;
		}else{
			view.addObject("area_list", service.getAreaList());
			view.addObject("member", service.getMember(member_id));
			view.setViewName("support/lost");
			return view;
		}
	}
	@RequestMapping(value = "/support/lost/theaterlist", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getTheaterList(@RequestParam("theater_area") String theater_area) throws Exception{

		ResponseEntity<List<String>> entity = null;
		
		try{
			entity = new ResponseEntity<List<String>>(service.getTheaterList(theater_area), HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/support/lost/new", method = RequestMethod.POST)
	public ModelAndView supportWriteLost(LostVO vo, @RequestParam("theater_area") String theater_area, @RequestParam("theater_name") String theater_name,
										@RequestParam("place") String place, @RequestParam("member_id") String member_id) throws Exception {
		
		vo.setLost_place(theater_area + " " + theater_name + " " + place);
		vo.setComplete("N");
		service.persist(vo);
		
		ModelAndView view=new ModelAndView();
		view.setViewName("redirect:/support/index");
		return view;
	}
}