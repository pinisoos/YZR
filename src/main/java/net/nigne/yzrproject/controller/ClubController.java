package net.nigne.yzrproject.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.ReviewVO;
import net.nigne.yzrproject.service.ClubService;



@Controller
public class ClubController {
	
	@Autowired
	private ClubService club_service;

	@RequestMapping(value = "/club", method = RequestMethod.GET)
	public String club(Locale locale, Model model,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		if(member_id ==null || member_id == ""){
			
		}else{
			MemberVO membervo = club_service.getMember(member_id);
			model.addAttribute("membervo", membervo);
		}
		return "club";
	}
	
	@RequestMapping(value = "/club/{member_id}/{club}", method = RequestMethod.PUT)
	public ResponseEntity<String> update_club( @PathVariable("member_id") String member_id,@PathVariable("club") String club) {
		ResponseEntity<String> entity = null;
		try {
			club_service.Member_club_Update(member_id, club);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}