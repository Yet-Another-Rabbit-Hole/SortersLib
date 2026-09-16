package net.YaRh.SortersLib.algorithms;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.VisualSort.VisualList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @since 1.1.0
 */
public class QuickSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("QuickSort");
	
	/**
	 * Dictates from where the pivot is chosen
	 *
	 * @since 1.1.0
	 */
	public enum PivotKriterium {
		FIRST(l -> 0),
		LAST(l -> l.size()-1),
		MIDDLE(l -> l.size()/2),
		RANDOM(l -> (int) ((Math.random() * 100)%l.size()));
		
		private final Function<List<Integer>, Integer> chooser;
		
		PivotKriterium(Function<List<Integer>, Integer> chooser) {
			this.chooser = chooser;
		}
		public int choose(List<Integer> list) {
			if (list.isEmpty()) return 0;
			return chooser.apply(list);
		}
	}
	
	private final PivotKriterium kriterium;
	
	public QuickSort(PivotKriterium kriterium) {
		this.kriterium = kriterium;
	}
	public QuickSort() {
		this.kriterium = PivotKriterium.LAST;
	}
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (resolveDoubleList(list)) return;
		
		List<Integer> smaller = new ArrayList<>();
		List<Integer> equal = new ArrayList<>();
		List<Integer> greater = new ArrayList<>();
		
		int pivotIdx = kriterium.choose(list);
		int pivot = list.remove(pivotIdx);
		
		equal.add(pivot);
		
		do {
			int i = list.remove(0);
			if (pivot > i) smaller.add(i);
			else if (pivot == i) equal.add(i);
			else greater.add(i);
		} while (!list.isEmpty());
		
		sort(greater);
		sort(smaller);
		
		LOGGER.debug.println("smaller: %s", smaller);
		LOGGER.debug.println("equal: %s", equal);
		LOGGER.debug.println("greater: %s", greater);
		
		list.addAll(smaller);
		list.addAll(equal);
		list.addAll(greater);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}