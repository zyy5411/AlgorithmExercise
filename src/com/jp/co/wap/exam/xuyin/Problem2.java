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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jp.co.wap.exam.lib.Interval;

/**
 * Created on Sep. 25th, 2013 13:19:13 PM
 * Last modified on Sep. 29th, 2013 15:02:53 PM
 * @author Ying Xu
 */
public class Problem2 {
	
	/**
	 * Description: Sort the provided integer array with quick sort algorithm.
	 * !!!Important Notice: As the [08:00, 09:00] and [09:00, 10:00] are overlapped as specified, so we 
	 * consider the maximum working time of the two is 60 minutes, not 120 minutes
	 * @param input
	 * @param start
	 * @param end
	 */
	public static void quickSort(Integer[] input, int start, int end){
		if(start >= end)
			return;
		int pivot = partition(input, start, end);
		quickSort(input, start, pivot-1);
		quickSort(input, pivot+1, end);
	} 
	
	/**
	 * Description: Reorder the numbers so that all numbers before and after 
	 * the pivot are smaller and larger than the pivot number
	 * @param input
	 * @param start
	 * @param end
	 * @return the index of the pivot number
	 */
	private static int partition(Integer[] input, int start, int end) {
		int small = start-1;
		int pivot = input[end];
		for(int i = start; i<end; i++){
			if(input[i] < pivot){
				small++;
				if(small != i){
					int temp = input[small]; 
					input[small] = input[i];
					input[i] = temp;
				}
			}
		}
		small++;
		if(small != end){
			int temp = input[small]; 
			input[small] = input[end];
			input[end] = temp;
		}
		return small;
	}
	
	/**
	 * Description: Calculate the maximum work time to work assign to one worker.
	 * @param intervals
	 * @return
	 */
	public int getMaxWorkingTime(List<Interval> intervals){
		
		//If the interval list is null or empty, return 0.
		if(intervals == null || intervals.size() == 0)
			return 0;
		
		
		/**
		 * Sort end minute units of all intervals; map each end minute unit to multiple intervals
		 */
		//a map mapping from the end minute unit to the intervals. Multiple intervals may have the same end minute unit.
		Map<Integer, List<Interval>> endMinuteIntervalMap = new HashMap<Integer, List<Interval>>();
		
		for(Interval interval: intervals){
			int endMinute = interval.getEndMinuteUnit();
			if(endMinuteIntervalMap.containsKey(endMinute)){
				endMinuteIntervalMap.get(endMinute).add(interval);
			}else{
				List<Interval> list = new ArrayList<Interval>();
				list.add(interval);
				endMinuteIntervalMap.put(endMinute, list);
			}
		}
		
		//Sort the end minute units
		Integer[] endMinutes = new Integer[endMinuteIntervalMap.keySet().size()];
		endMinuteIntervalMap.keySet().toArray(endMinutes);
		quickSort(endMinutes, 0, endMinutes.length-1);
		
		//the total minute units
		int totalMinutes = 60*24+1;
		//the maximum working time up to each minute unit
		int[] workingTimeVec = new int[totalMinutes];
		
		/**
		 * Calculate the maximum working time up to each minute unit
		 */
		int i = 0;
		for(Integer endMinute: endMinutes){
			
			//the max working time of the interval list that has one of the intervals that are ended at the 
			//current minute unit as the last interval
			int maxWorkingTime = 0;
			
			//Iterate each interval that is ended at the current minute unit
			for(Interval interval: endMinuteIntervalMap.get(endMinute)){
				
				int beginMinute = interval.getBeginMinuteUnit();
				
				//If the begin minute is 0, the max working time should be the interval duration itself.
				if(beginMinute == 0){
					maxWorkingTime = interval.getIntervalMinute();
					break;
				}else{
					
					//Find the nearest minute unit <bold>before</bold> the begin minute that has recorded a max working time
					int j = beginMinute-1;
					for(; j>=0; j--){
						if(workingTimeVec[j] > 0){
							break;
						}
					}
					
					//If there isn't any non-overlap intervals before the current interval, 
					//the max working time should be the interval duration itself
					if(j == -1){
						maxWorkingTime = interval.getIntervalMinute();
						break;
					}
					
					//Update the max working time for each interval that is ended at the current minute unit
					if(workingTimeVec[j] + interval.getIntervalMinute() > maxWorkingTime)
						maxWorkingTime = workingTimeVec[j] + interval.getIntervalMinute();
				}
			}
			
			//Update the workingTimeVec[i] according to the calculated working time and workingTimeVec[i-1]
			if(i == 0)
				workingTimeVec[endMinute] = maxWorkingTime;
			else
				workingTimeVec[endMinute] = (maxWorkingTime > workingTimeVec[endMinutes[i-1]])?maxWorkingTime:workingTimeVec[i-1];
			
			i++;
		}
		
		/**
		 * Return the max working time up to the last end minute
		 */
		return workingTimeVec[endMinutes[endMinutes.length-1]];
		
	}

}
