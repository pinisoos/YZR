package net.nigne.yzrproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.EmpVO;
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.service.EmpService;
import net.nigne.yzrproject.service.PurchaseService;

@Controller
public class EventController {

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String store(Locale locale, Model model) throws Exception {
		
		return "event";
	}
	
	@RequestMapping(value = "/event/{category}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> event_img(@PathVariable("category") Integer category){
		ResponseEntity<List<String>> entity = null;
		
		try{
			List<String> list = new ArrayList();
			
			if(category.equals(1)){
				list.add("http://img.cgv.co.kr/Front/Main/2016/0222/box_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0707/insta_240x200.png");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0718/[CGV_1607_049]web_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/web_list_240_200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/수어사이드_CGV_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0726/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0722/240X200(1).jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0530/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1112/list_UplusMembership_mo2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_bc_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1111/240x200_do.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_nh_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0719/IrrationalMan_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0303/web_2016coupon_240_200_2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0309/web_special_listbanner_2_VVIP GIFT.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0324/web_cjonepoint_listbanner.jpg");
			}else if(category.equals(2)){
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/수어사이드_CGV_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0726/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0722/240X200(1).jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0530/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1112/list_UplusMembership_mo2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_bc_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1111/240x200_do.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_nh_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0719/IrrationalMan_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0303/web_2016coupon_240_200_2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0309/web_special_listbanner_2_VVIP GIFT.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0324/web_cjonepoint_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0222/box_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0707/insta_240x200.png");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0718/[CGV_1607_049]web_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/web_list_240_200.jpg");
			}else if(category.equals(3)){
				list.add("http://img.cgv.co.kr/Front/Main/2015/1112/list_UplusMembership_mo2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_bc_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1111/240x200_do.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_nh_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0719/IrrationalMan_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0303/web_2016coupon_240_200_2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0309/web_special_listbanner_2_VVIP GIFT.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0324/web_cjonepoint_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0222/box_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0707/insta_240x200.png");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0718/[CGV_1607_049]web_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/web_list_240_200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/수어사이드_CGV_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0726/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0722/240X200(1).jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0530/240x200_fin.jpg");
			}else if(category.equals(4)){
				list.add("http://img.cgv.co.kr/Front/Main/2016/0719/IrrationalMan_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0303/web_2016coupon_240_200_2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0309/web_special_listbanner_2_VVIP GIFT.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0324/web_cjonepoint_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0222/box_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0707/insta_240x200.png");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0718/[CGV_1607_049]web_b_240X200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/web_list_240_200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0727/수어사이드_CGV_240x200.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0726/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0722/240X200(1).jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0530/240x200_fin.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1112/list_UplusMembership_mo2.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_bc_listbanner.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2015/1111/240x200_do.jpg");
				list.add("http://img.cgv.co.kr/Front/Main/2016/0107/web_nh_listbanner.jpg");
			}
		
			entity = new ResponseEntity<List<String>>(list, HttpStatus.OK);
		
		}catch(Exception e){
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}