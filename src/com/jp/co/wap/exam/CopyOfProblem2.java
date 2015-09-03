package com.jp.co.wap.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.jp.co.wap.exam.lib.Interval;

import util.Timer;

public class CopyOfProblem2 {

	final int MAXHOUR = 24;
	final int MAXMINUTE = 60;

	/**
	 * use assistant array to sort the interval by start time.
	 * @param intervals
	 * @return the max working time
	 */
	public int getMaxWorkingTime(List<Interval> intervals) {
		// use assistant array to sort the interval by start time.
		EndingTime[] workingTime = new EndingTime[MAXHOUR * MAXMINUTE + 2];
		for (Interval interval : intervals) {
			int start = interval.getBeginMinuteUnit();
			if (null == workingTime[start]) {
				EndingTime endtime = new EndingTime(interval);
				workingTime[start] = endtime;
			} else {
				workingTime[start].add(interval);
			}
		}

		//calculate the maxWorking time.
		int[] maxworkingTime = new int[MAXHOUR * MAXMINUTE + 2];
		Arrays.fill(maxworkingTime, 0);
		maxworkingTime[maxworkingTime.length - 1] = Integer.MAX_VALUE;
		//		workingTime[0] = new WorkingTime();
		EndingTime time;
		for (int i = 0; i < workingTime.length; i++) {
			if (null != workingTime[i]) {
				time = workingTime[i];
				for (int endTime : time.endTime) {
					//					Tools.println("" + time.startTime + "=" + i);
					//					Tools.println("time:" + time.startTime + "," + endTime);
					int tmpMaxTime = maxworkingTime[i] + endTime - i;
					//					Tools.println(maxworkingTime[time.startTime] + "+"
					//							+ (endTime - time.startTime) + "=max:" + tmpMaxTime);
					while (tmpMaxTime > maxworkingTime[endTime]) {
						maxworkingTime[endTime++] = tmpMaxTime;
					}
				}
			}
		}
		return maxworkingTime[maxworkingTime.length - 2];
	}

	/**
	 * sort the interval by start time without assistant array.
	 * @param intervals
	 * @return the max working time
	 */
	public int getMaxWorkingTime2(List<Interval> intervals) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Timer.getInstance().clickAndPrintInterval("排序");
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval arg0, Interval arg1) {
				if (arg0.getBeginHour() != arg1.getEndHour()) {
					return arg0.getBeginHour() - arg1.getBeginHour();
				} else
					return arg0.getBeginMinute() - arg1.getBeginMinute();
			}

		});
		Timer.getInstance().clickAndPrintInterval("排序");
		int[] maxworkingTime = new int[MAXHOUR * MAXMINUTE + 2];
		Arrays.fill(maxworkingTime, 0);
		maxworkingTime[maxworkingTime.length - 1] = Integer.MAX_VALUE;

		for (Interval interval : intervals) {
			int startTime = interval.getBeginMinuteUnit();
			int endTime = interval.getEndMinuteUnit();
			int tmpMaxTime = maxworkingTime[startTime]
					+ interval.getIntervalMinute();
			while (tmpMaxTime > maxworkingTime[endTime]) {
				maxworkingTime[endTime++] = tmpMaxTime;
			}
		}

		return maxworkingTime[maxworkingTime.length - 2];
	}

	class EndingTime {
		//		int startTime;
		List<Integer> endTime = new LinkedList<Integer>();

		public EndingTime() {

		}

		public EndingTime(Interval interval) {
			//			startTime = interval.getBeginMinuteUnit();
			endTime.add(interval.getEndMinuteUnit());
		}

		void add(Interval interval) {
			endTime.add(interval.getEndMinuteUnit());
		}

	}

	public static void main(String[] args) {

		CopyOfProblem2 p = new CopyOfProblem2();
		//		Interval interval1 = new Interval("06:00", "08:30");
		//		Interval interval2 = new Interval("09:00", "11:00");
		//		Interval interval3 = new Interval("12:30", "14:00");
		//		Interval interval4 = new Interval("8:00", "09:00");
		//		Interval interval5 = new Interval("10:30", "14:00");
		//		Interval interval6 = new Interval("09:00", "11:30");
		//		Interval interval7 = new Interval("13:30", "24:00");
		//		Interval interval8 = new Interval("00:00", "05:00");
		//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3,
		//				interval4, interval5, interval6, interval7, interval8);
		List<Interval> figure1 = new ArrayList<Interval>();
		int n = 10000;
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
		System.out.println(p.getMaxWorkingTime2(figure1));
		timer.clickAndPrintTotalTimeCost("方案直接排序");
		timer.start();
		System.out.println(p.getMaxWorkingTime(figure1));
		timer.clickAndPrintTotalTimeCost("方案用辅助数组");
	}
}
