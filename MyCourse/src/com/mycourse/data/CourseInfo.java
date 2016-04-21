package com.mycourse.data;

import java.io.Serializable;



public class CourseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//�γ���
	public String name;
	//�Ͽν���
	public String adress;
	//��ʦ
	public String teacher;
	//����
	public int week;
	//�γ̹�����
	public int Period;
	//�γ��ڵڼ��ڿ�ʼ
	public int start;
	
	public CourseInfo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name  �γ���
	 * @param adress  ����
	 * @param week  ����
	 * @param numOfPeriod һ������
	 * @param period  ��ʼʱ��
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
