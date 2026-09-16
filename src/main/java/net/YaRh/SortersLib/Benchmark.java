package net.YaRh.SortersLib;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.ConvConf.Switch;
import net.YaRh.SortersLib.algorithms.SortingAlgorithm;
import net.YaRh.VisualSort.VisualList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.YaRh.SortersLib.Config.benchmarkListSize;
import static net.YaRh.SortersLib.Config.benchmarkRepeat;

/**
 * @since 1.0.0
 */
public class Benchmark {
	private static final Logger LOGGER = new Logger("Benchmark");
	
	public static final Switch visual = new Switch(false);
	
	private static SortingAlgorithm algorithm = null;
	
	public static long medianTime = -1L;
	public static long successRate = -1L;
	
	private static int runs = 0;
	private static int success = 0;
	
	public static void setAlgorithm(SortingAlgorithm pAlgorithm) {
		algorithm = pAlgorithm;
	}
	
	public static void benchmark() {
		if (algorithm == null)
			throw new IllegalStateException("Cannot benchmark as there is no algorithm given");
		
		LOGGER.information.enable();
		LOGGER.logging.enable();
		LOGGER.errors.enable();
		
		success = 0;
		runs = 0;
		medianTime = -1L;
		successRate = -1L;
		
		for (Integer i = benchmarkRepeat.get(); i > 0; i--)
			benchmarkRun();
		
		successRate = success / runs;
		
		logResult();
	}
	
	private static void benchmarkRun() {
		runs++;
		
		List<Integer> unordered = randomBenchmarkList();
		
		List<Integer> test;
		if (visual.get()) test = new VisualList(unordered);
		else test = new ArrayList<>(unordered);
		
		List<Integer> sorted = new ArrayList<>(unordered);
		Collections.sort(sorted);
		
		long start = System.nanoTime();
		algorithm.sort(test);
		long end = System.nanoTime();
		
		long time = end - start;
		boolean isSorted = test.equals(sorted);
		
		logRunResult(time, isSorted);
		if (!isSorted) logFailedRun(unordered, sorted, test);
		
		if (medianTime == -1L) medianTime = time;
		else {
			medianTime += time;
			medianTime /= runs;
		}
		if (isSorted) success++;
	}
	
	private static void logResult() {
		LOGGER.log.println("Benchmark finished");
		LOGGER.info.println("Median time: %sms", medianTime / 1_000_000L);
		LOGGER.info.println("Across %d benchmark(s)", benchmarkRepeat.get());
		LOGGER.info.println("With a success rate of %s", successRate);
		LOGGER.info.println("With %d ints per list", benchmarkListSize.get());
	}
	
	private static void logRunResult(long duration, boolean correct) {
		long timeInMs = duration / 1_000_000L;
		if (correct) LOGGER.info.println("Finished run %d: time: %sms", runs, timeInMs);
		else LOGGER.error.println("Finished run %d: sorted incorrectly", runs);
	}
	
	private static void logFailedRun(List<Integer> unordered, List<Integer> sorted, List<Integer> test) {
		LOGGER.error.println("Given:    %s", unordered);
		LOGGER.error.println("Result:   %s", test);
		LOGGER.error.println("Expected: %s", sorted);
	}
	
	public static List<Integer> randomBenchmarkList() {
		List<Integer> list = new ArrayList<>();
		
		for (Integer i = benchmarkListSize.get(); i > 0; i--) {
			list.add((int) (Math.random() * 100));
		}
		
		return list;
	}
	
	public static List<Integer> randomList(int size) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = size; i > 0; i--) {
			list.add((int) (Math.random() * 100));
		}
		
		return list;
	}
	
	private Benchmark() {}
}