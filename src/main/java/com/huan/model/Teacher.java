 package com.huan.model;


public class Teacher {
	public String teacherName;
	public String courseName;
	public int perWeekClassNum;
	public int perWeekTimeNum;
	public boolean IsHead;
	public boolean IsNext;
	public Teacher() {
	}
	public Teacher(String teacherName, String courseName, int perWeekClassNum, int perWeekTimeNum, boolean isHead, boolean isNext) {
		this.teacherName = teacherName;
		this.courseName = courseName;
		this.perWeekClassNum = perWeekClassNum;
		this.perWeekTimeNum = perWeekTimeNum;
		this.IsHead = isHead;
		this.IsNext=isNext;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getPerWeekClassNum() {
		return perWeekClassNum;
	}
	public void setPerWeekClassNum(int perWeekClassNum) {
		this.perWeekClassNum = perWeekClassNum;
	}
	public int getPerWeekTimeNum() {
		return perWeekTimeNum;
	}
	public void setPerWeekTimeNum(int perWeekTimeNum) {
		this.perWeekTimeNum = perWeekTimeNum;
	}
	public boolean isIsHead() {
		return IsHead;
	}
	public void setIsHead(boolean isHead) {
		IsHead = isHead;
	}

	public boolean isIsNext() {
		return IsNext;
	}

	public void setIsNext(boolean isNext) {
		IsNext = isNext;
	}
	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + ", courseName=" + courseName + ", perWeekClassNum="
				+ perWeekClassNum + ", perWeekTimeNum=" + perWeekTimeNum + ", IsHead=" + IsHead + ", IsNext=" + IsNext
				+ "]";
	}
	
	
	
}
