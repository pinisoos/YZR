package net.nigne.yzrproject.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.SearchVO;
import net.nigne.yzrproject.service.SearchService;

/** 
* @FileName : SearchController.java 
* @Package  : net.nigne.yzrproject.controller 
* @Date     : 2016. 7. 25. 
* @�ۼ���		: ���뼺
* @���α׷� 	: ����...
*/
@Controller
public class SearchController {
	
	@Autowired
	SearchService ss;
	
	/** 
	* @Method Name : SearchIndex  
	* @Method	   : ����... 
	* @param search
	* @param model
	* @param request
	* @return
	* @throws Exception 
	*/
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchIndex(Model model,HttpServletRequest request) throws Exception {
		
		return "search";
	}
	
	/** 
	* @Method Name : home  
	* @Method	   : ����... 
	* @param search
	* @param model
	* @return
	* @throws Exception 
	*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String Search(@RequestParam("search") String search, Model model,HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("search", search);
		//��ȭ ���� ���  ����Ʈ
		List<MovieVO> movieList = ss.getSearch(search);
		List<DirectorVO> directorList = ss.getSearchDirector(search);
		List<ActorVO> actorList = ss.getSearchActor(search);
		//��ȭ ���� ��� ����
		Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
		//��� �⿬��ȭ
		List<SearchVO> associateMovieWithActor = ss.getSearchAssociateMovieWithActor(search);
		//���� ��ȭ
		List<SearchVO> associateMovieWithDirector = ss.getSearchAssociateMovieWithDirector(search);
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("directorList", directorList);
		model.addAttribute("actorList", actorList);
		model.addAttribute("associateMovie", associateMovie);
		model.addAttribute("movieActor", associateMovieWithActor);
		model.addAttribute("movieDirector", associateMovieWithDirector);

		return "search";
	}
	
	/** 
	* @Method Name : SearchAll  
	* @Method	   : ����... 
	* @param model
	* @param session
	* @return
	* @throws Exception 
	*/
	@RequestMapping(value = "/search/result", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> SearchAll(Model model,HttpSession session) throws Exception {
		
		String search = session.getAttribute("search").toString();
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			List<MovieVO> movieList = ss.getSearch(search);
			List<DirectorVO> directorList = ss.getSearchDirector(search);
			List<ActorVO> actorList = ss.getSearchActor(search);
			
			//��ȭ ���� ��� ����
			Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
			
			//��� �⿬��ȭ
			List<SearchVO> associateMovieWithActor = ss.getSearchAssociateMovieWithActor(search);
			
			//���� ��ȭ
			List<SearchVO> associateMovieWithDirector = ss.getSearchAssociateMovieWithDirector(search);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("movieList", movieList);
			map.put("directorList", directorList);
			map.put("actorList", actorList);
			map.put("associateMovie", associateMovie);
			map.put("movieActor", associateMovieWithActor);
			map.put("movieDirector", associateMovieWithDirector);
			
			//�������� ����
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	
	
	/** 
	* @Method Name : movieListPage  
	* @Method	   : ����... 
	* @param page
	* @param session
	* @return 
	*/
	// /movie/ �������¡
	@RequestMapping(value = "/search/movie/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> movieListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			
			//������ó���� ���� Criteria����
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			criteria.setArticlePerPage(8);
			
			//�˻��� ��ȭ ��������
			List<MovieVO> list = ss.getListPage(search,criteria);
			//��ȭ ���� �⿬�� ��������
			Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
			
			//�˻��� ��ȭ ���� ��������
			long movieTotal = ss.getTotalCount(search);
			
			//������ ����Ŀ�� �� �� ������ ���� �������� �ִ� Criteria�� �Ѱܼ� ����¡ ���� ����Ѵ�
			PageMaker pm = new PageMaker(criteria, movieTotal);
			
			//���������� ��ϰ� ����¡ ó������ ��Ƽ�
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);
			
			//�������� ����
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	/** 
	* @Method Name : actorListPage  
	* @Method	   : ����... 
	* @param page
	* @param session
	* @return 
	*/
	// /actor/ �������¡
	@RequestMapping(value = "/search/actor/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> actorListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{

			//������ó���� ���� Criteria����
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			//�˻��� ��� ��������
			List<ActorVO> list = ss.getListPageActor(search,criteria);
			//��� ���� ��ȭ
			List<SearchVO> associateMovie = ss.getSearchAssociateMovieWithActor(search);
			
			//�˻��� ��� ���� ��������
			long actorTotal = ss.getTotalCountActor(search);
			
			//������ ����Ŀ�� �� �� ������ ���� �������� �ִ� Criteria�� �Ѱܼ� ����¡ ���� ����Ѵ�
			PageMaker pm = new PageMaker(criteria, actorTotal);
			
			//���������� ��ϰ� ����¡ ó������ ��Ƽ�
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);
			
			//�������� ����
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	/** 
	* @Method Name : directorListPage  
	* @Method	   : ����... 
	* @param page
	* @param session
	* @return 
	*/
	// /director/ �������¡
	@RequestMapping(value = "/search/director/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> directorListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			
			//������ó���� ���� Criteria����
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			//���� ����Ʈ ��������
			List<DirectorVO> list = ss.getListPageDirector(search,criteria);
			//���� ���� ��ȭ
			List<SearchVO> associateMovie = ss.getSearchAssociateMovieWithDirector(search);
			
			//�˻��� ��� ���� ��������
			long directorTotal = ss.getTotalCountDirector(search);
			
			//������ ����Ŀ�� �� �� ������ ���� �������� �ִ� Criteria�� �Ѱܼ� ����¡ ���� ����Ѵ�
			PageMaker pm = new PageMaker(criteria, directorTotal);
			
			//���������� ��ϰ� ����¡ ó������ ��Ƽ�
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);

			//�������� ����
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}