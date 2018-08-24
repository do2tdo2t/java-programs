package com.checkin.webapp.master;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.master.command.LoginOkMasterCommand;
import com.checkin.webapp.master.command.ShowMasterChart;
import com.checkin.webapp.master.model.MasterVO;

@Controller
public class MasterController {
	/*
	//로그인 성공/실패
	public ModelAndView loginOkMaster(HttpServletRequest request, MasterVO vo) {
		
	}
	
	//로그인폼으로 이동
	public ModelAndView loginFormMaster(HttpServletRequest request) {
		
	}
	
	//로그아웃
	public ModelAndView logoutMaster(HttpServletRequest request) {
		
	}
	
	//회원가입폼으로 이동
	public ModelAndView registerFormMaster(HttpServletRequest request) {
		
	}
	
	//회원가입 성공/실패
	public ModelAndView registerOkMaster(HttpServletRequest request) {
		
	}
	
	//수정하기폼으로 이동
	public ModelAndView editFormMaster(HttpServletRequest request) {
		
	}
	
	//수정 성공/실패
	public ModelAndView editOkMaster(HttpServletRequest request) {
		
	}
	
	//마이페이지로 이동
	public ModelAndView showMaster(HttpServletRequest request, MasterVO vo) {
		
	}
	
	//웹마스터가 보는 멤버리스트로 이동
	public ModelAndView showAllMaster(HttpServletRequest request, MasterVO vo) {
		
	}
	
	*/
	@RequestMapping(value="/master/showChart", method = RequestMethod.GET)
	public ModelAndView masterMain(HttpServletRequest request) {
		return new ShowMasterChart().execute(request);
	}
	
	//로그인 성공/실패
	// 매핑 경로 : /main/tryLoginManager
	@RequestMapping(value="/main/tryLoginManager", method=RequestMethod.POST)
	@ResponseBody
	public MasterVO loginOkMaster(HttpServletRequest request, MasterVO vo) {
		System.out.println(3+vo.toString());
		LoginOkMasterCommand command = new LoginOkMasterCommand();
		return command.executeVo(request, vo);
	}
	
	@RequestMapping("/master/logout")
	public String logoutMaster(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "main/login/login";
	}
	
}
