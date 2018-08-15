package com.checkin.webapp.webmaster.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.webmaster.model.NoticeDAOInterface;
import com.checkin.webapp.webmaster.model.NoticeVO;

public class InsertNoticeOkCommand implements WebmasterCommandInterface {
	
	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public ModelAndView execute(HttpServletRequest request, NoticeVO vo) {
		System.out.println("InsertNoticeOkCommand. execute()");
		ModelAndView mav = new ModelAndView();
		NoticeVO test = new NoticeVO();
		test.setNostartdate("2018-08-16");
		test.setNoenddate("2018-08-20");
		test.setNotitle("연휴 관련 공지 입니다.");
		test.setTarget(0);
		test.setWid("webm");
		
		NoticeDAOInterface mapper = sqlSession.getMapper(com.checkin.webapp.webmaster.model.NoticeDAOInterface.class);
		int result = mapper.insertNotice(test);
		System.out.println("InsertNoticeOkCommand.. call insertNotice().."+result);
		
		return null;
	}

}
