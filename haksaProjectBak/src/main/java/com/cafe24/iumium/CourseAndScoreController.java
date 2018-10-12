package com.cafe24.iumium;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	public String enrolCourseList(Model model, HttpSession session) {
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
	 * 1-1 선택된 과목의 행 정보를 모두 전달하는 배열 값을 받아서 수강신청 테이블에 입력
	 */
	
	
	
	
	/*
	 *	2.수강 신청된 과목정보를 입력된 학번을 통해서 조회하는 메소드 
	 *	입력 값 = 학번, 반환 값 = 과목 정보의 리스트 객체, 뷰 이름이 담긴 ModelAndView 객체
	 */
	
	@RequestMapping(value="/courseAndScore/searchCourse", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView searchCourse(ModelAndView mv, HttpServletRequest request) {
		System.out.println("CourseAndScoreController - searchCourse() 호출");
		String option = null;
		String value = null;
		
		if(request.getParameter("option") == null) {
			option = "studentnumber";
		} else {
			option = request.getParameter("option");
		}
		
		if(request.getParameter("value") == null) {
			value = "";
		} else {
			value = request.getParameter("value");
		}
		
		String[] array = new String[2];
		array[0] = option;
		array[1] = value;
		
		System.out.println("array[0] 출력 : " + array[0]);
		System.out.println("array[1] 출력 : " + array[1]);
		
		List<EnrolCourse> enrolCourse = courseAndScoreService.searchEnrolCourse(array);
		
		mv.addObject("enrolCourse", enrolCourse);
		mv.setViewName("/courseAndScore/searchCourse");

		return mv;
	}
	
	/*
	 * 3. 교수의 세션id를 검색하여 담당 과목을 조회 하고 과목 정보의 리스트를 불러온다. 
	 */
	
	@RequestMapping(value="/courseAndScore/enrolScoreCourseList")
	public String enrolScoreCourseList(Model model, HttpSession session) {
		
		System.out.println("CourseAndScoreController - enrolScoreCourseList() 호출");
		String id = null;
		String level = null;
		
		if(session.getId() != null) {
			id = (String)session.getAttribute("userId");
			level = (String)session.getAttribute("userLevel");
		}
		
		System.out.println("session ID : " + id);
		System.out.println("session LEVEL : " + level);
		
		List<InsertScore> insertScore = courseAndScoreService.searchEnrolScoreCourseList(id, level);
		
		model.addAttribute("insertScore", insertScore);
		model.addAttribute("id", id);
		
		return "/courseAndScore/enrolScoreCourseList";
	}
	
	/*
	 * 4. 교수의 강의 과목명을 입력하여 과목을 수강하는 학생들의 정보가 담긴 리스트가 조회된다.
	 * 
	 */
	
	@RequestMapping(value="/courseAndScore/enrolScore" , method= {RequestMethod.POST, RequestMethod.GET})
	public String enrolScoreCourse(Model model, HttpServletRequest request) {
		System.out.println("CourseAndScoreController - enrolScoreCourse() 호출");
		
		String subjectName = (String)request.getParameter("subject");
		
		List<EnrolCourse> enrolCourse = courseAndScoreService.searchEnrolScoreCourse(subjectName);
		
		//강의 정보도 같이 전송해야 하므로 컨트롤러에서 처리해야 함.
		//model.addAttribute("EnrolCourse",EnrolCourse);
		model.addAttribute("enrolCourse", enrolCourse);
		
		return "/courseAndScore/enrolScore";
	}
	
	/*
	 * 5. 반별 석차 조회
	 */
	
	@RequestMapping(value="/courseAndScore/printScoreRankInClass", method= {RequestMethod.POST, RequestMethod.GET})
	public String printScoreCourseByClass(Model model, HttpServletRequest request) {
		System.out.println("CourseAndScoreController - EnrolScoreCourseByClass() 호출");
		
		String ClassName = null;
		
		if(request.getParameter("ClassName") != null) {
			ClassName = (String)request.getParameter("ClassName");
		}
		
		List<EnrolCourse> enrolCourse = courseAndScoreService.searchEnrolScoreCourseByClass(ClassName);
		
		model.addAttribute("enrolCourse", enrolCourse);
		
		
		return "/courseAndScore/printScoreRankInClass";
	}
	/*
	 * 6.학과별 석차 조회
	 */
	@RequestMapping(value="/courseAndScore/printScoreRankInDept", method= {RequestMethod.POST, RequestMethod.GET})
	public String printScoreCourseByDept(Model model, HttpServletRequest request) {
		
		String DeptName = null;
		
		if(request.getParameter("DeptName") != null) {
			DeptName = (String)request.getParameter("DeptName");
		}
		
		List<EnrolCourse> enrolCourse = courseAndScoreService.searchprintScoreCourseByDept(DeptName);
		
		model.addAttribute("enrolCourse", enrolCourse);
		
		return "/courseAndScore/printScoreRankInDept";
	}
}
