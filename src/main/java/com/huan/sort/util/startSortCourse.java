package com.huan.sort.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.huan.althgorim.SA;
import com.huan.definition.ConstantVal;
import com.huan.definition.Position;
import com.huan.definition.ResultType;
import com.huan.exception.Myexception;
import com.huan.model.BaseTeacher;
import com.huan.model.HalfTeacher;
import com.huan.model.Teacher;


public class startSortCourse {
	// 老师0~49 班级1~15 课时1~49
	// ====== allocate teacher-class information =======
	public static final int daysPerWeek = 7;
	public ArrayList<String> recordCourses = new ArrayList<String>();
	public ArrayList<Integer[]> definedCost = new ArrayList<Integer[]>();
	public ArrayList<ArrayList<Integer>> classIncludeTeacher = new ArrayList<ArrayList<Integer>>();
	public int diffCourseNums;
	public int classNum;
	public int morning;
	public int afternoon;
	public int saturday;
	public int sunday;

	public int lessonNum;

	public List<BaseTeacher> datas;
	public int teacherNum;
	public int needLessons;
	public boolean allowMorning;
	public boolean[] everyWeek;
	public SA sa = null;

	public startSortCourse(int classNum, int morning, int afternoon, int saturday, int sunday, List<BaseTeacher> datas,
			boolean allowMorning) {
		super();
		this.classNum = classNum;
		this.morning = morning;
		this.afternoon = afternoon;
		this.saturday = saturday;
		this.sunday = sunday;
		this.datas = datas;
		this.lessonNum = morning + afternoon;
		this.teacherNum = datas.size();
		this.needLessons = 7 * lessonNum;
		this.allowMorning = allowMorning;
		everyWeek = new boolean[7 * lessonNum];
		for (int i = 0; i < 5 * lessonNum + saturday; i++) {
			everyWeek[i] = false;
		}
		for (int i = 5 * lessonNum + saturday; i < 6 * lessonNum; i++) {
			everyWeek[i] = true;
		}
		for (int i = 6 * lessonNum; i < 6 * lessonNum + sunday; i++) {
			everyWeek[i] = false;
		}
		for (int i = 6 * lessonNum + sunday; i < 7 * lessonNum; i++) {
			everyWeek[i] = true;
		}

		// for (int i = 0; i < classNum; i++) {
		// for (int j = 0; j < lessonNum * daysPerWeek; j++) {
		// Position tempPosition = new Position();
		// tempPosition.classX = i + 1;
		// tempPosition.timeY = j + 1;
		// globalPositions.add(tempPosition);
		//
		// }
		//
		// }
	}

	// ====== allocate course-position information ======
	// public ArrayList<Position> globalPositions = new
	// ArrayList<Position>(classNum * lessonNum * daysPerWeek);

	public startSortCourse() {
		teacherNum = datas.size();

		// for (int i = 0; i < classNum; i++) {
		// for (int j = 0; j < lessonNum * daysPerWeek; j++) {
		// Position tempPosition = new Position();
		// tempPosition.classX = i + 1;
		// tempPosition.timeY = j + 1;
		// globalPositions.add(tempPosition);
		//
		// }
		//
		// }

		// initBlank();
	}

