package com.jp.co.wap.exam.liuzheng;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.jp.co.wap.exam.lib.Interval;

public class Problem2 {

	public class ComparatorInterval implements Comparator {
		public int compare(Object arg0, Object arg1) {
			Interval i0 = (Interval) arg0;
			Interval i1 = (Interval) arg1;

			int result = -Integer.compare(i0.getEndMinuteUnit(),
					i1.getEndMinuteUnit());
			if (result == 0) {
				return -Integer.compare(i0.getBeginMinuteUnit(),
						i1.getBeginMinuteUnit());
			}
			return result;
		}
	}

	public static boolean isOverlap(Interval interval0, Interval interval1) {
		// TODO: implement
		int as = interval0.getBeginMinuteUnit();
		int ae = interval0.getEndMinuteUnit();
		int bs = interval1.getBeginMinuteUnit();
		int be = interval1.getEndMinuteUnit();

		if (ae < bs || be < as)
			return false;
		if ((bs <= ae && as < bs && ae < be)
				|| (as <= be && bs < as && be < ae))
			return true;
		if ((as <= bs && be <= ae) || (bs <= as && ae <= be))
			return true;

		return false;
	}

	public int getMaxWorkingTime(List<Interval> intervals) {
		// TODO: Implement this method.
		int maxWorkingTime = 0;
		int size = intervals.size();

		ComparatorInterval comparator = new ComparatorInterval();
		Collections.sort(intervals, comparator);
		List<Integer> foos = new Vector<Integer>();

		// / interval
		// | f(0) + interval
		// | f(1) + interval
		// f(n) = max + ...
		// | f(i) + interval
		// | ...
		// \ f(n-1) + interval

		for (int index = 0; index < size; index++) {
			foos.add(intervals.get(index).getIntervalMinute());
		}

		for (int i = 1; i < size; i++) {
			Interval currentInterval = intervals.get(i);
			int currentUnit = foos.get(i);
			int maxUnit = currentUnit;
			for (int j = 0; j < i; j++) {
				Interval prevInterval = intervals.get(j);
				if (!isOverlap(currentInterval, prevInterval)) {
					int tempUnit = foos.get(j) + currentUnit;
					if (tempUnit > maxUnit) {
						maxUnit = tempUnit;
					}
				}
			}
			foos.set(i, maxUnit);
			if (maxUnit > maxWorkingTime) {
				maxWorkingTime = maxUnit;
			}
		}

		return maxWorkingTime;
	}
}
