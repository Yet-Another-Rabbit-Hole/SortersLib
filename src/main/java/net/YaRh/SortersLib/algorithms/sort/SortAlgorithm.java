package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.Logging;
import net.YaRh.VisualSort.VisualList;

import java.util.List;

/**
 * @since 1.0.0
 */
public interface SortAlgorithm {
	void sort(List<Integer> list);
	
	default boolean resolveDoubleList(List<Integer> list) {
		if (list.size() == 2) {
			if (list.get(0) > list.get(1))
				VisualList.swap(list, 0, 1);
			return true;
		}
		return false;
	}
	default boolean resolveTrippleList(List<Integer> list) {
		if (list.size() == 3) {
			resolveDoubleList(list.subList(0, 2));
			resolveDoubleList(list.subList(1, 3));
			resolveDoubleList(list.subList(0, 2));
			return true;
		}
		return false;
	}
}