	public void initBlank() {
		// 周六下午、周日不上课！
		// int freeDay = (6 - 1) * 49 + 4;
		// System.out.println(globalPositions.size());
		// for (int i = freeDay; i < classNum * lessonNum * daysPerWeek; i++) {
		// globalPositions.get(i).need = false;
		// globalPositions.get(i).filled = true;
		// }

		// int freeDay=5*

		// for (int i = 0; i < teacherNum; i++) {
		// for (int j = 0; j < daysPerWeek * lessonNum; j++) {
		// datas.get(i).teacherY.add(true);
		// }
		// }

		Integer c_chinese[] = new Integer[lessonNum];
		Integer c_math[] = new Integer[lessonNum];
		Integer c_english[] = new Integer[lessonNum];
		Integer c_arts[] = new Integer[lessonNum];
		Integer c_science[] = new Integer[lessonNum];
		Integer c_sport[] = new Integer[lessonNum];
		Integer c_empty[] = new Integer[lessonNum];
		Integer c_other[] = new Integer[lessonNum];

		for (int i = 0; i < morning; i++) {
			c_chinese[i] = morning - i;

			c_math[i] = 1;

			c_english[i] = morning - i;

			c_sport[i] = 60;

			c_empty[i] = Integer.MAX_VALUE;

			c_arts[i] = morning - i + 2;

			c_science[i] = 1;

			c_other[i] = morning - i + 1;

		} // morning
		if (allowMorning)
			c_empty[morning - 1] = 1;

		for (int i = morning; i < afternoon / 2 + morning; i++) {

			c_chinese[i] = i - morning + 1;

			c_math[i] = afternoon / 2 + morning + 1 - i;

			c_english[i] = i - 2;

			c_sport[i] = 60;

			c_empty[i] = Integer.MAX_VALUE;

			c_arts[i] = i - 1;

			c_science[i] = afternoon / 2 + morning + 1 - i;

			c_other[i] = i;

		} // half of afternoon

		for (int i = afternoon / 2 + morning; i < lessonNum; i++) {

			c_chinese[i] = afternoon / 2 + morning + 2 - i;

			c_math[i] = i - (afternoon / 2 + morning - 1);

			c_english[i] = afternoon / 2 + morning + 2 - i;
			if (i == lessonNum - 1 || i == lessonNum - 2)
				c_sport[i] = 1;
			else {
				c_sport[i] = 60;
			}

			c_empty[i] = Integer.MAX_VALUE;

			c_arts[i] = afternoon / 2 + morning + 2 - i;

			c_science[i] = i - (afternoon / 2 + morning - 1);

			c_other[i] = 1;

		} // half of afternoon
		c_empty[lessonNum - 1] = 1;

		definedCost.add(c_chinese);// 0
		definedCost.add(c_math);// 1
		definedCost.add(c_english);// 2
		definedCost.add(c_sport);// 3
		definedCost.add(c_arts);// 4
		definedCost.add(c_science);// 5
		definedCost.add(c_empty);// 6
		definedCost.add(c_other);// 7
		// System.out.println("===========cost===");
		// for (int i = 0; i < 7; i++) {
		//
		// for (int j = 0; j < definedCost.get(i).length; j++) {
		// System.out.print(definedCost.get(i)[j] + " ");
		// }
		// System.out.println();
		// }

	}

	// <excute>
	public void allocateClasses() {

		diffCourseNums = diffCourseNum();
		allocateHead(diffCourseNums, classNum);

	}
	// </excute>

	public int diffCourseNum() {
		int count = 1;
		boolean sameFlag = false;
		recordCourses.add(datas.get(0).courseName);
		for (int i = 0; i < teacherNum; i++) {
			sameFlag = false;
			for (int j = 0; j < recordCourses.size(); j++) {
				if (datas.get(i).courseName.equals(recordCourses.get(j))) {
					sameFlag = true;
					break;

				}
			}
			if (!sameFlag) {
				recordCourses.add(datas.get(i).courseName);
				count++;
			}
		}
		return count;
	}

