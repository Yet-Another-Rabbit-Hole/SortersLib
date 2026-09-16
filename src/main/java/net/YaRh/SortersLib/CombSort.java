package net.YaRh.SortersLib;

import net.YaRh.CheapLog.logging.Logger;

import java.util.List;

public class CombSort implements SortingAlgorithm {
	public static final Logger LOGGER = new Logger("CombSort");
	
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
		int spacing = list.size() - 1;
		do {
			touched = false;
			
			for (int i = 0; i + spacing < list.size(); i++) {
				int i1 = list.get(i);
				int i2 = list.get(i + spacing);
				if (i1 > i2) {
					list.set(i, i2);
					list.set(i + spacing, i1);
					touched = true;
				}
			}
			
			spacing--;
			
			LOGGER.debug.println("Looped through: %s touched: %s, spacing: %d", list, touched, spacing);
		} while (touched || spacing >= 0);
		
		LOGGER.debug.println("Sorted list: %s", list);
	}
}