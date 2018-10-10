package com.cafe24.iumium;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.iumium.courseandscore.dto.EnrolCourse;
import com.cafe24.iumium.courseandscore.dto.InsertScore;
import com.cafe24.iumium.courseandscore.service.CourseAndScoreService;

@Controller
public class CourseAndScoreController {
	
	@Autowired
	private CourseAndScoreService courseAndScoreService;
	
	/*1.세션에서 학번을 입력받아 수강 신청가능 과목을 조회하여 표로 출력해주는 메소드
	 * 입력 값 = 세션 값, 반환 값 = view 페이지명 문자열
	*/
	
	@RequestMapping(value="/courseAndScore/enrolCourse")
	public String enrolCourse(Model model, HttpSession session) {
		System.out.println("CourseAndScoreController - enrolCourse() 호출");
		String id = null;
		
		//세션 아이디 값이 있을 시 id 문자열 변수에 대입
		if(session.getId() != null) {
			id = (String)session.getAttribute("userId");
		}
		System.out.println("session ID : " + id);
		
		/*
		 *  수강신청 목록 조회 서비스 메소드 호출
		 *  입력 값 = id, 반환 값 = EnrolCourse 타입 리스트
		 */
		List<EnrolCourse> enrolCourse = courseAndScoreService.selectEnrolCourse(id);
		
		// 모델 객체에 반환 값과 세션 id 저장
		model.addAttribute("enrolCourse", enrolCourse);
		model.addAttribute("id", id);
		
		return "/courseAndScore/enrolCourse";
	}
	
	/*
	 *	2.수강 신청된 과목정보를 입력된 학번을 통해서 조회하는 메소드 
	 *	입력 값 = 학번, 반환 값 = 과목 정보의 리스트 객체, 뷰 이름이 담긴 ModelAndView 객체
	 */
	
	@RequestMapping(value="/courseAndScore/searchCourse", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView searchCourse(ModelAndView mv, HttpServletRequest request) {
		System.out.println("CourseAndScoreController - searchCourse() 호출");
		String id = request.getParameter("id");
		
		List<EnrolCourse> enrolCourse = courseAndScoreService.searchEnrolCourse(id);
		
		// 
		mv.addObject("enrolCourse", enrolCourse);
		mv.setViewName("/courseAndScore/searchCourse");

		return mv;
	}
	
	/*
	 * 3. 교수의 세션id를 검색하여 담당 과목을 조회 하고 과목 정보를 불러온다. 
	 */
	
	@RequestMapping("/courseAndScore/enrolScoreCourseList")
	public ModelAndView enrolScoreCourseList(ModelAndView mv, HttpSession session) {
		
		System.out.println("CourseAndScoreController - enrolScoreCourseList() 호출");
		String id = null;
		
		/*if(session.getId() != null) {
			id = (String)session.getAttribute("userId");
		}*/
		
		id = "3456";
		
		System.out.println("session ID : " + id);
		
		List<InsertScore> insertScore = courseAndScoreService.searchEnrolScoreCourseList(id);
		
		mv.addObject("insertScore", insertScore);
		mv.addObject("id", id);
		mv.setViewName("/courseAndScore/enrolScoreCourseList");
		
		return mv;
	}
	
	@RequestMapping("/courseAndScore/enrolScore")
	public String enrolScoreCourse(ModelAndView mv) {
		System.out.println("CourseAndScoreController - enrolScoreCourse() 호출");
		
		
		
		return "/courseAndScore/enrolScore";
	}
	
	
}
