package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.logging.Logger;

import java.util.List;

/**
 * Solves sorting a list like Thanos: if it isn't sorted, delete half of it
 *
 * @since 2.1.0
 */
public class ThanosSort implements SortAlgorithm {
	public static final Logger LOGGER = new Logger("ThanosSort");
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void sort(List<Integer> list) {
		LOGGER.debug.println("sorting %s", list);
		
		if (list.size() <= 1) return;
		
		int i = 0;
		while (++i < list.size()) {
			LOGGER.debug.println("list %s", list);
			
			if (list.get(i - 1) > list.get(i)) {
				int half = list.size() / 2;
				for (int j = half; j != 0; j--) list.remove(half);
				i = 0;
			}
		}
	}
}
