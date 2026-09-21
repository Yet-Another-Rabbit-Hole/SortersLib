package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * Swaps the first and last values of the list if needed,
 * then sorts the first two thirds,
 * then the last two thirds
 * and then the first two thirds again
 *
 * @since 2.1.0
 */
public class StoogeSort implements SortAlgorithm {
	public static final Logger LOGGER = new Logger("StoogeSort");
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void sort(List<Integer> list) {
		LOGGER.debug.println("sorting %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		if (resolveTrippleList(list)) return;
		
		int i1 = list.get(0);
		int i2 = list.get(list.size() - 1);
		if (i1 > i2) VisualList.swap(list, 0, list.size() - 1);
		
		int third = list.size() / 3;
		
		List<Integer> firstTwoThirds = list.subList(0, third * 2);
		List<Integer> secondTwoThirds = list.subList(third, list.size() - 1);
		
		sort(firstTwoThirds);
		sort(secondTwoThirds);
		sort(firstTwoThirds);
	}
}