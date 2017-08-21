<%@page import="com.huan.sort.util.startSortCourse"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.huan.model.BaseTeacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.huan.definition.ResultType"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/Resources/css/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/Resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/Resources/css/myStyle.css" rel="stylesheet"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx}/Resources/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${ctx}/Resources/js/jquery/jquery-ui-1.9.2.custom.min.js"></script>
<title>排课结果</title>

<style type="text/css">
#page{
	background-color:#999999;
}
.right {
	border: 0px;
	width: 13%;
	position: fixed;
	right: 1%;
	top: 3%;
	font-size: 15px;
	font-color: red;
	text-align: center;
}

.myInfo {
	font-size: 20px;
	display:"none";
}
.expand{
	color:white;
}
</style>
</head>
<script>
	var classNums;
	var lessonNums;
	var changeStr;
	var fixTable;
	$(document).ready(function() {
		classNums = $("tr[id='rooms'] td").length - 1;
		lessonNums = ($("tr").length - 8) / 7;
		changeStr = new Array(classNums);
		fixTable = new Array(classNums);
		for (var i = 0; i < classNums; i++) {
			fixTable[i] = new Array((lessonNums * 7));
			for (var j = 0; j < lessonNums * 7; j++) {
				fixTable[i][j] = 0;
			}
		}
		for (var i = 0; i < classNums; i++) {
			changeStr[i] = "";
		}

	})

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drop(ev) {

		ev.preventDefault();
		var dropPosotion = new Array(2);
		dropStr = ev.target.className;
		dropPosotion = dropStr.split(" ");

		var dragPosition = new Array(2)
		dragStr = ev.dataTransfer.getData("Text");
		dragPosition = dragStr.split(" ");
		if (dragPosition[0] != dropPosotion[0]) {
			alert("不允许跨班级");
		} else if (dragPosition[1] != dropPosotion[1]) {
			var dragTag = "[class='" + dragPosition[0] + " " + dragPosition[1]
					+ "']";
			var dropTag = "[class='" + dropPosotion[0] + " " + dropPosotion[1]
					+ "']";
			temp0 = $("body").find(dragTag).eq(0).html();
			temp1 = $("body").find(dragTag).eq(1).html();
			$("body").find(dragTag).eq(0).html(
					$("body").find(dropTag).eq(0).html());
			$("body").find(dragTag).eq(1).html(
					$("body").find(dropTag).eq(1).html());
			$("body").find(dropTag).eq(0).html(temp0);
			$("body").find(dropTag).eq(1).html(temp1);
			changeStr[dragPosition[0]] += dragPosition[1] + "="
					+ dropPosotion[1] + "&";
		}
	}

	function drag(ev) {
		ev.dataTransfer.setData("Text", ev.target.className);
	}
	// 	var fix = false;

	// 	function fixSite() {
	// 		fix = true;
	// 		$("td").css({
	// 			cursor : "pointer"
	// 		});
	// 	}
	// 	function cancelFix() {
	// 		fix = false;
	// 		$("td").css({
	// 			cursor : "auto"
	// 		});

	// 	}

	function fixCell(ev) {
		var position = new Array(2);
		positionStr = ev.target.className;
		position = positionStr.split(" ");

		if (fixTable[position[0]][position[1]] == 0) {
			var tag = "[class='" + position[0] + " " + position[1] + "']";
			$("body").find(tag).eq(0).attr("style", "background:#66FFFF");
			$("body").find(tag).eq(1).attr("style", "background:#66FFFF");
			fixTable[position[0]][position[1]] = 1;
		} else {
			var tag = "[class='" + position[0] + " " + position[1] + "']";
			$("body").find(tag).eq(0).attr("style", "background:white");
			$("body").find(tag).eq(1).attr("style", "background:white");
			fixTable[position[0]][position[1]] = 0;
		}
	}

	function changeSubmit() {
// 		var form = $("<form method='post' target='_blank'></form>");
// 		form.attr({
// 			"action" : "/sortCourse/change.action"
// 		});
// 		var args = new Array(2);
// 		var input = $("<input type='text'>");
// 		input.attr({
// 			"name" : "fixTable"
// 		});
// 		input.val(fixTable);
// 		form.append(input);
// 		input = $("<input type='text'>");
// 		input.attr({
// 			"name" : "changeStr"
// 		});
// 		input.val(changeStr);
// 		form.append(input);
// 		form.submit();
		$(".myFixTable").val(fixTable);
		$(".myChangeStr").val(changeStr);
		return true;

	}
	$(document).ready(function() {
		$(".myShow").click(function() {
			if ($(".myInfo").css("display") == "none") {
				$(".myInfo").css({"display": "inline","color": "#660000"});
			} else {
				$(".myInfo").css("display", "none");
			}
		});
	});
