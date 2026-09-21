package net.YaRh.SortersLib.algorithms.search;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.ConvConf.Attribute;
import net.YaRh.SortersLib.algorithms.sort.CombSort;
import net.YaRh.SortersLib.algorithms.sort.SortAlgorithm;

import java.util.List;
import java.util.Optional;

public class BinarySearch implements SearchAlgorithm {
	public static final Logger LOGGER = new Logger("BinarySearch");
	
	public static final Attribute<SortAlgorithm> sorter = new Attribute<>(new CombSort());
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void setup(List<Integer> list) {
		SortAlgorithm sorter = BinarySearch.sorter.get();
		sorter.sort(list);
	}
	
	@Override
	public Optional<Integer> search(List<Integer> list, int value) {
		int low = 0;
		int high = list.size() - 1;
		
		int mid;
		int midValue;
		while (low <= high) {
			mid = low + (high - low) / 2;
			midValue = list.get(mid);
			
			if (midValue == value)
				return Optional.of(mid);
			
			if (midValue < value)
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		return Optional.empty();
	}
}