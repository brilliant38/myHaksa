package com.cafe24.iumium.courseandscore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.iumium.courseandscore.dao.CourseAndScoreDao;
import com.cafe24.iumium.courseandscore.dto.EnrolCourse;
import com.cafe24.iumium.courseandscore.dto.InsertScore;

@Service
@Transactional
public class CourseAndScoreService {
	
	@Autowired
	private CourseAndScoreDao courseAndScoreDao;
	
	/*1. 세션 아이디 받아서 학과별반번호 조회
	 * 	학과별반번호 일치하는 강의상황서 조회
	 * 	조회된 과목 정보 출력 
	 */
	
	public List<EnrolCourse> selectEnrolCourse(String id){ 
		System.out.println("CourseAndScoreService - selectEnrolCourse() 호출");
		
		int classByDepartmentNumber = courseAndScoreDao.inquireClassByDepartmentNumber(id);
		System.out.println("세션 학번으로 학과별반번호 조회 : " + classByDepartmentNumber);
		
		List<EnrolCourse> lectureStatusNumber = courseAndScoreDao.inquireLectureStatus(classByDepartmentNumber);
		System.out.println("학과별반번호로 강의상황서 번호 : " + lectureStatusNumber);
		
		//list = courseAndScoreDao.inquireDeptCourse(deptCode);
		
		return lectureStatusNumber;
	}
	
	/*
	 *	2.입력된 아이디로 수강신청 테이블 조회
	 *	강의상황서 레코드의 속성별로 코드는 해당 코드의 필요값 조회
	 */
	
	public List<EnrolCourse> searchEnrolCourse(String[] array){
		return courseAndScoreDao.inquireSearchLectureById(array);
	}
	
	/*
	 * 	3.입력된 교수 세션 아이디로 담당 과목 코드 조회
	 * 	조회된 과목 코드로 과목 명 조회
	 */
	
	public List<InsertScore> searchEnrolScoreCourseList(String id) {
		
		//입력된 교수 세션 아이디로 담당 과목 코드 조회
		List<InsertScore> InsertScore =  courseAndScoreDao.inquireEnrolScoreCourseCode(id);
		
		System.out.println("InsertScore 과목코드 출력: " + InsertScore.get(0).getEnrolSubjectNo());
		System.out.println("InsertScore 과목명 출력: " + InsertScore.get(0).getInsertScoreCourseName());
		
		return InsertScore;
	}
	
	/*
	 * 4.교수가 강의중인 과목을 클릭하여 입력된 과목명을 
	 * 수강 신청 내역 테이블에서 조회하여 겹치는 학생의 정보와 과목 정보를 반환  하는 dao 호출
	 */
	
	public List<EnrolCourse> searchEnrolScoreCourse(String subjectName) {
		
		//해당 수강생 학번 리스트 조회
		List<EnrolCourse> enrolCourse = courseAndScoreDao.inquireEnrolScoreStudentNum(subjectName);
		
		
		return enrolCourse; 
	}
	
	
	
	/*
	 * 5.입력 받은 반 조건으로 성적입력 테이블 조회하는 dao 호출
	 */
	
	public List<EnrolCourse> searchEnrolScoreCourseByClass(String ClassName) {
		return courseAndScoreDao.inquireEnrolScoreCourseByClass(ClassName);
	}
	/*
	 * 6.입력 받은 학과 조건으로 성적입력 테이블 조회하는 dao 호출
	 */
	public List<EnrolCourse> searchprintScoreCourseByDept(String DeptName) {
		return courseAndScoreDao.inquireprintScoreCourseByDept(DeptName);
	}
	
}