</script>
<body>
	<div class="container">
		<div class="left">
			<div id="accordion">
				<%
					Object result = request.getAttribute("result");
					Object myCourse = session.getAttribute("myCourse");
					if (result == null || myCourse == null) {
						out.print("<h1>无解<h1/>");
					} else {
						Map<Integer, String> indexMap = new HashMap<Integer, String>();
						indexMap.put(1, "一");
						indexMap.put(2, "二");
						indexMap.put(3, "三");
						indexMap.put(4, "四");
						indexMap.put(5, "五");
						indexMap.put(6, "六");
						indexMap.put(7, "日");
						ResultType ret = (ResultType) result;
						int lessonNum = ret.lessonNum;
						int needLessons = lessonNum * 7;
						boolean everyWeek[] = ret.everyWeek;
						int sheet[][] = ret.sheetInfor;
						int odd[][] = ret.oddSheet;
						int even[][] = ret.evenSheet;
						List<BaseTeacher> datas = ((startSortCourse) myCourse).datas;
						int classNum = ret.classNum;
				%>


				<h2>年级老师-结果</h2>
				<div>
					<table id="tab" border="1"
						class="table table-hover table-bordered">
						<tr id="rooms">
							<td></td>
							<%
								for (int i = 0; i < classNum; i++) {
							%>
							<td><%=String.format("%d 班", i + 1)%></td>
							<%
								}
							%>

						</tr>
						<%
							for (int j = 0; j < needLessons; j++) {
									if (j % lessonNum == 0) {
						%>
						<tr>
							<td rowspan="<%=(lessonNum + 1)%>">
								星期<%=indexMap.get(j / lessonNum + 1)%></td>
						</tr>
						<%
							}
						%>
						<tr>

							<%
								for (int i = 0; i < classNum; i++) {
											if (sheet[i][j] >= 0) {
												BaseTeacher bt = datas.get(sheet[i][j]);
												String f = "false";
												if (bt.headClass == i) {
													f = "true";
												}
							%>
							<td class="<%=String.format("%d %d", i, j)%>" draggable="true"
								ondragstart="drag(event)" ondrop="drop(event)"
								ondragover="allowDrop(event)" onclick="fixCell(event)"
								title="教师名: <%=bt.teacherName%>&#10课程名: <%=bt.courseName%>&#10班级数: <%=bt.perWeekClassNum%>&#10周课时数: <%=bt.perWeekTimeNum%>&#10班主任: <%=f%>&#10全周上课"><%=bt.teacherName%></td>
							<%
								} else {
												String temp = "";
												String title = "此课时为空";
												if (odd[i][j] >= 0) {
													BaseTeacher bt = datas.get(odd[i][j]);
													String f = "false";
													if (bt.headClass == i) {
														f = "true";
													}
													temp += String.format("%s<br/>", bt.teacherName);
													title = String.format(
															"教师名: %s&#10课程名: %s&#10班级数: %d&#10周课时数: %d&#10班主任: %s&#10单周上课&#10",
															bt.teacherName, bt.courseName, bt.perWeekClassNum, bt.perWeekTimeNum, f);
												}
												if (even[i][j] >= 0) {
													BaseTeacher bt = datas.get(even[i][j]);
													String f = "false";
													if (bt.headClass == i) {
														f = "true";
													}
													temp += String.format("%s", bt.teacherName);
													title += String.format(
															"&#10教师名: %s&#10课程名: %s&#10班级数: %d&#10周课时数: %d&#10班主任: %s&#10双周上课&#10",
															bt.teacherName, bt.courseName, bt.perWeekClassNum, bt.perWeekTimeNum, f);

												}
												if (temp != "" || (even[i][j] == -1 && odd[i][j] == -1 && sheet[i][j] == -1)) {
							%>
							<td class="<%=String.format("%d %d", i, j)%>" draggable="true"
								ondragstart="drag(event)" ondrop="drop(event)"
								ondragover="allowDrop(event)" onclick="fixCell(event)"
								title="<%=title%>"><%=temp%></td>

							<%
								} else {
							%>
							<td title="此课时不可安排课程"></td>
							<%
								}
							%>

							<%
								}
							%>


							<%
								}
							%>

							<%
								}
							%>
							<%
								}
							%>
						
					</table>
				</div>

				<form method='post' target='_blank' action="/sortCourse/change.action" onsubmit="return changeSubmit();">
				<input type="hidden" name="fixTable" class="myFixTable" />
				<input type="hidden" name="changeStr" class="myChangeStr" />				
				

				<input type="submit" class="btn btn-info btn-lg"
					style="aligen: center" id="btnConfirm" value="提交" />
					</form>
				<br />
				<br />
				<br />
				<br />
				<br />
			</div>
		</div>
		<div class="right">
			<div id="page">

				<div id="faqSection">
						<dt>
							<h3 class="myShow expand">&nbsp;tips&nbsp;</h3>
						</dt>
				</div>
				<div class="myInfo" style="display: none;">
				<br />
					鼠标停留查看详情
					<br />
					拖动以调整位置;
					<br />
					单击以固定位置.
					<br />
					<br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>