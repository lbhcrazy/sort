package com.huan.definition;

import java.util.ArrayList;
import java.util.Map;

import com.huan.model.WholeTeacher;


public class ResultType {

	public ArrayList<WholeTeacher> datas;
	public int[][] sheetInfor;
	public int classNum;
	public int lessonNum;
	public boolean everyWeek[];
	public static final double conflictCost=1000.0;
	public static final double connectCost=50.0;
	public double sumCost;
	public int [][]oddSheet;
	public int [][]evenSheet;
	
	public ResultType(ArrayList<WholeTeacher> datas, int[][] sheetInfor) {
		this.datas =new ArrayList<>(datas);
		this.sheetInfor=new int[sheetInfor.length][sheetInfor[0].length];
		for(int i=0;i<sheetInfor.length;i++){
			for(int j=0;j<sheetInfor[0].length;j++){
				this.sheetInfor[i][j] = sheetInfor[i][j] ;
			}
		}
		
	}
	public ResultType(){
		
	}
	
	
	public ResultType(int classNum, int lessonNum, boolean[] everyWeek) {
		super();
		this.classNum = classNum;
		this.lessonNum = lessonNum;
		this.everyWeek = everyWeek;
	}
	public double getCost(ArrayList<Integer[]> definedCost){
		double sum=0,p1=0,p2=0,p3=0;
		for(int i=0;i<datas.size();i++){
			Integer certainCost[] = definedCost.get(datas.get(i).courseIndex);
			for(int j=0;j<datas.get(i).wholePro.arrangeCells.size();j++){
				Position temp=datas.get(i).wholePro.arrangeCells.get(j);
				p1+=certainCost[temp.timeY%lessonNum];
			}
			
			p2+=conflictCost*datas.get(i).wholePro.conflictCells.size();
			p3+=connectCost*datas.get(i).wholePro.connectCells.size()/2;
			
		}
				
				
				
		sum=p1+p2+p3;	
		sumCost=sum;
		return sum;
		
	}
	
	public void indexTransform(int origin[][],Map<Integer, Integer>trans,int type){
		int target[][]=null;
		int row=origin.length,col=origin[0].length;
		switch (type) {
		case ConstantVal.PROCESS_WHOLE:
			target=sheetInfor;
			break;
		case ConstantVal.PROCESS_EVEN:
			evenSheet=new int[row][col];
			target=evenSheet;
			break;
		case ConstantVal.PROCESS_ODD:
			oddSheet=new int[row][col];
			target=oddSheet;
			break;
		default:
			break;
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(origin[i][j]>=0)
					target[i][j]=trans.get(origin[i][j]);
				else 
					target[i][j]=origin[i][j];
			}
		}
	}
	
	
}
