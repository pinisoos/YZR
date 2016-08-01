package net.nigne.yzrproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.service.AdminRentService;

@Controller
public class AdminRentController {

	@Autowired
	private AdminRentService service;
	
	@RequestMapping(value = "/admin/rent", method = RequestMethod.GET)
	public ModelAndView adminUserPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/rent");
		return view;
	}
	@RequestMapping(value = "/admin/rent/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> rentList(@PathVariable("page") Integer page) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			List<RentVO> list=null;
			list = service.getRentList(criteria);
			
			Long memberTotal = service.getRentCount();
			PageMaker pm = new PageMaker(criteria, memberTotal);
			
			map.put("rent_list", list);
			map.put("paging", pm);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/admin/rent/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRent(@PathVariable("no") Integer no) throws Exception{
		ResponseEntity<String> entity = null;
		try{
			service.deleteRent(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/admin/rent/{no}", method = RequestMethod.POST)
	public ResponseEntity<String> rentComplete(@PathVariable("no") Integer no) throws Exception{
		ResponseEntity<String> entity = null;
		System.out.println(no);
		try{
			service.rentComplete(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}