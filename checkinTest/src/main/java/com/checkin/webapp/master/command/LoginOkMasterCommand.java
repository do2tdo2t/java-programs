package com.checkin.webapp.master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.master.model.MasterDAOInterface;
import com.checkin.webapp.master.model.MasterVO;

public class LoginOkMasterCommand implements MasterCommandInterface{

	@Override
	public ModelAndView execute(HttpServletRequest request, MasterVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


	public MasterVO executeVo(HttpServletRequest request, MasterVO vo) {
		MasterDAOInterface dao = Constants.sqlSession.getMapper(MasterDAOInterface.class);
		//로그인 처리
		MasterVO vo2 = dao.loginMaster(vo);
		System.out.println("LoginOkMaster.."+vo.toString());
		
		if(vo2!=null) {
			System.out.println("LoginOkMaster.. 로그인 성공"+vo2.toString());
			HttpSession session = request.getSession();
			session.setAttribute("mname", vo2.getMname());
			session.setAttribute("mid", vo2.getMid());
			session.setAttribute("type", "M");
			session.setAttribute("logChk", "Y");
			//session.setAttribute("aname", vo2.getAname());
			//session.setAttribute("a", vo2.getA());
			//selectOneRecord -accomodation
		}else {
			System.out.println("LoginOkMaster.. 로그인 실패");
		}
		return vo2;
	}

}
