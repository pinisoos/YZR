package net.nigne.yzrproject.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.nigne.yzrproject.domain.EmpVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.service.EmpService;
import net.nigne.yzrproject.service.TheaterService;
import net.nigne.yzrproject.service.TimetableService;

@Controller
public class TimetableController {
	@Autowired
	private TheaterService theater_service;
	@Autowired
	private TimetableService tt_service;
	
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String timetable(Locale locale, Model model) throws Exception {
		
		
		
		return "timetable";
	}
	
	@RequestMapping(value = "/timetable/theater", method = RequestMethod.GET)
	public ResponseEntity<List<TheaterVO>> theater(@RequestParam("location") String location){
		ResponseEntity<List<TheaterVO>> entity = null;
		List<TheaterVO> theater_list = null;
		
		try{
			theater_list = theater_service.getTheater(location);
			entity = new ResponseEntity<List<TheaterVO>>(theater_list, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<TheaterVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/timetable/tt", method = RequestMethod.GET)
	public ResponseEntity<List<TimetableVO>> timetable(@RequestParam("theater_id") String theater_id, @RequestParam("day") String day){
		ResponseEntity<List<TimetableVO>> entity = null;
		List<TimetableVO> tt = null;
		
		try{
			tt = tt_service.getTimetable(theater_id, day);
			entity = new ResponseEntity<List<TimetableVO>>(tt, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<TimetableVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}