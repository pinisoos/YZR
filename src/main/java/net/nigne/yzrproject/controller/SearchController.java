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
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Controller
public class SearchController {
	
	@Autowired
	SearchService ss;
	
	/** 
	* @Method Name : SearchIndex  
	* @Method	   : 설명... 
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
	* @Method	   : 설명... 
	* @param search
	* @param model
	* @return
	* @throws Exception 
	*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String Search(@RequestParam("search") String search, Model model,HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("search", search);
		//영화 디렉터 배우  리스트
		List<MovieVO> movieList = ss.getSearch(search);
		List<DirectorVO> directorList = ss.getSearchDirector(search);
		List<ActorVO> actorList = ss.getSearchActor(search);
		//영화 관련 배우 감독
		Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
		//배우 출연영화
		List<SearchVO> associateMovieWithActor = ss.getSearchAssociateMovieWithActor(search);
		//감독 영화
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
	* @Method	   : 설명... 
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
			
			//영화 관련 배우 감독
			Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
			
			//배우 출연영화
			List<SearchVO> associateMovieWithActor = ss.getSearchAssociateMovieWithActor(search);
			
			//감독 영화
			List<SearchVO> associateMovieWithDirector = ss.getSearchAssociateMovieWithDirector(search);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("movieList", movieList);
			map.put("directorList", directorList);
			map.put("actorList", actorList);
			map.put("associateMovie", associateMovie);
			map.put("movieActor", associateMovieWithActor);
			map.put("movieDirector", associateMovieWithDirector);
			
			//브라우저로 전송
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	
	
	/** 
	* @Method Name : movieListPage  
	* @Method	   : 설명... 
	* @param page
	* @param session
	* @return 
	*/
	// /movie/ 목록페이징
	@RequestMapping(value = "/search/movie/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> movieListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			
			//페이지처리를 위한 Criteria생성
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			criteria.setArticlePerPage(8);
			
			//검색한 영화 가져오기
			List<MovieVO> list = ss.getListPage(search,criteria);
			//영화 감독 출연자 가져오기
			Map<String,Object> associateMovie = ss.getSearchAssociateMovie(search);
			
			//검색한 영화 갯수 가져오기
			long movieTotal = ss.getTotalCount(search);
			
			//페이지 메이커에 총 글 갯수와 현재 페이지가 있는 Criteria를 넘겨서 페이징 값을 계산한다
			PageMaker pm = new PageMaker(criteria, movieTotal);
			
			//최종적으로 목록과 페이징 처리값을 담아서
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);
			
			//브라우저로 전송
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	/** 
	* @Method Name : actorListPage  
	* @Method	   : 설명... 
	* @param page
	* @param session
	* @return 
	*/
	// /actor/ 목록페이징
	@RequestMapping(value = "/search/actor/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> actorListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{

			//페이지처리를 위한 Criteria생성
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			//검색한 배우 가져오기
			List<ActorVO> list = ss.getListPageActor(search,criteria);
			//배우 연관 영화
			List<SearchVO> associateMovie = ss.getSearchAssociateMovieWithActor(search);
			
			//검색한 배우 갯수 가져오기
			long actorTotal = ss.getTotalCountActor(search);
			
			//페이지 메이커에 총 글 갯수와 현재 페이지가 있는 Criteria를 넘겨서 페이징 값을 계산한다
			PageMaker pm = new PageMaker(criteria, actorTotal);
			
			//최종적으로 목록과 페이징 처리값을 담아서
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);
			
			//브라우저로 전송
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	/** 
	* @Method Name : directorListPage  
	* @Method	   : 설명... 
	* @param page
	* @param session
	* @return 
	*/
	// /director/ 목록페이징
	@RequestMapping(value = "/search/director/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> directorListPage(@PathVariable("page") Integer page, HttpSession session) {
		
		String search = (String) session.getAttribute("search");
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			
			//페이지처리를 위한 Criteria생성
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			//감독 리스트 가져오기
			List<DirectorVO> list = ss.getListPageDirector(search,criteria);
			//감독 연관 영화
			List<SearchVO> associateMovie = ss.getSearchAssociateMovieWithDirector(search);
			
			//검색한 배우 갯수 가져오기
			long directorTotal = ss.getTotalCountDirector(search);
			
			//페이지 메이커에 총 글 갯수와 현재 페이지가 있는 Criteria를 넘겨서 페이징 값을 계산한다
			PageMaker pm = new PageMaker(criteria, directorTotal);
			
			//최종적으로 목록과 페이징 처리값을 담아서
			Map<String,Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			map.put("am", associateMovie);

			//브라우저로 전송
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}