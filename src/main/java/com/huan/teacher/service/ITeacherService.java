package com.huan.teacher.service;

import java.util.List;

import com.huan.model.Teacher;

public interface ITeacherService {
	List<Teacher>findAll();
	Teacher selectByid(int id);
}
