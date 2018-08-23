package com.checkin.webapp.accomodation.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.accomodation.model.AccomodationVO;

public class InsertCommand implements AccomodationCommandInterface{

	@Override
	public ModelAndView execute(HttpServletRequest request, AccomodationVO vo) {
		
		return null;
	}
	

	public ModelAndView execute(HttpServletRequest request, AccomodationVO vo,MultipartFile file1,MultipartFile file2,MultipartFile file3) {
		System.out.println("InsertCommand.."+vo.toString());
		return null;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
