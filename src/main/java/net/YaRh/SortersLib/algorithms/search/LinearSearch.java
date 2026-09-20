package net.YaRh.SortersLib.algorithms.search;

import net.YaRh.CheapLog.logging.Logger;

import java.util.List;
import java.util.Optional;

public class LinearSearch implements SearchAlgorithm {
	public static final Logger LOGGER = new Logger("LinearSearch");
	
	static {
		LOGGER.disable();
	}
	
	@Override
	public void setup(List<Integer> list) {}
	
	@Override
	public Optional<Integer> search(List<Integer> list, int value) {
		LOGGER.log.println("Searching \"%s\"", value);
		
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) == value)
				return Optional.of(i);
		
		LOGGER.error.println("Not found");
		return Optional.empty();
	}
}