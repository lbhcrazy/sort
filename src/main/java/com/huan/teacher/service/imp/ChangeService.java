package com.huan.teacher.service.imp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.huan.althgorim.SA;
import com.huan.course.util.ConvertUtil;
import com.huan.definition.ResultType;
import com.huan.exception.Myexception;
import com.huan.sort.util.startSortCourse;

@Service
public class ChangeService {

	public void excute(startSortCourse myCourse,HttpServletRequest request) throws Myexception{
		int lessonNum=myCourse.lessonNum;
		int classNum=myCourse.classNum;
		int table[][]=new int [classNum][lessonNum*7];
		String fixTable=request.getParameter("fixTable");
		String changeStr=request.getParameter("changeStr");
		String mic[]=fixTable.split(",");
		int micNum[]=ConvertUtil.parsIntArray(mic);
		int count=0;
		if(micNum.length!=0){
			for(int i=0;i<classNum;i++){
				for(int j=0;j<lessonNum*7;j++){
					table[i][j]=micNum[count++];
				}
			}
		}
		String exchange1[]=changeStr.split(",");
		System.out.println(exchange1.length);
		count=0;
		String exchange[]=new String[classNum];
		changeStr=","+changeStr+",";
		int index1=0,index2=0;
		while(count<classNum){
			index1=index2;
			index2=changeStr.indexOf(",",index1+1);
			if(index1+1==index2){
				exchange[count++]="";
			}else{
				exchange[count++]=changeStr.substring(index1+1,index2-1);
			}
			
		}
		SA sa=myCourse.sa;
		sa.changeAttr(table,exchange,sa.sheetInfor);
		ResultType ret=myCourse.changeAndDeal();
		request.getSession().setAttribute("myCourse", myCourse);
		request.setAttribute("result", ret);
	}
}
