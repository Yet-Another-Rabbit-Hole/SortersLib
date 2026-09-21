package net.YaRh.SortersLib.benchmark;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.ConvConf.Switch;
import net.YaRh.SortersLib.algorithms.sort.SortAlgorithm;
import net.YaRh.VisualSort.VisualList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.YaRh.SortersLib.Config.benchmarkSetSize;
import static net.YaRh.SortersLib.Config.benchmarkRepeat;

/**
 * @since 1.0.0
 */
public class SortingBenchmark {
	public static final Logger LOGGER = new Logger("SortingBenchmark");
	
	static {
		LOGGER.disable();
	}
	
	public static final Switch visual = new Switch(false);
	
	private static SortAlgorithm algorithm = null;
	
	public static long medianTime = -1L;
	public static double successRate = -1L;
	
	private static int runs = 0;
	private static double success = 0;
	
	public static void setAlgorithm(SortAlgorithm pAlgorithm) {
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
		
		assertRun(test, unordered, sorted, time);
		
		if (medianTime == -1L) medianTime = time;
		else {
			medianTime += time;
			medianTime /= runs;
		}
	}
	
	private static void assertRun(List<Integer> test, List<Integer> unordered, List<Integer> sorted, long time) {
		boolean isSorted = test.equals(sorted);
		boolean isMbySorted = isSorted(test);
		
		if (isSorted) logSuccessfulResult(time);
		else if (isMbySorted) logSmwhtSuccRun(sorted.size(), test);
		else logFailedRun(unordered, sorted, test);
		
		if (isSorted) success++;
		else if (isMbySorted) success += .5D;
	}
	
	private static void logResult() {
		LOGGER.log.println("Benchmark finished");
		LOGGER.info.println("Median time: %sms", medianTime / 1_000_000L);
		LOGGER.info.println("Across %d benchmark(s)", benchmarkRepeat.get());
		LOGGER.info.println("With a success rate of %s", successRate);
		LOGGER.info.println("With %d ints per list", benchmarkSetSize.get());
	}
	
	private static void logSuccessfulResult(long duration) {
		long timeInMs = duration / 1_000_000L;
		LOGGER.log.println("Successfully sorted");
		LOGGER.info.println("Finished run %d: time: %sms", runs, timeInMs);
	}
	
	private static void logSmwhtSuccRun(int expectedSize, List<Integer> result) {
		LOGGER.error.println("Not sorted valid but returned ordered list");
		LOGGER.debug.println("Result: %s", result);
		LOGGER.info.println("The expected size was %s, the result had a size of %s", expectedSize, result.size());
	}
	
	private static void logFailedRun(List<Integer> unordered, List<Integer> sorted, List<Integer> test) {
		LOGGER.error.println("Failed to sort correctly");
		LOGGER.debug.println("Given:    %s", unordered);
		LOGGER.debug.println("Result:   %s", test);
		LOGGER.debug.println("Expected: %s", sorted);
	}
	
	public static List<Integer> randomBenchmarkList() {
		return randomList(benchmarkSetSize.get());
	}
	
	public static boolean isSorted(List<Integer> list) {
		for (int i = 1; i < list.size(); i++)
			if (list.get(i-1) > list.get(i))
				return false;
		
		return true;
	}
	
	public static List<Integer> randomList(int size) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = size; i > 0; i--) {
			list.add((int) (Math.random() * 100));
		}
		
		return list;
	}
	
	private SortingBenchmark() {}
}