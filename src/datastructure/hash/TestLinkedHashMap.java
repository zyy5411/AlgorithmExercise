package datastructure.hash;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import util.Tools;

public class TestLinkedHashMap {

	void testVisitOrder() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(100,
				(float) 0.75, true);
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		Tools.println("" + map.get("a"));
		Tools.println("" + map.get("c"));
		map.entrySet();
		for (Iterator<Entry<String, Integer>> ite = map.entrySet().iterator(); ite
				.hasNext();) {
			Entry<String, Integer> e = ite.next();
			Tools.println("" + e.getKey() + "," + e.getValue());
		}
		for (Iterator<String> ite = map.keySet().iterator(); ite.hasNext();) {
			Tools.println("keySet:" + ite.next());
		}
	}

	public static void main(String[] args) {
		TestLinkedHashMap t = new TestLinkedHashMap();
		t.testVisitOrder();
	}

}