	public void allocateHead(int t_diffCourseNums, int t_classNum) {
		// allocateHead
		for (int i = 0, j = 0; i < t_classNum; i++) {
			for (; j < teacherNum;) {
				if (datas.get(j).IsHead) {
					datas.get(j).headClass=i;
					datas.get(j).classes.add(i);
					j++;
					break;
				} else {
					j++;
				}

			}

		}

		for (int k = 0; k < t_diffCourseNums; k++) {
			ArrayList<Integer> randomList = new ArrayList<Integer>();
			for (int i = 0; i < classNum; i++) {
				randomList.add(i);
			}
			Collections.shuffle(randomList);
			ArrayList<Integer> indexNum = new ArrayList<Integer>();
			for (int i = 0; i < teacherNum; i++) {
				if (datas.get(i).courseName.equals(recordCourses.get(k))) {
					indexNum.add(i);
				}
			}
			int headIndex[] = new int[classNum];
			for (int i = 0, index = 0; i < indexNum.size(); i++) {

				if (datas.get(indexNum.get(i)).IsHead) {
					headIndex[index++] = indexNum.get(i);
				}

			}
			int positive = 0;
			for (int t = 0; t < classNum; t++) {
				if (headIndex[t] != 0) {
					positive++;
				} else {
					break;
				}

			}

			if (positive == 0) {
				for (int i = 0, n = 0; i < indexNum.size(); i++) {
					for (int j = 0; j < datas.get(indexNum.get(i)).perWeekClassNum; j++) {
						datas.get(indexNum.get(i)).classes.add(randomList.get(n++));
					}
				}

			} else {
				Integer compan = new Integer(0);
				for (int i = 0; i < positive; i++) {
					compan = datas.get(headIndex[i]).classes.get(0);
					randomList.remove(compan);
				}
				for (int i = 0, n = 0; i < indexNum.size(); i++) {
					int loop = datas.get(indexNum.get(i)).perWeekClassNum;
					if (datas.get(indexNum.get(i)).IsHead) {
						loop--;
					}
					for (int j = 0; j < loop; j++) {
						datas.get(indexNum.get(i)).classes.add(randomList.get(n++));
					}
				}
			}

		}

	}

	public void sortTeacherClassByIncrease() {

		for (int i = 0; i < teacherNum; i++) {
			for (int m = 0; m < datas.get(i).classes.size() - 1; m++) {
				for (int n = m + 1; n < datas.get(i).classes.size(); n++) {
					if (datas.get(i).classes.get(m) > datas.get(i).classes.get(n)) {
						int temp = datas.get(i).classes.get(m);
						datas.get(i).classes.set(m, datas.get(i).classes.get(n));
						datas.get(i).classes.set(n, temp);
					}
				}
			}
		}

	}

	public void rankTeacher() {
		int t = 0;
		for (int i = 0; i < teacherNum; i++) {
			if (datas.get(i).courseIndex == ConstantVal.COURSE_EMPTY && t != i) {
				BaseTeacher temp = datas.get(i);
				datas.set(i, datas.get(t));
				datas.set(t, temp);
				t++;
			}
		}
		for (int i = t; i < teacherNum; i++) {
			if (datas.get(i).courseIndex == ConstantVal.COURSE_SPORT && t != i) {
				BaseTeacher temp = datas.get(i);
				datas.set(i, datas.get(t));
				datas.set(t, temp);
				t++;
			}
		}

		for (int i = t; i < teacherNum - 1; i++) {
			for (int j = i + 1; j < teacherNum; j++) {
				if (datas.get(i).perWeekClassNum * datas.get(i).perWeekTimeNum < datas.get(j).perWeekClassNum
						* datas.get(j).perWeekTimeNum) {
					BaseTeacher temp = datas.get(i);
					datas.set(i, datas.get(j));
					datas.set(j, temp);
				}

			}

		}

	}

	public void everyClassIncludeTeacher() {

		// classIncludeTeacher
		ArrayList<Integer> temp = new ArrayList<>();

		for (int i = 0; i < classNum; i++) {
			for (int m = 0; m < datas.size(); m++) {
				if (datas.get(m).classes.contains(i)) {
					temp.add(m);
				}
			}
			classIncludeTeacher.add(new ArrayList<>(temp));
			temp.clear();
		}

		// System.out.println("========classinfor======");
		// for (int i = 0; i < classIncludeTeacher.size(); i++) {
		// for (int m = 0; m < classIncludeTeacher.get(i).size(); m++) {
		// System.out.print(classIncludeTeacher.get(i).get(m) + " ");
		// }
		// System.out.println();
		// }

	}

	// <excute>
	public ResultType allocateLessones() throws Myexception {

		// sortTeacherClassByIncrease();
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).courseIndex = searchCharacter(datas.get(i).courseName);
		}

		rankTeacher();

		everyClassIncludeTeacher();
		initBlank();

		sa = new SA(20, 40, 45.0, 0.95, datas, definedCost, classIncludeTeacher, needLessons,  morning,  afternoon,saturday,sunday, classNum,
				everyWeek,allowMorning);
		sa.arrangeHalf();
		sa.initWhole();
		// printConflict(sa.datas);
		return sa.solve(true);
		
		

	}
	// </excute>

	public ResultType changeAndDeal() throws Myexception {
		return sa.solve(false);
	}

