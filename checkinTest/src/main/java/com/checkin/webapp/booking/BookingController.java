package com.checkin.webapp.booking;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.booking.command.BookingCancleCommand;
import com.checkin.webapp.booking.command.BookingCommand;
import com.checkin.webapp.booking.model.BookingVO;

@Controller
public class BookingController {
	/*
	//예약리스트 보기(관리자)
	public ModelAndView showAllBookingList(HttpServletRequest request, BookingVO vo) {}
	
	//예약리스트 보기(일반회원)
	public ModelAndView showMyBookingList(HttpServletRequest request, BookingVO vo) {}
	
	//예약하기
	public int insertBookingAjax(HttpServletRequest request) {}
	
	public ModelAndView cancelBooking(HttpServletRequest request, BookingVO vo) {}
	
	*/
	//예약하기
	
	@RequestMapping(value="/main/trybooking", method = RequestMethod.GET)
	@ResponseBody
	public int insertBookingAjax(HttpServletRequest request,BookingVO vo) {
		System.out.println("BookingController.."+vo.toString());
	
		return new BookingCommand().executeAjax(request,vo);
	}
	
	@RequestMapping(value="/main/mypage/trycanclebooking", method = RequestMethod.GET)
	public ModelAndView cancelBooking(HttpServletRequest request,BookingVO vo) {
		System.out.println("BookingController.."+vo.toString());
	
		return new BookingCancleCommand().execute(request,vo);
	}
		
}
