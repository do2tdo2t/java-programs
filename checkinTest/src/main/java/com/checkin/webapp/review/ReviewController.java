package com.checkin.webapp.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.review.command.EditReviewCommand;
import com.checkin.webapp.review.command.InsertReviewCommand;
import com.checkin.webapp.review.command.SelectListCommand;
import com.checkin.webapp.review.command.SelectReviewAjaxCommand;
import com.checkin.webapp.review.command.SelectReviewListAjaxCommand;
import com.checkin.webapp.review.command.SelectReviewsCntAjaxCommand;
import com.checkin.webapp.review.model.ReviewVO;

@Controller
public class ReviewController {
	/*
	//리뷰쓰기 폼으로 이동
	public ModelAndView writeReviewForm(HttpServletRequest request) {}
	
	//리뷰쓰기 성공/실패
	public ModelAndView writeReviewOk(HttpServletRequest request, ReviewVO vo) {}
	
	public ModelAndView editReviewForm(HttpServletRequest request) {}
	
	public ModelAndView editReviewOk(HttpServletRequest request, ReviewVO vo) {}
	
	public ModelAndView deleteReview(HttpServletRequest request, ReviewVO vo) {}
	
	public List<ReviewVO> selectReviewAjax(HttpServletRequest request){}
	
	*/
	//일반회원 리뷰 리스트
	// 매핑 주소 : /master/showAccoReviewList
	@RequestMapping("/main/showAccoReviewList")
	public ModelAndView showMemberReviewList(HttpServletRequest request, ReviewVO vo){
	
		return new SelectListCommand().execute(request,vo); 
	}
	
	@RequestMapping("/main/getReivews")
	@ResponseBody
	public List<ReviewVO> selectReviewListAjax(HttpServletRequest request, ReviewVO vo){
		return new SelectReviewListAjaxCommand().executeAjax(request,vo);
	}
	
	@RequestMapping("/main/getReivewsCnt")
	@ResponseBody
	public int selectReviewCntAjax(HttpServletRequest request, @RequestParam("r") int r){
		return new SelectReviewsCntAjaxCommand().executeAjax(request,r);
	}
	
	@RequestMapping(value="/main/mypage/tryWriteReview",method=RequestMethod.POST)
	public ModelAndView writeReview(HttpServletRequest request,ReviewVO vo , MultipartFile file1) {
		return new InsertReviewCommand().execute(request,vo,file1);
	}
	
	@RequestMapping("/main/mypage/getReview")
	@ResponseBody
	public ReviewVO selectReviewAjax(HttpServletRequest request, ReviewVO vo){
		return new SelectReviewAjaxCommand().executeAjax(request,vo);
	}
	
	
	@RequestMapping(value="/main/mypage/tryEditReview",method=RequestMethod.POST)
	public ModelAndView editReview(HttpServletRequest request, ReviewVO vo, MultipartFile file1) {
		System.out.println("ReviewController... editReview.. "+vo.toString());
		return new EditReviewCommand().execute(request,vo,file1);
	}
	
}
