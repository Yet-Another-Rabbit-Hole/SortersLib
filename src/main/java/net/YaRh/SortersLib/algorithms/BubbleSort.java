package net.YaRh.SortersLib.algorithms;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * @since 1.0.0
 */
public class BubbleSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("BubbleSort");
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		
		boolean touched;
		do {
			touched = false;
			
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i) > list.get(i+1)) {
					VisualList.swap(list, i, i+1);
					touched = true;
				}
			}
			
			LOGGER.debug.println("Looped through: %s touched: %s", list, touched);
		} while (touched);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}