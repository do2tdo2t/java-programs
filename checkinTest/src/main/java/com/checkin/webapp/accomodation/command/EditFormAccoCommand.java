package com.checkin.webapp.accomodation.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.accomodation.model.AccomodationDAOInterface;
import com.checkin.webapp.accomodation.model.AccomodationVO;

public class EditFormAccoCommand implements AccomodationCommandInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request, AccomodationVO vo) {	
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		System.out.println("EditFormAccoCommand... in");
		int a = 0 ;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("master/accomodation/editAccomodation");
		//dao 작업
		AccomodationDAOInterface dao  = Constants.sqlSession.getMapper(AccomodationDAOInterface.class);

		String aStr = request.getParameter("a");
		if(aStr!= null && aStr!="") a = Integer.parseInt(aStr);
		AccomodationVO vo = new AccomodationVO();
		vo.setA(a);
		AccomodationVO resultVo = dao.selectOneRecord(vo);
		System.out.println(resultVo.toString());
		mav.addObject("accoVO",resultVo);
		
		return mav;
	}

}
