package net.nigne.yzrproject.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.AdminMovieService;

@Controller
public class AdminMovieController {

	@Autowired
	private AdminMovieService service;
	
	@Autowired
	private String uploadPath;
	
	@RequestMapping(value = "/admin/movie", method = RequestMethod.GET)
	public ModelAndView adminMoviePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/movie");
		return view;
	}
	@RequestMapping(value = "/admin/movie/{movie_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMovie(@PathVariable("movie_id") String movie_id) throws Exception{

		ResponseEntity<String> entity = null;
		
		try{
			service.deleteMovie(movie_id);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/admin/movie/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> movieList(@PathVariable("page") Integer page, @RequestParam("keyword") String keyword,
															@RequestParam("search") String search) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<MovieVO> list=null;
			if("".equals(search)){
				
				list = service.getMovieList(criteria);
				
				Long movieTotal = service.getMovieCount();
				PageMaker pm = new PageMaker(criteria, movieTotal);

				map.put("movie_list", list);
				map.put("paging", pm);
			}else{
				
				list = service.getSearchMovieList(criteria, keyword, search);
				
				Long movieSearchTotal =service.getMovieSearchCount(keyword, search);
				PageMaker pm = new PageMaker(criteria, movieSearchTotal);
				
				map.put("movie_list", list);
				map.put("paging", pm);
			}
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/admin/movie/new", method = RequestMethod.GET)
	public ModelAndView adminMovieWritePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/movieWrite");
		return view;
	}
	@RequestMapping(value = "/admin/movie/new", method = RequestMethod.POST)
	public ModelAndView adminMovieWrite(MultipartFile file, @RequestParam("movie_id") String movie_id,
			@RequestParam("title") String title, @RequestParam("open_date") String open_date, @RequestParam("runtime") Integer runtime,
			@RequestParam("rating") String rating, @RequestParam("site") String site, @RequestParam("story") String story,
			@RequestParam("movie_genre") String movie_genre) throws Exception {
		
		String fileName = movie_id + ".jpg";
		File target = new File(uploadPath + "poster\\", fileName);
		FileCopyUtils.copy(file.getBytes(), target);
		
		MovieVO vo=new MovieVO();
		vo.setMovie_id(movie_id);
		vo.setTitle(title);
		vo.setOpen_date(open_date);
		vo.setRuntime(runtime);
		vo.setRating(rating);
		vo.setSite(site);
		vo.setStory(story);
		vo.setPoster(fileName);
		vo.setStatus("schedule");
		
		GenreVO gvo=new GenreVO();
		gvo.setMovie_id(movie_id);
		gvo.setMovie_genre(movie_genre);
		
		service.persist(vo, gvo);
		
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/movie");
		return view;
	}
	@RequestMapping(value = "/admin/movie/{movie_id}/{status}", method = RequestMethod.PUT)
	public ResponseEntity<String> statusMovie(@PathVariable("movie_id") String movie_id, @PathVariable("status") String status) throws Exception{

		ResponseEntity<String> entity = null;
		
		try{
			service.statusMovie(movie_id, status);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}