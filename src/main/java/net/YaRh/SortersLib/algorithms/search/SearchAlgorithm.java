package net.YaRh.SortersLib.algorithms.search;

import java.util.List;
import java.util.Optional;

/**
 * @since 2.0.0
 */
public interface SearchAlgorithm {
	void setup(List<Integer> list);
	
	Optional<Integer> search(List<Integer> list, int value);
}