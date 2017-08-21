package com.huan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huan.model.Teacher;
import com.huan.model.TeacherForm;
import com.huan.sort.util.startSortCourse;
import com.huan.teacher.service.ITeacherService;
import com.huan.teacher.service.imp.ChangeService;
import com.huan.teacher.service.imp.DealService;

@Controller
public class SortContoller {

	@Autowired
	private ITeacherService teacherService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	DealService dealService;

	@Autowired
	ChangeService changeService;

	@RequestMapping(value = "/input.action", method = RequestMethod.GET)
	public String input() {
		return "input";
	}

	@RequestMapping(value = "/test.action", method = RequestMethod.POST)
	public String test() {
		try {
			String fixTable = request.getParameter("fixTable");
			String changeStr = request.getParameter("changeStr");
			startSortCourse rt = (startSortCourse) request.getSession().getAttribute("myCourse");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return "result";
	}

	@RequestMapping(value = "/deal.action", method = RequestMethod.POST)
	public String deal(TeacherForm form) throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			dealService.excute(form.getTeachers());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "result";
	}

	@RequestMapping(value = "/testdeal.action", method = RequestMethod.POST)
	public void testdeal() {
		System.out.println("test start!");
		try {

			String firstRet[] = request.getParameterValues("first");
			for (String s : firstRet) {
				System.out.print(s + " ");
			}
			System.out.println();
			String secondRet[] = request.getParameterValues("second");
			System.out.println("num:= " + secondRet.length);
			for (String s : secondRet) {
				System.out.print(s + " ");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@RequestMapping(value = "/simulator.action", method = RequestMethod.GET)
	public String simulator() {
		List<Teacher> ts = this.teacherService.findAll();
		request.setAttribute("teachers", ts);
		return "simulator";
	}

	@RequestMapping(value = "/change.action", method = RequestMethod.POST)
	public String change() throws IOException {
		
		try {
			startSortCourse rt = (startSortCourse) request.getSession().getAttribute("myCourse");
			changeService.excute(rt, request);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return "result";
	}

}
