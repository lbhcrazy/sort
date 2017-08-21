package com.huan.model;

import com.huan.sort.util.ProcessUtil;

public class WholeTeacher extends BaseTeacher{
	
	public ProcessUtil wholePro;
	
	public WholeTeacher(String teacherName, String courseName, int perWeekClassNum, int perWeekTimeNum, boolean isHead,int teacherIndex) {
		super(teacherName, courseName, perWeekClassNum, perWeekTimeNum, isHead,false,teacherIndex);
		wholePro=new ProcessUtil();
	}
	
	
	
}
