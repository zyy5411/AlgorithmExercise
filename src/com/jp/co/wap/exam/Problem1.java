package com.jp.co.wap.exam;

import java.util.Arrays;
import java.util.List;

import com.jp.co.wap.exam.lib.Interval;

/**
 * split the interval by minute,
 * each element in overlaps array represent one minute.
 * 
 * @author Zheng yangyang
 *
 */

public class Problem1 {

	private final int MAXHOUR = 24;
	private final int MAXMINUTE = 60;

	/**
	 * get the max overlap count.
	 * @param intervals
	 * @return max overlap.
	 */
	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
		if (null == intervals || 0 == intervals.size())
			return 0;
		int[] overlaps = new int[MAXHOUR * MAXMINUTE + 1];
		Arrays.fill(overlaps, 0);
		//for each interval increase the time area. 
		int start;
		int end;
		for (Interval interval : intervals) {
			start = interval.getBeginMinuteUnit();
			end = interval.getEndMinuteUnit();
			while (start <= end) {
				overlaps[start]++;
				start++;
			}
		}

		return getMaxOverlap(overlaps);
	}

	/**
	 * the max overlap is the max number is the overlaps array.
	 * @return max overlap.
	 */
	private int getMaxOverlap(int[] overlaps) {
		int max = 0;
		for (int overlapCount : overlaps) {
			if (max < overlapCount)
				max = overlapCount;
		}
		return max;
	}

}
