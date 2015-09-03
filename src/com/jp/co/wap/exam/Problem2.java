package com.jp.co.wap.exam;

import java.util.Arrays;
import java.util.List;

import com.jp.co.wap.exam.lib.Interval;

public class Problem2 {

	private final int MAXHOUR = 24;
	private final int MAXMINUTE = 60;

	/**
	 * use assistant array to sort the interval by start time.
	 * @param intervals
	 * @return the max working time
	 */
	public int getMaxWorkingTime(List<Interval> intervals) {
		if (null == intervals || 0 == intervals.size())
			return 0;
		// step1. use assistant array to sort the interval by start time.
		// time range is [0,MAXHOUR*MAXMINUTE].
		EndingTimeNode[] workingTime = new EndingTimeNode[MAXHOUR * MAXMINUTE];
		initEndingTimes(intervals, workingTime);

		// step2. calculate the maxWorking time.
		// time range is [0,MAXHOUR*MAXMINUTE],and the last element is a guard element.
		int[] maxworkingTime = new int[MAXHOUR * MAXMINUTE + 2];
		Arrays.fill(maxworkingTime, 0);
		maxworkingTime[maxworkingTime.length - 1] = Integer.MAX_VALUE;
		calculateTheMaxWorkingTime(workingTime, maxworkingTime);
		return maxworkingTime[maxworkingTime.length - 2];
	}

	/**
	 * store the interval's ending time to the endingTimes array,
		so that it can Transfer from the lowest start.
		the interval is from the sub index to the ending time.
	 * @param intervals all the intervals
	 * @param endingTimes end time array
	 */
	private void initEndingTimes(List<Interval> intervals,
			EndingTimeNode[] endingTimes) {
		int start;
		for (Interval interval : intervals) {
			start = interval.getBeginMinuteUnit();
			if (null == endingTimes[start]) {
				endingTimes[start] = new EndingTimeNode(
						interval.getEndMinuteUnit());
			}
			endingTimes[start].add(interval.getEndMinuteUnit());
		}
	}

	/**
	 * calculate the maxWorking time.
	 * @param endingTimes the end time's array.
	 * @param maxworkingTime each element store the maxWorking time which is from 0 to here.
	 */
	private void calculateTheMaxWorkingTime(EndingTimeNode[] endingTimes,
			int[] maxworkingTime) {
		EndingTimeNode endtimeNode;
		for (int i = 0; i < endingTimes.length; i++) {
			if (null != endingTimes[i]) {
				endtimeNode = endingTimes[i];
				while (null != endtimeNode) {
					int endTime = endtimeNode.endTime;
					int maxTimeWithThisInterval = maxworkingTime[i] + endTime
							- i;
					while (maxTimeWithThisInterval > maxworkingTime[endTime]) {
						maxworkingTime[endTime++] = maxTimeWithThisInterval;
					}
					endtimeNode = endtimeNode.next;
				}
			}
		}
	}

	/**
	 * store the interval's ending time.
	 * @param intervals
	 * @return the max working time
	 */

	private class EndingTimeNode {
		int endTime;
		EndingTimeNode next;
		EndingTimeNode lastNode;

		public EndingTimeNode(int time) {
			endTime = time;
			lastNode = this;
		}

		void add(int time) {
			lastNode.next = new EndingTimeNode(time);
			lastNode = lastNode.next;
		}
	}

}
