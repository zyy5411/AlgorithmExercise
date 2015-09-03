package com.jp.co.wap.exam;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.jp.co.wap.exam.lib.Interval;

public class Problem2WithList {

	final int MAXHOUR = 24;
	final int MAXMINUTE = 60;

	/**
	 * use assistant array to sort the interval by start time.
	 * @param intervals
	 * @return the max working time
	 */
	public int getMaxWorkingTime(List<Interval> intervals) {
		if (null == intervals || 0 == intervals.size())
			return 0;
		// use assistant array to sort the interval by start time.
		EndingTime[] workingTime = new EndingTime[MAXHOUR * MAXMINUTE + 2];
		initEndingTimes(intervals, workingTime);

		//calculate the maxWorking time.
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
	 * @param endingTimes endtime array
	 */
	private void initEndingTimes(List<Interval> intervals,
			EndingTime[] endingTimes) {
		int start;
		for (Interval interval : intervals) {
			start = interval.getBeginMinuteUnit();
			if (null == endingTimes[start]) {
				endingTimes[start] = new EndingTime();
			}
			endingTimes[start].add(interval);
		}
	}

	/**
	 * calculate the maxWorking time.
	 * @param endingTimes the end time's array.
	 * @param maxworkingTime each element store the maxWorking time which is from 0 to here.
	 */
	private void calculateTheMaxWorkingTime(EndingTime[] endingTimes,
			int[] maxworkingTime) {
		EndingTime endtime;
		for (int i = 0; i < endingTimes.length; i++) {
			if (null != endingTimes[i]) {
				endtime = endingTimes[i];
				for (int endTime : endtime.endTime) {
					int maxTimeWithThisInterval = maxworkingTime[i] + endTime
							- i;
					while (maxTimeWithThisInterval > maxworkingTime[endTime]) {
						maxworkingTime[endTime++] = maxTimeWithThisInterval;
					}
				}
			}
		}
	}

	/**
	 * store the interval's ending time.
	 * @param intervals
	 * @return the max working time
	 */

	class EndingTime {
		List<Integer> endTime = new LinkedList<Integer>();

		void add(Interval interval) {
			endTime.add(interval.getEndMinuteUnit());
		}
	}

}
