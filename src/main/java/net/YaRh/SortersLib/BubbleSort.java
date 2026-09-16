package net.YaRh.SortersLib;

import net.YaRh.CheapLog.logging.Logger;

import java.util.List;

public class BubbleSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("BubbleSort");
	
	public void sort(List<Integer> list) {
		LOGGER.debug.println("Sorting list: %s", list);
		
		if (list.size() <= 1) return;
		if (list.size() == 2) {
			int i = list.remove(0);
			if (list.get(0) > i) list.add(0, i);
			else list.add(i);
			return;
		}
		
		boolean touched;
		do {
			touched = false;
			
			for (int i = 0; i < list.size() - 1; i++) {
				int i1 = list.get(i);
				int i2 = list.get(i + 1);
				if (i1 > i2) {
					list.set(i, i2);
					list.set(i + 1, i1);
					touched = true;
				}
			}
			
			LOGGER.debug.println("Looped through: %s touched: %s", list, touched);
		} while (touched);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}