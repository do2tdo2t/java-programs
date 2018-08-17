package com.checkin.webapp.webmaster;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.webmaster.command.InsertNoticeOkCommand;
import com.checkin.webapp.webmaster.model.NoticeVO;

@Controller
public class WebmasterController {
	/*
	//멤버리스트 이동
	// 매핑 경로 : /webmaster/userList
	public ModelAndView showAllMember(HttpServletRequest request, MemberVO vo);
	
	//숙박업소관리자리스트 이동
	// 매핑 경로 : /webmaster/masterList
	public ModelAndView showAllMaster(HttpServletRequest request, MasterVO vo);
	
	//숙박업소리스트 이동
	// 매핑 경로 : /webmaster/showAccoList
	public ModelAndView showAllAccomodation(HttpServletRequest request, AccomodationVO vo);
	
	//공지사항게시판 이동
	// 매핑 경로 : /webmaster/notices
	public ModelAndView showAllNoticeList(HttpServletRequest request, NoticeVO vo);
	
	//공지사항 글보기 페이지로 이동
	// 매핑 경로 : /webmaster/notice
	public ModelAndView viewOneNotice(HttpServletRequest request, NoticeVO vo);
	
	//수정폼으로 이동
	// 매핑 경로 : /webmaster/modifyNotice
	public ModelAndView editNoticeForm(HttpServletRequest request, NoticeVO vo);
	
	//수정 완료/실패해을 때 이동할 페이지
	// 매핑 경로 : /webmaster/tryModifyNotice
	public ModelAndView editNoticeOk(HttpServletRequest request, NoticeVO vo);
	
	//글 삭제
	// 매핑 경로 : /webmaster/deleteNotice
	public ModelAndView deleteNotice(HttpServletRequest request, NoticeVO vo);
	
	// 매핑 경로 : /webmaster/addNotice
	public ModelAndView insertNoticeForm(HttpServletRequest request, NoticeVO vo);
	
	// 매핑 경로 : /webmaster/tryAddNotice
	public ModelAndView insertNoticeOk(HttpServletRequest request, NoticeVO vo);
	
	//웹마스터 로그인
	// 매핑 경로 : /webmaster/webmasterLogin
	public ModelAndView loginWebmaster(HttpServletRequest request, WebmasterVO vo);
	
	//웹마스터 로그아웃
	// 매핑 경로 : /webmaster/webmasterLogout
	public ModelAndView logoutWebmaster(HttpServletRequest request);
	*/
	// 매핑 경로 : /webmaster/tryAddNotice
	@RequestMapping("/webmaster/insertest")
	public ModelAndView insertNoticeOk(HttpServletRequest request, NoticeVO vo) {
		return new InsertNoticeOkCommand().execute(request,vo);
	}
		
		
}
