package com.checkin.webapp.room.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.room.model.RoomDAOInterface;
import com.checkin.webapp.room.model.RoomVO;

public class ShowRoomListCommand implements RoomCommandInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request, RoomVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		String aStr = request.getParameter("a");
		int a = 0;
		if(aStr != null && aStr != "") a = Integer.parseInt(aStr);
		String checkinout = request.getParameter("checkinout");
		System.out.println("ShowRoomListCommand.. a = "+ a + ", checkinout = "+ checkinout);
		
		//===================== dao 작업 ========================//
		RoomVO vo = new RoomVO();
		vo.setA(a);
		RoomDAOInterface dao = Constants.sqlSession.getMapper(RoomDAOInterface.class);
		List<RoomVO> list = dao.selectAllRoomList(vo);
		System.out.println("ShowRoomListCommand.."+list.toString());
		//===================== dao 작업 ========================//
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("main/accomodation/showDetailAccomodation");
		return mav;
	}

}
