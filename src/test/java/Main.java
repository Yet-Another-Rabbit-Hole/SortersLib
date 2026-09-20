import net.YaRh.SortersLib.algorithms.search.BinarySearch;
import net.YaRh.SortersLib.algorithms.search.LinearSearch;
import net.YaRh.SortersLib.benchmark.SearchingBenchmark;
import net.YaRh.SortersLib.benchmark.SortingBenchmark;
import net.YaRh.SortersLib.Config;
import net.YaRh.SortersLib.algorithms.sort.ShakerSort;
import net.YaRh.VisualSort.VisualList;

import static net.YaRh.CheapLog.Config.*;
import static net.YaRh.VisualSort.Config.stepByStep;
import static net.YaRh.VisualSort.Config.stepDelay;

public class Main {
	public static void main(String[] args) {
		debugging.enable();
		thread.enable();
		logging.enable();
		//location.enable();
		errors.enable();
		ids.enable();
		
		VisualList.LOGGER.disable();
		
		stepByStep.disable();
		//stepDelay.set(2D);
		
		//Config.benchmarkRepeat.set(15);
		//Config.benchmarkSetSize.set(15);
		
		//SortingBenchmark.setAlgorithm(new ShakerSort());
		//SortingBenchmark.benchmark();
		
		BinarySearch.LOGGER.logging.enable();
		SearchingBenchmark.setAlgorithm(new BinarySearch());
		SearchingBenchmark.benchmark();
		
		/*
		SortAlgorithm a = new CombSort();
		VisualList v = new VisualList();
		v.addAll(Benchmark.randomBenchmarkList());
		a.sort(v);
		v.isOrdered();
		 */
	}
}