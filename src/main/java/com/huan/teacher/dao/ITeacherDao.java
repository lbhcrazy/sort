package com.huan.teacher.dao;

import java.util.List;

import com.huan.model.Teacher;

public interface ITeacherDao {
	List<Teacher>findAll();

	Teacher selectByid(int i);
}
