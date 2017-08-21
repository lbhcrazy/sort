package com.huan.teacher.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.model.Teacher;
import com.huan.teacher.dao.ITeacherDao;
import com.huan.teacher.service.ITeacherService;

@Service("teacherService")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class TeacherServiceImp implements ITeacherService {

	@Autowired
	private ITeacherDao teacherDao;
	@Override
	public List<Teacher> findAll() {
		// TODO Auto-generated method stub
		return teacherDao.findAll();
	}
	@Override
	public Teacher selectByid(int id) {
		// TODO Auto-generated method stub
		return teacherDao.selectByid(id);
	}
}
