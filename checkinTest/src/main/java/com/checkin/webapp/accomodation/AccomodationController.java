package com.checkin.webapp.accomodation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.accomodation.command.RecommendCommand;
import com.checkin.webapp.accomodation.model.AccomodationVO;

@Controller
public class AccomodationController {
	/*
	//숙박업소 리스트 
	// 매핑 경로 : /main/showAccoList
	public ModelAndView showList(HttpServletRequest request, AccomodationVO vo) {
		
	}
	
	// 매핑 경로 : /master/viewAcco
	public ModelAndView viewAccomodation(HttpServletRequest request, AccomodationVO vo) {
		
	}
	// 매핑 경로 : /master/editAcco
	public ModelAndView editAccomodationForm(HttpServletRequest request, AccomodationVO vo) {}
	
	// 매핑 경로 : /master/editOkAcco
	public ModelAndView editOkAccomodation(HttpServletRequest request, AccomodationVO vo) {}
	
	// 매핑 경로 : /master/viewAcco
	public ModelAndView viewAccomodation(HttpServletRequest request, AccomodationVO vo) {}
	
	// 매핑 경로 : /master/deleteAcco
	public ModelAndView deleteAccomodation(HttpServletRequest request, AccomodationVO vo) {}
	
	// 매핑 경로 : /master/insertAcco
	public ModelAndView insertAccomodationForm(HttpServletRequest request) {}
	
	//가맹점 주인
	// 매핑 경로 : /master/insertOkAcco
	public ModelAndView insertOkAccomodation(HttpServletRequest request, AccomodationVO vo) {}
	
	//숙박업소리스트(전체 관리자)
	// 매핑 경로 : /webmaster/showAccoList
	public ModelAndView showListWebmaster(HttpServletRequest request, AccomodationVO vo) {}
	*/
	
	//main index page ajax 처리
	// 매핑 경로 : /main/recommendAjax
	@RequestMapping("/main/recommend")
	@ResponseBody
	public List<AccomodationVO> recommendAjax(HttpServletRequest request, AccomodationVO vo) {
		System.out.println("AccomodationController..."+vo.toString());
		return new RecommendCommand().execute2(request,vo);
	}
	
}
