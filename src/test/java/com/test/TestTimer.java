package com.test;

import java.util.Timer;
import java.util.TimerTask;
//可以搜索Spring TimerTask 来设置
public class TestTimer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestTimer(4);

	}
	public TestTimer(int Second) {
		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(), 0, Second*1000);
		
	}
	private class MyTimeTask extends TimerTask{
		@Override
		public void run() {
			System.out.println("timetask is run!");
		}
	}

}
