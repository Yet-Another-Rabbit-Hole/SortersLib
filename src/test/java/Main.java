import net.YaRh.SortersLib.Benchmark;
import net.YaRh.SortersLib.Config;
import net.YaRh.SortersLib.algorithms.*;
import net.YaRh.VisualSort.VisualList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
		stepDelay.set(2D);
		
		Config.benchmarkRepeat.set(2);
		//Config.benchmarkListSize.set(5);
		
		Benchmark.setAlgorithm(new QuickSort());
		Benchmark.benchmark();
		
		/*
		SortingAlgorithm a = new CombSort();
		VisualList v = new VisualList();
		v.addAll(Benchmark.randomBenchmarkList());
		a.sort(v);
		v.isOrdered();
		 */
	}
}