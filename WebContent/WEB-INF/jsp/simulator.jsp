<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.huan.model.Teacher"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/Resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx}/Resources/js/jquery/jquery-1.9.1.min.js"></script>
<title>模拟数据展示</title>
</head>
<script>
	$(document)
			.ready(
					function() {
						$("input").attr("readonly",true);
						//<tr/>居中
						$("#tab tr").attr("align", "center");

						//增加<tr/>
						$("#but")
								.click(
										function() {
											var _len = $("#tab tr").length;
											$("#tab")
													.append(
															"<tr id="+_len+" align='center'>"
																	+ "<td>"
																	+ _len
																	+ "</td>"
																	+ "<td><input type='text' name='teachers["+(_len-1)+"].teacherName' /></td>"
																	+ "<td><input type='text' name='teachers["+(_len-1)+"].courseName' /></td>"
																	+ "<td><input type='number' name='teachers["+(_len-1)+"].perWeekClassNum'  min='1' /></td>"
																	+ "<td><input type='number' name='teachers["+(_len-1)+"].perWeekTimeNum'  min='1' /></td>"
																	+ "<td><input type='checkbox' name='teachers["+(_len-1)+"].IsHead' /></td>"
																	+ "<td><input type='checkbox' name='teachers["+(_len-1)+"].IsNext' /></td>"
																	+ "<td><a href=\'#\' onclick=\'deltr("
																	+ _len
																	+ ")\'>删除</a></td>"
																	+ "</tr>");
										})
					})

	$(function() {
		$('#btnConfirm').click(
				function() {
					var milasUrl = {};//新建对象，用来存储所有数据
					var count = 0;
					var subMilasUrlArr = {};//存储每一行数据
					var tableData = {};
					$("#tab tr").each(function(trindex, tritem) {//遍历每一行
						tableData[trindex] = new Array();
						$(tritem).find("input").each(function(tdindex, tditem) {
							tableData[trindex][tdindex] = $(tditem).val();//遍历每一个数据，并存入
							if ($(tditem).attr("name").indexOf("IsHead")!=-1&&$(tditem).is(":checked")) {
								count++;
// 								console.log($(tditem).attr("name"));
							}
						});
						subMilasUrlArr[trindex] = tableData[trindex];//将每一行的数据存入
					});
					for ( var key in subMilasUrlArr) {
						milasUrl[key] = subMilasUrlArr[key];//将每一行存入对象
					}
					var vaildate = true;
					if ($(".classNum").val() == "") {
						alert("班级数目不能为空")
						return false;
					}
					if ($(".morning").val() == "") {
						alert("年级信息不能为空")
						return false;
					}
					if ($(".afternoon").val() == "") {
						alert("年级信息不能为空")
						return false;
					}
					if ($(".saturday").val() == "") {
						alert("年级信息不能为空")
						return false;
					}
					if ($(".sunday").val() == "") {
						alert("年级信息不能为空")
						return false;
					}
					if (count != $(".classNum").val()) {
						console.log(count);
						console.log(" "+$(".classNum").val());
						alert("班级数目与班主任老师数目不符")
						return false;
					}
					var everyRow = {};
					for ( var key in milasUrl) {
						everyRow[key] = milasUrl[key];
						for ( var one in everyRow[key]) {
							if (everyRow[key][one] == "") {
								alert("第" + key + "行" + "第"
										+ (parseInt(one) + 1) + "列不能为空")
								return false;
							}
						}
					}
// 					var sum1 = 0;
// 					for ( var key in milasUrl) {
// 						everyRow[key] = milasUrl[key];
// 						sum1 += everyRow[key][2] * everyRow[key][3];
// 					}
					var mNum = parseInt($(".morning").val());
					var aNum = parseInt($(".afternoon").val());
					var saNum = parseInt($(".saturday").val());
					var suNum = parseInt($(".sunday").val());
// 					var sum2 = count * (5 * (mNum + aNum) + saNum + suNum);
// 					if (sum1 != sum2) {
// 						alert("年级信息显示的课时总数目与教师总课时数目不相等")
// 						return false;
// 					}

					$('form').submit();
				})

	})

	var deltr = function(index) {
		var _len = $("#tab tr").length;
		$("tr[id='" + index + "']").remove();//删除当前行
		for (var i = index + 1, j = _len; i < j; i++) {
			$("tr[id=\'" + i + "\']").attr("id", (i - 1));
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(1)").html(i - 1);
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(2) input").attr("name","teachers["+(i-2)+"].teacherName");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(3) input").attr("name","teachers["+(i-2)+"].courseName");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(4) input").attr("name","teachers["+(i-2)+"].perWeekClassNum");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(5) input").attr("name","teachers["+(i-2)+"].perWeekTimeNum");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(6) input").attr("name","teachers["+(i-2)+"].IsHead");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(7) input").attr("name","teachers["+(i-2)+"].IsNext");
			$("tr[id=\'" + (i - 1) + "\'] td:nth-child(8) a").attr("onclick",
					"deltr(" + (i - 1) + ")");
		}

	}
