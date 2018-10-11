package com.cafe24.iumium.courseandscore.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.iumium.courseandscore.dto.EnrolCourse;
import com.cafe24.iumium.courseandscore.dto.InsertScore;

@Repository
public class CourseAndScoreDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String nameSpace = "com.cafe24.iumium.courseandscore.dao.CourseAndScoreMapper.";
	
	// 1.세션 아이디 받아서 학과코드, 반 조회
	public int inquireClassByDepartmentNumber(String id){
		System.out.println("CourseAndScoreDao - inquireStudentDept() 호출");
		List<EnrolCourse> list = sqlSessionTemplate.selectList(nameSpace + "inquireStudentDept", id);
		
		int classByDepartmentNumber = list.get(0).getClassByDepartmentNumber();
		System.out.println("학번으로 조회된 학과별반번호 : " + classByDepartmentNumber);
		
		return classByDepartmentNumber;
	}
	
	// 2.조회된 학과별반번호로 강의상황서 번호와 모든 속성코드 조회
	
	public List<EnrolCourse> inquireLectureStatus(int lectureStatusNumber){
		System.out.println("CourseAndScoreDao - inquireDeptCourse() 호출");
		
		return sqlSessionTemplate.selectList(nameSpace + "inquireLectureStatus", lectureStatusNumber);
	}
	
	// 3. 입력된 조건, 값으로 수강신청내역 테이블에서 학번이 일치하는 모든 레코드의 컬럼 값 조회
	
	public List<EnrolCourse> inquireSearchLectureById(String[] array) {
		return sqlSessionTemplate.selectList(nameSpace + "inquireSearchLectureById", array);
	}
	
	//	4. 세션 교수 아이디로 담당 과목코드 조회
	public List<InsertScore> inquireEnrolScoreCourseCode(String id) {
		return sqlSessionTemplate.selectList(nameSpace + "inquireEnrolScoreCourseCode", id);
	}
	
	// 5. 교과명으로 수강신청내역 테이블 학번 리스트 조회
	public List<EnrolCourse> inquireEnrolScoreStudentNum(String subjectName) {
		return sqlSessionTemplate.selectList(nameSpace + "inquireEnrolScoreStudentNum", subjectName);
	}
	
	// 6.학번 리스트로 성적 입력 할 학생 정보 조회
	
	public List<InsertScore> inquireEnrolScoreCourse(List<EnrolCourse> enrolCourse) {
		
		List<InsertScore> insertScore = sqlSessionTemplate.selectList(nameSpace + "inquireEnrolScoreCourse", enrolCourse);
		
		return insertScore; 
	}
	
	// 7.입력받은 반의 값으로 성적입력테이블에서 반이 일치하는 학생들의 평균 총점을 조회한다.
	public List<EnrolCourse> inquireEnrolScoreCourseByClass(String ClassName) {
		
		List<EnrolCourse> enrolCourse = sqlSessionTemplate.selectList(nameSpace + "inquireEnrolScoreCourseByClass", ClassName);
		
		if (enrolCourse.size() != 0) {
			System.out.println("학생 평점 출력 : " + enrolCourse.get(0).getInsertScoreTotalAverageScore());
		}
		
		return enrolCourse;
	}
	// 8.입력받은 학과의 값으로 성적입력테이블에서 반이 일치하는 학생들의 평균 총점을 조회한다.
	public List<EnrolCourse> inquireprintScoreCourseByDept(String DeptName) {
		
		List<EnrolCourse> enrolCourse = sqlSessionTemplate.selectList(nameSpace + "inquireprintScoreCourseByDept", DeptName);
		
		return enrolCourse;
	}
}