//	public void printConflict(ArrayList<WholeTeacher> datas) {
//		for (int i = 0; i < teacherNum; i++) {
//			System.out.print("name:" + datas.get(i).teacherName);
//			System.out.print(" course:" + datas.get(i).courseName);
//			System.out.print(" totalLessonsL:" + datas.get(i).perWeekClassNum * datas.get(i).perWeekTimeNum);
//			System.out.println();
//			System.out.println("normal");
//			for (int k = 0; k < datas.get(i).arrangeCells.size(); k++) {
//				Position tPosition = datas.get(i).arrangeCells.get(k);
//				System.out.print("(" + tPosition.classX + "," + tPosition.timeY + ")");
//			}
//			System.out.println();
//			System.out.print("conflict: ");
//			for (int k = 0; k < datas.get(i).conflictCells.size(); k++) {
//				Position tPosition = datas.get(i).conflictCells.get(k);
//				System.out.print("(" + tPosition.classX + "," + tPosition.timeY + ")");
//			}
//			System.out.println();
//			System.out.print("connect: ");
//			for (int k = 0; k < datas.get(i).connectCells.size(); k++) {
//				Position tPosition = datas.get(i).connectCells.get(k);
//				System.out.print("(" + tPosition.classX + "," + tPosition.timeY + ")");
//			}
//			System.out.println();
//			int ttttt = datas.get(i).arrangeCells.size() + datas.get(i).conflictCells.size();
//			if (ttttt == datas.get(i).perWeekClassNum * datas.get(i).perWeekTimeNum)
//				System.out.println("this teacher arrange" + ttttt + "lessons");
//			else {
//				System.out.println("this teacher arrange" + ttttt + "lessons");
//			}
//			System.out.println();
//		}
//
//	}

	public int searchCharacter(String courseName) {
		if (courseName.equals("语文"))
			return ConstantVal.COURSE_CHINESE;
		else if (courseName.equals("数学"))
			return ConstantVal.COURSE_MATH;
		else if (courseName.equals("英语"))
			return ConstantVal.COURSE_ENGLISH;
		else if (courseName.equals("体育"))
			return ConstantVal.COURSE_SPORT;
		else if (courseName.equals("政治") || courseName.equals("历史") || courseName.equals("地理"))
			return ConstantVal.COURSE_ARTS;
		else if (courseName.equals("物理") || courseName.equals("化学") || courseName.equals("生物"))
			return ConstantVal.COURSE_SCIENCE;
		else if (courseName.equals(""))
			return ConstantVal.COURSE_EMPTY;
		else
			return ConstantVal.COURSE_OTHER;

	}

	public int position2Index(int x, int y) {
		return (x - 1) * daysPerWeek * lessonNum + y - 1;
	}

	public Position index2Position(int a) {

		int x = a / (daysPerWeek * lessonNum) + 1;
		int y = a % (daysPerWeek * lessonNum);
		return new Position(x, y);

	}

	public void paramCheck() throws Myexception {
		int tls_double = 0;// 两周课时总数
		for (Teacher data : datas) {
			if (data instanceof HalfTeacher)
				tls_double += data.perWeekClassNum * data.perWeekTimeNum;
			else {
				tls_double += 2 * data.perWeekClassNum * data.perWeekTimeNum;
			}
		}
		if (tls_double % classNum != 0) {
			throw new Myexception("总教师课时数除以班级数目不等于零！");
		}
		int oneWeek = 5 * (morning + afternoon) + saturday + sunday;
		int remain = oneWeek * classNum * 2 - tls_double;
		if (remain < 0) {
			throw new Myexception("所有老师周课时超过每周可安排课时");
		}
		// int supplement = remain / classNum;
		// if (supplement > 0) {
		// for (int i = 0; i < classNum; i++) {
		// datas.add(new allData("", "", 1, supplement, false, false, -1));
		// teacherNum++;
		// }
		// }

	}

}
