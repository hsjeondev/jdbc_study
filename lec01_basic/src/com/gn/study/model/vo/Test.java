package com.gn.study.model.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Test {
	private int testNo;
	private String testName;
	private LocalDateTime testDate;
	
	public Test() {}
	
	public Test(int testNo, String testName, LocalDateTime testDate) {
		this.testNo = testNo;
		this.testName = testName;
		this.testDate = testDate;
	}
	
	public int getTestNo() {
		return testNo;
	}

	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}


	
	public String changeDate() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(testDate.getYear(), testDate.getMonthValue() - 1, testDate.getDayOfMonth(),
                     testDate.getHour(), testDate.getMinute(), testDate.getSecond());
        
		Date date = calendar.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
		
		return sdf.format(date);
	}
	
	@Override
	public String toString() {
		return "번호:" +testNo+", 이름:"+testName+", 등록일:"+testDate;
	}

}
