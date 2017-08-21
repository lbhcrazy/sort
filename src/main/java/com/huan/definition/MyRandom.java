package com.huan.definition;

import java.util.Random;

public class MyRandom {
	
	private static Random myRandom=null;
	private MyRandom(){
		
	}
	public static Random getInstance(){
		
		if(myRandom==null){
			myRandom=new Random();
		}
		return myRandom;
	}

}
