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
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.SupportNoticeService;

@Controller
public class SupportNoticeController {

	@Autowired
	private SupportNoticeService service;
	
	@RequestMapping(value = "/support/notice", method = RequestMethod.GET)
	public ModelAndView supportNoticePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("support/notice");
		return view;
	}
	@RequestMapping(value = "/support/notice/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> noticeList(@PathVariable("page") Integer page, @RequestParam(name="keyword", required=false) String keyword,
															@RequestParam(name="search", required=false, defaultValue="") String search) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<NoticeVO> list=null;
			if("".equals(search)){
				
				list = service.getNoticeList(criteria);
				
				Long noticeTotal = service.getNoticeCount();
				PageMaker pm = new PageMaker(criteria, noticeTotal);

				map.put("notice_list", list);
				map.put("paging", pm);
			}else{
				
				list = service.getSearchNoticeList(criteria, keyword, search);
				
				Long noticeSearchTotal =service.getNoticeSearchCount(keyword, search);
				PageMaker pm = new PageMaker(criteria, noticeSearchTotal);
				
				map.put("notice_list", list);
				map.put("paging", pm);
			}
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/support/notice/read/{no}", method = RequestMethod.GET)
	public ModelAndView supportNoticeReadPage(@PathVariable("no") Integer no) throws Exception {
		
		ModelAndView view=new ModelAndView();
		view.addObject("vo", service.getNoticeDetail(no));
		view.setViewName("support/noticeRead");
		return view;
	}
}