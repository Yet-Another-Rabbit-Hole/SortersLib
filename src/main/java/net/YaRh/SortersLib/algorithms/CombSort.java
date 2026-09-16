package net.YaRh.SortersLib.algorithms;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * @since 1.0.0
 */
public class CombSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("CombSort");
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		
		boolean touched;
		int spacing = list.size() - 1;
		do {
			touched = false;
			
			for (int i = 0; i + spacing < list.size(); i++) {
				if (list.get(i) > list.get(i + spacing)) {
					VisualList.swap(list, i, i + spacing);
					touched = true;
				}
			}
			
			spacing--;
			
			LOGGER.debug.println("Looped through: %s touched: %s, spacing: %d", list, touched, spacing);
		} while (touched || spacing >= 0);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}