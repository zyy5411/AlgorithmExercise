package com.jp.co.wap.exam;
//package jp.co.wap.exam;
//
//import java.util.Arrays;
//import java.util.List;
//
//import jp.co.wap.exam.lib.Interval;
//
//import util.Timer;
//import util.Tools;
//
//public class CopyOfProblem1 {
//
//	final int MAXHOUR = 24;
//	final int MAXMINUTE = 60;
//	int[] overlaps = new int[MAXHOUR * MAXMINUTE + 3];
//	int[] nextTime = new int[MAXHOUR * MAXMINUTE + 3];
//
//	public CopyOfProblem1() {
//		Arrays.fill(overlaps, 0);
//		Arrays.fill(nextTime, 0);
//		nextTime[nextTime.length - 1] = Integer.MAX_VALUE;
//		nextTime[0] = Integer.MAX_VALUE;
//	}
//
//	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
//		for (Interval interval : intervals) {
//			addInterval(interval);
//		}
//
//		return 0;
//	}
//
//	public int getMaxIntervalOverlapCount2(List<Interval> intervals) {
//		Arrays.fill(overlaps, 0);
//		for (Interval interval : intervals) {
//			int start = timeToMinites(interval.startTime);
//			int end = timeToMinites(interval.endTime);
//			while (start <= end) {
//				overlaps[start++]++;
//			}
//		}
//
//		return getMaxOverlap();
//	}
//
//	int getMaxOverlap() {
//		int max = 0;
//		for (int i : overlaps) {
//			if (max < i)
//				max = i;
//		}
//		return max;
//	}
//
//	private void addInterval(Interval interval) {
//		int start = timeToMinites(interval.startTime) + 1;
//		int end = timeToMinites(interval.endTime) + 1;
//		int index = start;
//		//		Tools.println("===" + start);
//		int preIndex = findPreEdgeIndex(start);
//		nextTime[preIndex] = start;
//		overlaps[start] = overlaps[preIndex];
//		nextTime[end] = Integer.MAX_VALUE;
//		if (0 == nextTime[start]) {
//			nextTime[start] = findNextEdgeIndex(start);
//			//			Tools.println("nextTime:" + nextTime[start]);
//		}
//		int lastChangeIndex = index;
//		while (index < end) {
//			overlaps[index]++;
//			Tools.println("increase:" + index);
//			lastChangeIndex = index;
//			index = nextTime[index];
//		}
//		nextTime[lastChangeIndex] = end;
//		nextTime[end] = findNextEdgeIndex(end);
//		overlaps[end] = overlaps[lastChangeIndex] - 1;
//
//		//		Tools.println("end:" + end + "," + nextTime[end]);
//		//		for (int i = 0; i < nextTime.length; i++) {
//		//			if (nextTime[i] != 0) {
//		//				//				Tools.println(i + ",overlap:" + overlaps[i] + ",next:"
//		//				//						+ nextTime[i]);
//		//			}
//		//		}
//
//	}
//
//	private int findNextEdgeIndex(int index) {
//		do {
//			index++;
//		} while (0 == nextTime[index]);
//		return index;
//	}
//
//	private int findPreEdgeIndex(int index) {
//		while (0 == nextTime[index]) {
//			index--;
//		}
//		return index;
//	}
//
//	private int timeToMinites(String time) {
//		int minites = 0;
//		String[] timeSplit = time.split(":");
//		minites += 60 * Integer.parseInt(timeSplit[0]);
//		minites += Integer.parseInt(timeSplit[1]);
//		return minites;
//	}
//
//	public static void main(String[] args) {
//
//		CopyOfProblem1 p = new CopyOfProblem1();
//		Interval interval1 = new Interval("08:00", "12:30");
//		Interval interval2 = new Interval("06:00", "09:00");
//		Interval interval3 = new Interval("11:00", "13:00");
//		Interval interval4 = new Interval("7:00", "12:40");
//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3,
//				interval4);
//		//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3,
//		//				interval4);
//		//		List<Interval> figure1 = new ArrayList<Interval>();
//		//		int n = 1000;
//		//		while (n-- > 0) {
//		//			figure1.add(new Interval());
//		//		}
//
//		Timer timer = Timer.getInstance();
//		timer.start();
//		System.out.println(p.getMaxIntervalOverlapCount(figure1));
//		timer.clickAndPrintInterval("方案一");
//		System.out.println(p.getMaxIntervalOverlapCount2(figure1));
//		timer.clickAndPrintInterval("方案2");
//	}
//}
