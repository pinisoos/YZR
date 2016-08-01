package net.nigne.yzrproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.service.SupportRentService;

@Controller
public class SupportRentController {
	
	@Autowired
	private SupportRentService service;
	
	@RequestMapping(value = "/support/rent", method = RequestMethod.GET)
	public ModelAndView supportRentPage(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		ModelAndView view=new ModelAndView();
		String member_id=(String)session.getAttribute("member_id");
		if(member_id==null){
			view.setViewName("login");
			return view;
		}else{
			view.addObject("member", service.getMember(member_id));
			view.setViewName("support/rent");
			return view;
		}
	}
	@RequestMapping(value = "/support/rent/typelist", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getTypeList(@RequestParam("type") String type) throws Exception{

		ResponseEntity<List<String>> entity = null;
		
		if(type.equals("special")){
			try{
				entity = new ResponseEntity<List<String>>(service.getPlextypeList(), HttpStatus.OK);
			}catch(Exception e){
				entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			}
		}else{
			try{
				entity = new ResponseEntity<List<String>>(service.getAreaList(), HttpStatus.OK);
			}catch(Exception e){
				entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			}
		}
		return entity;
	}
	@RequestMapping(value = "/support/rent/theaterlist", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getTheaterList(@RequestParam("theater_type") String theater_type, @RequestParam("type") String type) throws Exception{

		ResponseEntity<List<String>> entity = null;
		
		if(type.equals("special")){
			try{
				entity = new ResponseEntity<List<String>>(service.getSpecialTheaterList(theater_type), HttpStatus.OK);
			}catch(Exception e){
				entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			}
		}else{
			try{
				entity = new ResponseEntity<List<String>>(service.getTheaterList(theater_type), HttpStatus.OK);
			}catch(Exception e){
				entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			}
		}
		return entity;
	}
	@RequestMapping(value = "/support/rent/new", method = RequestMethod.POST)
	public ModelAndView supportWriteRent(RentVO vo, @RequestParam("theater") String theater,
										@RequestParam("theater_type") String theater_type, @RequestParam("theater_name") String theater_name) throws Exception {
		
		vo.setRent_place(theater + " " + theater_type + " " + theater_name);
		vo.setComplete("N");
		service.persist(vo);
		
		ModelAndView view=new ModelAndView();
		view.setViewName("support/index");
		return view;
	}
}