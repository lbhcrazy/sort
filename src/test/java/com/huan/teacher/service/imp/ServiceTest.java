package com.huan.teacher.service.imp;

import org.junit.Test;

public class ServiceTest {

	public int a = 7;

	@Test
	public void testQueryAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(ServiceTest.class);
		ServiceTest myTest = new ServiceTest();
		System.out.println(myTest.getClass());
		Class<ServiceTest> test2 = (Class<ServiceTest>) Class.forName("com.huan.teacher.service.imp.ServiceTest");
		ServiceTest test3 = test2.newInstance();
		System.out.println(test3.a);
	}
	
	@Test
	public void searchIndex(){
		String changeStr=",,,0=2&,,,,,,,,,,,0=4&";
		String exchange[]=new String[15];
		changeStr=","+changeStr+",";
		int index1=0,index2=0,count=0;
		while(count<15){
			index1=index2;
			index2=changeStr.indexOf(",",index1+1);
			if(index1+1==index2){
				exchange[count++]="";
			}else{
				exchange[count++]=changeStr.substring(index1+1,index2-1);
			}
			
		}
		
		for(int i=0;i<exchange.length;i++){
			if(exchange[i].length()!=0){
				String oper[]=exchange[i].split("&");
				System.out.println(oper.length);
			}
		}
		
		System.out.println("fu");
	}

}