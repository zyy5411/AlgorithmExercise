/*
 * Copyright 2014, Works Applications
 * 
 * This file is part of the solution to the programming 
 * examination released by Works Application Inc. 
 * 
 * The interfaces are provided by Works Applications and 
 * the methods are implemented by Ying Xu.
 *
 */

package com.jp.co.wap.exam.xuyin;

import java.util.List;

import com.jp.co.wap.exam.lib.Interval;

/**
 * Created on Sep. 25th, 2013 13:18:07 PM
 * Last modified on Sep. 29th, 2013 14:20:03 PM
 * @author Ying Xu
 */
public class Problem1 {
	
	/**
	 * Description: Given a list of intervals, calculate 
	 * the maximum number of overlapped intervals at the 
	 * same time.
	 * @param intervals
	 * @return the maximum number of overlapped intervals
	 */
	public int getMaxIntervalOverlapCount(List<Interval> intervals){
		
		//If the interval list is null or empty, return 0
		if(intervals == null || intervals.size() == 0)
			return 0;
		
		//The total minute units in a day, i.e. from 00:00 to 24:00
		int totalMiniteUnit = 60*24+2;  
		//The table <code>minuteCountTable</code> containing the 
		//number of intervals that are increased or decreased at  
		//each minute unit. Each entry is initialized as zero.
		int minuteCountTable[] = new int[totalMiniteUnit];
		
		//The minimum and maximum begin minute unit of all intervals
		int minBeginUnit = 0;
		int maxBeginUnit = 0;
	
		//Update the minuteCountTable for each interval
		for(Interval interval: intervals){
			int beginUnit = interval.getBeginMinuteUnit();
			if(beginUnit > maxBeginUnit)
				maxBeginUnit = beginUnit;
			if(beginUnit < minBeginUnit)
				minBeginUnit = beginUnit;
			//Increase the count at the begin minute unit
			minuteCountTable[beginUnit]++;
			//Decrease the count at the end+1 minute unit
			minuteCountTable[interval.getEndMinuteUnit()+1]--;
		}
		
		//The maximum number of overlapped intervals
		int maxInvervalOverlapCount = 0;
		//The current number of overlapped intervals
		int curIntervalOverlapCount = 0;
		
		//For each minute unit between the minimum and maximum begin unit
		for(int i = minBeginUnit; i<=maxBeginUnit; i++){
			if(minuteCountTable[i] != 0){
				//Calculate the current overlapped intervals by adding the 
				//count at each entry
				curIntervalOverlapCount += minuteCountTable[i];
				//Update the maximum number of overlapped intervals
				if(curIntervalOverlapCount > maxInvervalOverlapCount)
					maxInvervalOverlapCount = curIntervalOverlapCount;
			}
		}
		
		return maxInvervalOverlapCount;
	}

}
