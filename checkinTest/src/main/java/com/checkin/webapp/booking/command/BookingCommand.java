package com.checkin.webapp.booking.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.booking.model.BookingDAOInterface;
import com.checkin.webapp.booking.model.BookingVO;

public class BookingCommand implements BookingCommandInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request, BookingVO vo) {
		ModelAndView mav = new ModelAndView();
		//dao 작업............................
		
		
		return null;
	}
	
	public int executeAjax(HttpServletRequest request, BookingVO vo) {
		int result = 0;
		//dao 작업............................
		BookingDAOInterface dao = Constants.sqlSession.getMapper(BookingDAOInterface.class);
		result = dao.insertBooking(vo);
		System.out.println("BookingCommand..."+result);
		return result;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
