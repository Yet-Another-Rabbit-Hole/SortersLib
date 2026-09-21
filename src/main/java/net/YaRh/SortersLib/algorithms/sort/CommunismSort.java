package net.YaRh.SortersLib.algorithms.sort;

import net.YaRh.CheapLog.logging.Logger;

import java.util.List;

/**
 * Sorts the array after the rules of communism: sharing<p>
 * It distributes the total value under every node in the list
 *
 * @since 2.1.0
 */
public class CommunismSort implements SortAlgorithm {
	public static final Logger LOGGER = new Logger("CommunismSort");
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void sort(List<Integer> list) {
		LOGGER.debug.println("sorting %s", list);
		
		if (list.size() <= 1) return;
		
		int allValue = 0;
		for (int i = 0; i < list.size(); i++)
			allValue += list.get(i);
		
		int newValue = (int) (double) allValue / list.size();
		
		for (int i = 0; i < list.size(); i++)
			list.set(i, newValue);
	}
}