package com.huan.model;


import com.huan.sort.util.ProcessUtil;

public class HalfTeacher extends BaseTeacher {

	public ProcessUtil oddUtil;
	public ProcessUtil evenUtil;

	public HalfTeacher(String teacherName, String courseName, int perWeekClassNum, int perWeekTimeNum, boolean isHead,int teacherIndex) {
		super(teacherName, courseName, perWeekClassNum, perWeekTimeNum, isHead,true,teacherIndex);
		oddUtil=new ProcessUtil();
		evenUtil=new ProcessUtil();
	}
	

}
