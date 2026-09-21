package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * Sorts the list by sorting the first half,
 * then the second and moving the bigger maximum of both to the end
 * until it has sorted everything
 *
 * @since 2.1.0
 */
public class SlowSort implements SortAlgorithm {
	public static final Logger LOGGER = new Logger("SlowSort");
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void sort(List<Integer> list) {
		LOGGER.debug.println("sorting %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		if (resolveTrippleList(list)) return;
		
		int sorted = 0;
		int half;
		while (sorted < list.size()) {
			half = (list.size() - sorted) / 2;
			List<Integer> firstHalf = list.subList(0, half);
			List<Integer> secondHalf = list.subList(half, list.size() - sorted);
			
			if (firstHalf.isEmpty() || secondHalf.isEmpty()) break;
			
			sort(firstHalf);
			sort(secondHalf);
			
			int i1 = firstHalf.get(firstHalf.size() - 1);
			int i2 = secondHalf.get(secondHalf.size() - 1);
			
			if (i1 < i2) sorted++;
			else if (i2 < i1) {
				VisualList.swap(list, half - 1, list.size() - sorted - 1);
				sorted++;
			}
		}
	}
}