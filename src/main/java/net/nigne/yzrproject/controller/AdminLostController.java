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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.AdminLostService;

@Controller
public class AdminLostController {

	@Autowired
	private AdminLostService service;
	
	@RequestMapping(value = "/admin/lost", method = RequestMethod.GET)
	public ModelAndView adminLostPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/lost");
		return view;
	}
	@RequestMapping(value = "/admin/lost/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLost(@PathVariable("no") Integer no) throws Exception{

		ResponseEntity<String> entity = null;
		
		try{
			service.deleteLost(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/admin/lost/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> lostList(@PathVariable("page") Integer page, @RequestParam("keyword") String keyword,
															@RequestParam("search") String search) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<LostVO> list=null;
			if("".equals(search)){
				
				list = service.getLostList(criteria);
				
				Long lostTotal = service.getLostCount();
				PageMaker pm = new PageMaker(criteria, lostTotal);

				map.put("lost_list", list);
				map.put("paging", pm);
			}else{
				
				list = service.getSearchLostList(criteria, keyword, search);
				
				Long lostSearchTotal =service.getLostSearchCount(keyword, search);
				PageMaker pm = new PageMaker(criteria, lostSearchTotal);
				
				map.put("lost_list", list);
				map.put("paging", pm);
			}
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/admin/lost/new", method = RequestMethod.GET)
	public ModelAndView adminLostWritePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/lostWrite");
		return view;
	}
	@RequestMapping(value = "/admin/lost/{no}", method = RequestMethod.POST)
	public ResponseEntity<String> rentComplete(@PathVariable("no") Integer no) throws Exception{
		ResponseEntity<String> entity = null;
		try{
			service.lostComplete(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}