package com.checkin.webapp.webmaster.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.webmaster.model.NoticeDAOInterface;
import com.checkin.webapp.webmaster.model.NoticeVO;

public class InsertNoticeOkCommand implements WebmasterCommandInterface {


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

		
		if (Constants.sqlSession != null) {
			System.out.println("sqlSession is not null....");
			NoticeDAOInterface mapper = Constants.sqlSession.getMapper(NoticeDAOInterface.class);
			int result = mapper.insertNotice(test);
			System.out.println("InsertNoticeOkCommand.. call insertNotice().." + result);
		} else {
			System.out.println("sqlSession is null....");
		}
		return mav;
	}

}
