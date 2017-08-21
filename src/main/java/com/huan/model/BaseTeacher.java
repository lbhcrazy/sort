package com.huan.model;

import java.util.ArrayList;

public class BaseTeacher extends Teacher {
	public int courseIndex;
	public int teacherIndex;
	public ArrayList<Integer>classes;
	public int headClass=-1;
	public BaseTeacher(String teacherName, String courseName, int perWeekClassNum, int perWeekTimeNum, boolean isHead,boolean isNext,int teacherIndex) {
		super(teacherName, courseName, perWeekClassNum, perWeekTimeNum, isHead,isNext);
		this.teacherIndex=teacherIndex;
		this.classes=new ArrayList<>();
	}
	
	
	
}
