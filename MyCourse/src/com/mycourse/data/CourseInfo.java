package com.mycourse.data;

import java.io.Serializable;



public class CourseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//课程名
	public String name;
	//上课教室
	public String adress;
	//教师
	public String teacher;
	//周数
	public int week;
	//课程共几节
	public int Period;
	//课程在第几节开始
	public int start;
	
	public CourseInfo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name  课程名
	 * @param adress  教室
	 * @param week  周数
	 * @param numOfPeriod 一共几节
	 * @param period  开始时间
	 */
	public CourseInfo(String name,String adress,int week ,int Period,int start)
	{
		super();
		this.name = name;
		this.adress = adress;
		this.week = week;
		this.Period = Period;
		this.start = start;
	}
			
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getPeriod() {
		return Period;
	}
	public void setPeriod(int Period) {
		this.Period = Period;
	}
	public int getstart() {
		return start;
	}
	public void setstart(int start) {
		this.start = start;
	}
	

}
