package com.huan.teacher.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huan.althgorim.SA;
import com.huan.definition.ResultType;
import com.huan.exception.Myexception;
import com.huan.model.BaseTeacher;
import com.huan.model.HalfTeacher;
import com.huan.model.Teacher;
import com.huan.model.WholeTeacher;
import com.huan.sort.util.startSortCourse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Component
public class DealService {

	@Autowired
	HttpServletRequest request;

	public void excute(List<Teacher> form) throws Myexception {

		int classNum = Integer.parseInt(request.getParameter("classNum"));
		int morning = Integer.parseInt(request.getParameter("morning"));
		int afternoon = Integer.parseInt(request.getParameter("afternoon"));
		int saturday=0;
		try {
			 saturday = Integer.parseInt(request.getParameter("saturday"));
		} catch (Exception e) {
		}
		int sunday=0;
		try {
			sunday= Integer.parseInt(request.getParameter("sunday"));
		} catch (Exception e) {
		}
		String allowMorning_s = request.getParameter("allowMorning");
		boolean allowMorning = true;
		if (allowMorning_s == null||allowMorning_s=="false"||allowMorning_s=="off") {
			allowMorning = false;
		}
		int i = 0;
		List<BaseTeacher>tsList=new ArrayList<>();
		for (Teacher each : form) {
			BaseTeacher temp = null;
			if (!each.IsNext) {
				temp = new WholeTeacher(each.teacherName, each.courseName, each.perWeekClassNum, each.perWeekTimeNum,
						each.IsHead, i + 1);
			} else {
				temp = new HalfTeacher(each.teacherName, each.courseName, each.perWeekClassNum, each.perWeekTimeNum,
						each.IsHead, i + 1);
			}

			tsList.add(temp);
		}

		startSortCourse myCourse = new startSortCourse(classNum, morning, afternoon, saturday, sunday, tsList,
				allowMorning);
		myCourse.paramCheck();
		myCourse.allocateClasses();
		ResultType ret = myCourse.allocateLessones();
		
		request.getSession().setAttribute("myCourse", myCourse);
		request.setAttribute("result", ret);
		
		try{
			File filename=new File("C:\\Users\\Administrator\\Desktop\\data2.txt");
			if(!filename.exists()){
				filename.createNewFile();			 
			}
			File filename1=new File("C:\\Users\\Administrator\\Desktop\\wen2.txt");
			if(!filename1.exists()){
				filename1.createNewFile();			 
			}
			FileWriter out=new FileWriter(filename);
			FileWriter out1=new FileWriter(filename1);
			for(int p=0;p<myCourse.sa.tempCostflu.size();p++){
				out.write(myCourse.sa.tempCostflu.get(p)+"&");
			}
			for(int z=0;z<myCourse.sa.allT.size();z++){
				out1.write(myCourse.sa.allT.get(z).toString().substring(0, 4)+"&"+"\r\n");
			}
			
			 out.flush(); // 把缓存区内容压入文件  
             out.close(); // 最后记得关闭文件  
             out1.flush(); // 把缓存区内容压入文件  
             out1.close(); // 最后记得关闭文件  
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
