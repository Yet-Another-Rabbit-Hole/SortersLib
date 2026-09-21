package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class MergeSort implements SortAlgorithm {
	public static final Logger LOGGER = new Logger("MergeSort");
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		if (resolveTrippleList(list)) return;
		
		int mid = list.size() / 2;
		List<Integer> list1 = new ArrayList<>(list.subList(0, mid));
		List<Integer> list2 = new ArrayList<>(list.subList(mid, list.size()));
		
		sort(list1);
		sort(list2);
		
		list.clear();
		
		while (true) {
			if (list1.isEmpty() && list2.isEmpty()) break;
			if (list1.isEmpty()) {
				list.addAll(list2);
				break;
			}
			if (list2.isEmpty()) {
				list.addAll(list1);
				break;
			}
			
			switch (Integer.compare(list1.get(0), list2.get(0))) {
				case 0 -> {
					list.add(list1.remove(0));
					list.add(list2.remove(0));
				}
				case 1 -> list.add(list2.remove(0));
				case -1 -> list.add(list1.remove(0));
			}
		}
		
		LOGGER.debug.println("Ordered list: %s", list);
	}
}