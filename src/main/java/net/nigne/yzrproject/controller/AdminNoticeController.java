package net.nigne.yzrproject.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import net.nigne.yzrproject.service.AdminNoticeService;

@Controller
public class AdminNoticeController {

	@Autowired
	private AdminNoticeService service;
	
	@RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
	public ModelAndView adminNoticePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/notice");
		return view;
	}
	@RequestMapping(value = "/admin/notice/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotice(@PathVariable("no") Integer no) throws Exception{

		ResponseEntity<String> entity = null;
		
		try{
			service.deleteNotice(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/admin/notice/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> noticeList(@PathVariable("page") Integer page, @RequestParam("keyword") String keyword,
															@RequestParam("search") String search) {
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
	@RequestMapping(value = "/admin/notice/new", method = RequestMethod.GET)
	public ModelAndView adminNoticeWritePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/noticeWrite");
		return view;
	}
	@RequestMapping(value = "/admin/notice/new", method = RequestMethod.POST)
	public ModelAndView adminNoticeWrite(NoticeVO vo) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String notice_date=sdf.format(cal.getTime().getTime());

		vo.setNotice_date(notice_date);
		service.persist(vo);
		
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/notice");
		return view;
	}
	@RequestMapping(value = "/admin/notice/read/{no}", method = RequestMethod.GET)
	public ModelAndView adminNoticeReadPage(@PathVariable("no") Integer no) throws Exception {
		
		ModelAndView view=new ModelAndView();
		view.addObject("vo", service.getNoticeDetail(no));
		view.setViewName("admin/noticeRead");
		return view;
	}
	@RequestMapping(value = "/admin/notice/new", method = RequestMethod.PUT)
	public ModelAndView adminNoticeUpdate(NoticeVO vo) throws Exception {

		service.updateNotice(vo);
		
		return new ModelAndView("redirect:/admin/notice");
	}
}