package net.YaRh.SortersLib.algorithms;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * Walks through the list once and removes every int that's not in order
 *
 * @since 1.2.0
 */
public class StalinSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("StalinSort");
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		
		int last = list.get(0);
		int i = 1;
		do {
			int v = list.get(i);
			if (last <= v) {
				last = v;
				i++;
			} else list.remove(i);
		} while (i < list.size());
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}