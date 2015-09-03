package com.jp.co.wap.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jp.co.wap.exam.lib.Interval;

import junit.framework.TestCase;
import util.Timer;

public class Problem2Test extends TestCase {
	Problem2WithList pWithList = new Problem2WithList();
	Problem2 p2 = new Problem2();

	public void testCalculateTheMaxWorkingTime() {

		List<Interval> figure1 = new ArrayList<Interval>();
		//		Interval interval1 = new Interval("06:00", "08:30");
		//		Interval interval2 = new Interval("09:00", "11:00");
		//		Interval interval3 = new Interval("12:30", "14:00");
		//		Interval interval4 = new Interval("8:00", "09:00");
		//		Interval interval5 = new Interval("10:30", "14:00");
		//		Interval interval6 = new Interval("09:00", "11:30");
		//		Interval interval7 = new Interval("13:30", "24:00");
		//		Interval interval8 = new Interval("00:00", "05:00");
		//		Interval interval9 = new Interval("04:00", "07:00");
		//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3,
		//				interval4, interval5, interval6, interval7, interval8,
		//				interval9);
		int n = 1000;
		Random r = new Random();
		while (n-- > 0) {
			int hour = r.nextInt(24);
			int minite = r.nextInt(60);
			String startTime = String.format("%02d:", hour)
					+ String.format("%02d", minite);
			hour = r.nextInt(24 - hour) + hour;
			minite = r.nextInt(60);
			String endTime = String.format("%02d:", hour)
					+ String.format("%02d", minite);

			figure1.add(new Interval(startTime, endTime));
		}

		Timer timer = Timer.getInstance();
		timer.start();
		int t1 = new CopyOfProblem2().getMaxWorkingTime2(figure1);
		timer.clickAndPrintTotalTimeCost("方案直接排序");
		System.out.println(t1);
		timer.start();
		int t2 = pWithList.getMaxWorkingTime(figure1);
		timer.clickAndPrintTotalTimeCost("方案用辅助数组");
		System.out.println(t2);
		timer.start();
		int t3 = p2.getMaxWorkingTime(figure1);
		timer.clickAndPrintTotalTimeCost("方案endLink");
		System.out.println(t3);
		System.out.println(p2.getMaxWorkingTime(null));
	}

}
