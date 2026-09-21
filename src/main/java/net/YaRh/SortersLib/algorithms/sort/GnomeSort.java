package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.VisualSort.Visual;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * Solves the list by comparing two values, swapping them if necessary and advancing accordingly:<p>
 * If it had to swap the values, it moves a step back<p>
 * If the values were already sorted, it moves a step ahead
 *
 * @since 2.1.0
 */
public class GnomeSort implements SortAlgorithm {
	@Override
	public void sort(List<Integer> list) {
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		if (resolveTrippleList(list)) return;
		
		int currentPos = 0;
		while (currentPos < list.size() - 1) {
			if (currentPos == -1) currentPos = 0;
			
			int i1 = list.get(currentPos);
			int i2 = list.get(currentPos + 1);
			
			if (i1 <= i2) {
				currentPos++;
			} else {
				VisualList.swap(list, currentPos, currentPos + 1);
				currentPos--;
			}
		}
	}
}