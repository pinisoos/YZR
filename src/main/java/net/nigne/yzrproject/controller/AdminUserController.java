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
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.AdminUserService;

@Controller
public class AdminUserController {

	@Autowired
	private AdminUserService service;
	
	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public ModelAndView adminUserPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/user");
		return view;
	}
	@RequestMapping(value = "/admin/user/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> memberList(@PathVariable("page") Integer page, @RequestParam("keyword") String keyword,
															@RequestParam("search") String search) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<MemberVO> list=null;
			if("".equals(search)){
				
				list = service.getMemberList(criteria);
				
				Long memberTotal = service.getMemberCount();
				PageMaker pm = new PageMaker(criteria, memberTotal);
				
				map.put("member_list", list);
				map.put("paging", pm);
			}else{
				
				list = service.getSearchMemberList(criteria, keyword, search);
				
				Long memberSearchTotal =service.getMemberSearchCount(keyword, search);
				PageMaker pm = new PageMaker(criteria, memberSearchTotal);
				
				map.put("member_list", list);
				map.put("paging", pm);
			}
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/admin/user/{member_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMember(@PathVariable("member_id") String member_id) throws Exception{
		ResponseEntity<String> entity = null;
		try{
			service.deleteMember(member_id);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}