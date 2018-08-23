package com.checkin.webapp.accomodation.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.accomodation.model.AccomodationDAOInterface;
import com.checkin.webapp.accomodation.model.AccomodationVO;

public class ViewAccoCommand implements AccomodationCommandInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request, AccomodationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		System.out.println("GetAccoCommand.. in");
		ModelAndView mav = new ModelAndView();
		
		//session 처리
		String mid = getMidToSession(request);
		if(mid != null && mid != "") {
			System.out.println("GetAccoCommand.."+mid);
			mav.setViewName("master/accomodation/manageAccomodation");
			//dao처리
			AccomodationVO vo = excuteDAO(mid);
			mav.addObject("accoVO", vo);
			
			System.out.println("GetAccoCommand.."+vo.toString());
		}else {
			mav.setViewName("redirect:/main/login");
		}
		
		
		return mav;
	}
	
	public String getMidToSession(HttpServletRequest request) {
		return (String)request.getSession().getAttribute("mid");
	}
	
	public AccomodationVO excuteDAO(String mid) {
		AccomodationVO vo = new AccomodationVO();
		vo.setMid(mid);
	
		AccomodationDAOInterface dao = Constants.sqlSession.getMapper(AccomodationDAOInterface.class);
		return dao.selectOneRecord(vo);

	}
}
