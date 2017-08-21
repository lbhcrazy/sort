package com.huan.sort.util;

import java.util.ArrayList;

import com.huan.definition.Position;

public class ProcessUtil {
	public ArrayList<Integer>classes;
	public ArrayList<Position>arrangeCells;
	public ArrayList<Integer>weekY;
	public ArrayList<Position>conflictCells;
	public ArrayList<Position>connectCells;
	public ProcessUtil(){
		classes=new ArrayList<Integer>();
		arrangeCells=new ArrayList<Position>();
		weekY=new ArrayList<Integer>();
		conflictCells=new ArrayList<Position>();
		connectCells=new ArrayList<>();
	}

}