</script>
<style>
.grade{
width:38%;
height:35px;
}
</style>
</head>
<body>
	<div class="container">

		<br />


		<form action="/sortCourse/deal.action" method="post"
			class="documentForm" target="_blank">
			<h3 class="text-center">年级信息</h3>
			<table   border="0" >
				<tr>
				<td  class="grade">班级数目:<input type="number" name="classNum" class="classNum" min='1' value="15"></td>
				<td  class="grade">上午几节课:<input type="number" name="morning" class="morning" min='1' value="4"></td>
				<td  class="grade">下午几节课:<input type="number" name="afternoon" class="afternoon" min='1' value="3"></td>
				</tr>
				<tr class="grade">
				<td  class="grade">周六一共几节课:<input type="number" name="saturday" class="saturday" min='0' value="4"></td>
				<td  class="grade">周日一共几节课:<input type="number" name="sunday" class="sunday" min='0' value="0"></td>
				<td  class="grade">空白课可以放在上午最后一节<input type="checkbox" name="allowMorning" /></td>
				</tr>
				</table>


			<h3 class="text-center">教师信息</h3>
			<table id="tab" border="1"
				class="table table-hover table-striped table-bordered">
				<tr>
					<td>序号</td>
					<td>教师名</td>
					<td>课程名</td>
					<td>任教班级数目</td>
					<td>每周每班课时数目</td>
					<td>是否班主任</td>
					<td>是否单/双周</td>
				</tr>

				<%
					List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");

					for (int k = 0; k < teachers.size(); k++) {
				%>
				<tr id="<%=(k + 1)%>">
					<td><%=(k + 1)%></td>
					<td><input type="text" name=<%=String.format("teachers[%d].teacherName", k)%>
						value=<%=teachers.get(k).getTeacherName()%>></td>
					<td><input type="text" name=<%=String.format("teachers[%d].courseName", k)%>
						value=<%=teachers.get(k).getCourseName()%>></td>
					<td><input type="number" name=<%=String.format("teachers[%d].perWeekClassNum", k)%>
						value=<%=teachers.get(k).getPerWeekClassNum()%>></td>
					<td><input type="number" name=<%=String.format("teachers[%d].perWeekTimeNum", k)%>
						value=<%=teachers.get(k).getPerWeekTimeNum()%>></td>
					<%
						if (teachers.get(k).isIsHead()) {
					%>
					<td><input type="checkbox" name=<%=String.format("teachers[%d].IsHead", k)%> checked="true"
						/></td>
					<%
						} else {
					%>
					<td><input type="checkbox" name=<%=String.format("teachers[%d].IsHead", k)%> /></td>
					<%
						}
					%>
					<%
						if (teachers.get(k).isIsNext()) {
					%>
					<td><input type="checkbox" name=<%=String.format("teachers[%d].IsNext", k)%> checked="true"
						/></td>
					<%
						} else {
					%>
					<td><input type="checkbox" name=<%=String.format("teachers[%d].IsNext", k)%>  /></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
			<table>
				<td align="center"></td>
				<td align="center">
					<button type="button" class="btn btn-danger btn-lg" id="btnConfirm"
						style="aligen: center">提交</button>
				</td>
			</table>


		</form>
	</div>
</body>
</html>