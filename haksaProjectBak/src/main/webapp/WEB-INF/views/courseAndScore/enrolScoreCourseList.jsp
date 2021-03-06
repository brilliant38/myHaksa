<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>수강신청 및 정정</title><!-- Bootstrap core CSS-->

<script src="/resources/vendor/jquery/jquery.min.js"></script>

<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin.css" rel="stylesheet">

<script>
	$(document).ready(()=>{
		
		var = 
		
		$('#insertScore').click(()=>{
			alert('수강신청 페이지로 이동합니다.');
			
			$.ajax({
		        url:"/courseAndScore/enrolScore",
		        type:'POST',
		        data: allData,
		        success:(data)=>{
		            
		           
		        },
		        error:(jqXHR, textStatus, errorThrown)=>{
		            
		        }
		    });
		});
		
	});
</script>
<style>
	#enrolScoreCourseListMain{
		text-align : left;
		font-weight: bold;
	}
</style>
</head>

<body id="page-top">

	<jsp:include page="/WEB-INF/views/module/nav.jsp"/>

	<div id="wrapper">

		<jsp:include page="/WEB-INF/views/module/sidebar.jsp"/>

		<div id="content-wrapper">

			<div class="container-fluid">
			<!-- 여기에 내용이 담긴다 -->
			
			<p>담당강좌목록</p>
			
			<form>
				<table class="table table-bordered">
					<thead align="center">
						<tr align="right">
							<td scope="col" colspan="10">
								<p id = "enrolScoreCourseListMain">담당과목성적등록</p>
								<br><br>
							</td>
					    </tr>
						<tr>
							<th>년도</th>
							<th>학기</th>
							<th>강의상황서 번호</th>
							<th>과목명</th>
							<th>학점</th>
							<th>입력 확정</th>
							<th>공개 여부</th>
						</tr>
					</thead>
					
					<tbody align="center">
						<c:forEach var="row" items="${insertScore}">
							<tr>
								<td>${row.insertScoreSchoolYear }</td>
								<td>${row.insertScoreSemester }</td>
								<td>${row.lectureStatusNumber }</td>
								<td><a id="insertScore" href="/courseAndScore/enrolScore?subject=${row.insertScoreCourseName}">${row.insertScoreCourseName}</a></td>
								<td>${row.insertScoreCourseCredit }</td>
								<td>${row.insertScoreFixScore }</td>
								<td>${row.insertScoreOpenScore }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			
			</div>
			<!-- /.container-fluid -->
			
			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright © Haksa 2018</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->

	</div>
	
	
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="/resources/vendor/chart.js/Chart.min.js"></script>
	<script src="/resources/vendor/datatables/jquery.dataTables.js"></script>
	<script src="/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="/resources/js/demo/datatables-demo.js"></script>
	<script src="/resources/js/demo/chart-area-demo.js"></script>
	
	
</body>
</html>