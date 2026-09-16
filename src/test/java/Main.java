import net.YaRh.SortersLib.Benchmark;
import net.YaRh.SortersLib.BubbleSort;
import net.YaRh.SortersLib.MergeSort;
import net.YaRh.SortersLib.SortingAlgorithm;
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
		stepDelay.set(0.5D);
		
		Benchmark.benchmarkRepeat.set(1);
		
		Benchmark.setAlgorithm(new MergeSort());
		Benchmark.benchmark();
		
		SortingAlgorithm a = new BubbleSort();
		VisualList v = new VisualList();
		v.addAll(Benchmark.randomBenchmarkList());
		a.sort(v);
		v.isOrdered();
	}
}