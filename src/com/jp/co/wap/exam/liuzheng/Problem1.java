package com.jp.co.wap.exam.liuzheng;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.jp.co.wap.exam.lib.Interval;

public class Problem1 {

	private class Pair {
		private int minuteUnit;
		private boolean isStart;

		public Pair(int minuteUnit, boolean isStart) {
			this.setMinuteUnit(minuteUnit);
			this.setStart(isStart);
		}

		private int getMinuteUnit() {
			return minuteUnit;
		}

		private void setMinuteUnit(int minuteUnit) {
			this.minuteUnit = minuteUnit;
		}

		private boolean isStart() {
			return isStart;
		}

		private void setStart(boolean isStart) {
			this.isStart = isStart;
		}
	}

	public class ComparatorPair implements Comparator {
		public int compare(Object arg0, Object arg1) {
			Pair p0 = (Pair) arg0;
			Pair p1 = (Pair) arg1;

			int result = Integer.compare(p0.minuteUnit, p1.minuteUnit);
			if (result == 0) {
				if (p0.isStart == true && p1.isStart == false) {
					return -1;
				} else if (p0.isStart == false && p1.isStart == true) {
					return 1;
				} else {
					return 0;
				}
			}
			return result;
		}
	}

	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
		// TODO: Implement this method.

		// ≈≈–Ú
		// ﬂ£“ª±È
		// s: +1; e: -1; record the max

		int maxCount = 0;
		int overlapCount = 0;

		List<Pair> pairs = new Vector<Pair>();
		for (int index = 0; index < intervals.size(); index++) {
			Interval interval = intervals.get(index);
			pairs.add(new Pair(interval.getBeginMinuteUnit(), true));
			pairs.add(new Pair(interval.getEndMinuteUnit(), false));
		}
		ComparatorPair comparator = new ComparatorPair();
		Collections.sort(pairs, comparator);

		for (int index = 0; index < pairs.size(); index++) {
			Pair currentPair = pairs.get(index);
			if (currentPair.isStart() == true) {
				overlapCount++;
				if (overlapCount > maxCount) {
					maxCount = overlapCount;
				}
			} else {
				overlapCount--;
			}
		}

		return maxCount;
	}
}
