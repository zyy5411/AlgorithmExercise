package com.jp.co.wap.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jp.co.wap.exam.lib.Interval;

import junit.framework.TestCase;
import util.Timer;

public class Problem1Test extends TestCase {

	public void testGetMaxIntervalOverlapCount() {

		Problem1 p = new Problem1();
		//		Interval interval1 = new Interval("08:00", "12:30");
		//		Interval interval2 = new Interval("06:00", "09:00");
		//		Interval interval3 = new Interval("11:00", "13:00");
		//		Interval interval4 = new Interval("7:00", "12:40");
		//		Interval interval5 = new Interval("12:41", "13:40");
		//		List<Interval> figure1 = Arrays.asList(interval4, interval5);
		//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3,
		//				interval4);
		List<Interval> figure1 = new ArrayList<Interval>();
		int n = 100000;
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
		System.out.println(p.getMaxIntervalOverlapCount(figure1));
		timer.clickAndPrintInterval("·½°¸Ò»");
		System.out.println(p.getMaxIntervalOverlapCount(null));
	}
}
