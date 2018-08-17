package com.checkin.webapp.accomodation.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.accomodation.model.AccomodationDAOInterface;
import com.checkin.webapp.accomodation.model.AccomodationVO;

public class ShowAccoListCommand implements AccomodationCommandInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request, AccomodationVO vo) {
	
		
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		System.out.println("ShowAccoListCommand..execute()..");
		String checkinout =  request.getParameter("checkinout");
		String agu = request.getParameter("agu");
		String asubway = request.getParameter("asubway");
		String atype = request.getParameter("atype");
		String asorttype = request.getParameter("asorttype");
		System.out.println("ShowAccoListCommand..execute().."+checkinout+" "+agu+" "+asubway+" "+atype+" "+asorttype);
		
		//<![CDATA[ AND ROWNUM <= 15 ]]>
		
		ModelAndView mav = new ModelAndView();
		
		//dao 작업
		AccomodationDAOInterface dao = Constants.sqlSession.getMapper(AccomodationDAOInterface.class);
		AccomodationVO vo = new AccomodationVO();
		vo.setAgu(agu);
		vo.setAsubway(asubway);
		vo.setAtype(atype);
		vo.setAonepage(10);
		vo.setAcurpage(1);
		vo.setAsortkey(asorttype);
		System.out.println(vo.toString());
		List<AccomodationVO> list = dao.selectAllList(vo);
		System.out.println("ShowAccoListCommand..execute().."+list.size()+" "+ list.toString());
		mav.addObject("list",list);
		mav.setViewName("main/accomodation/showAccomodationList");
		
		//session 작업 - session에 checkinout 시간, 유형, 구, 인근 지하철 정보를 세팅한다.
		HttpSession session = request.getSession();
		session.setAttribute("checkinout", checkinout);
		session.setAttribute("gu", agu);
		session.setAttribute("subway", asubway);
		session.setAttribute("type", atype);
		if (asorttype== null || asorttype == "") session.setAttribute("asorttype", "writedate");
		else session.setAttribute("asorttype", asorttype);
		
		return mav;
	}

}
