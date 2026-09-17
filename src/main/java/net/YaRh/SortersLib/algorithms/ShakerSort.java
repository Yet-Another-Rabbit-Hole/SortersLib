package net.YaRh.SortersLib.algorithms;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * Like {@link BubbleSort} but works back and forth
 *
 * @since 1.2.0
 */
public class ShakerSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("ShakerSort");
	
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
			
			for (int i = list.size() - 1; i > 0; i--) {
				if (list.get(i) < list.get(i-1)) {
					VisualList.swap(list, i, i-1);
					touched = true;
				}
			}
		} while (touched);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}