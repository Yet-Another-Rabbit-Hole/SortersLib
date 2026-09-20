package net.YaRh.SortersLib.benchmark;

import net.YaRh.CheapLog.logging.Logger;
import net.YaRh.ConvConf.Switch;
import net.YaRh.SortersLib.algorithms.search.SearchAlgorithm;

import java.text.NumberFormat;
import java.util.*;

import static net.YaRh.SortersLib.Config.benchmarkSetSize;
import static net.YaRh.SortersLib.Config.benchmarkRepeat;

/**
 * @since 1.0.0
 */
public class SearchingBenchmark {
	private static final Logger LOGGER = new Logger("SearchingBenchmark");
	
	private static SearchAlgorithm algorithm = null;
	
	public static long averageSearchTime = -1L;
	public static long averageSetupTime = -1L;
	public static long averageChecks = -1L;
	public static double successRate = -1L;
	
	private static int runs = 0;
	private static double success = 0;
	private static int checks = 0;
	private static long setupTime = 0;
	private static long searchTime = 0;
	
	public static void setAlgorithm(SearchAlgorithm pAlgorithm) {
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
		searchTime = 0;
		setupTime = 0;
		checks = 0;
		averageSearchTime = -1L;
		averageSetupTime = -1L;
		successRate = -1L;
		
		for (Integer i = benchmarkRepeat.get(); i > 0; i--)
			benchmarkRun();
		
		successRate = success / runs;
		averageSearchTime = searchTime / runs;
		averageSetupTime = setupTime / runs;
		averageChecks = checks / runs;
		
		logResult();
	}
	
	private static void benchmarkRun() {
		LOGGER.log.println("Starting benchmark run");
		runs++;
		
		long end;
		long start;
		
		List<Integer> set = randomBenchmarkSet();
		
		start = System.nanoTime();
		algorithm.setup(set);
		end = System.nanoTime();
		long setupTime = end - start;
		
		int targetIndex = (int) (Math.random() * 10) % set.size();
		int targetValue = set.get(targetIndex);
		
		SearchingList searchSet = new SearchingList(set);
		
		start = System.nanoTime();
		Optional<Integer> result = algorithm.search(searchSet, targetValue);
		end = System.nanoTime();
		long searchTime = end - start;
		
		boolean found;
		found = result
				.filter(integer -> integer == targetIndex)
				.isPresent();
		
		logRunResult(searchTime, setupTime, found, searchSet.getAccess());
		if (!found) logFailedRun(targetValue, targetIndex, result);
		
		checks += searchSet.getAccess();
		
		SearchingBenchmark.searchTime += searchTime;
		SearchingBenchmark.setupTime += setupTime;
		if (found) success++;
	}
	
	private static void logResult() {
		LOGGER.log.println("Benchmark finished");
		LOGGER.info.println("Average setup time: %sms", averageSetupTime / 1_000_000L);
		LOGGER.info.println("Average search time: %sms", averageSearchTime / 1_000_000L);
		LOGGER.info.println("Across %d benchmark(s)", benchmarkRepeat.get());
		LOGGER.info.println("With a success rate of %s", NumberFormat.getPercentInstance().format(successRate));
		LOGGER.info.println("With an average of %s checks", averageChecks);
		LOGGER.info.println("With %d ints per list", benchmarkSetSize.get());
	}
	
	private static void logRunResult(long searchTime, long setupTime, boolean found, int checks) {
		long searchTimeMs = searchTime / 1_000_000L;
		long setupTimeMs = setupTime / 1_000_000L;
		if (found) LOGGER.info.println("Finished run %d: search time: %sms setup time: %sms checks: %s",
		                               runs, searchTimeMs, setupTimeMs, checks);
		else LOGGER.error.println("Finished run %d: failed", runs);
	}
	
	private static void logFailedRun(int targetValue, int targetIndex, Optional<Integer> result) {
		LOGGER.error.println("Given:    %s", targetValue);
		LOGGER.error.println("Result:   %s", (result.isEmpty()) ? "null" : result.get());
		LOGGER.error.println("Expected: %s", targetIndex);
	}
	
	public static List<Integer> randomBenchmarkSet() {
		return randomSet(benchmarkSetSize.get());
	}
	
	public static List<Integer> randomSet(int size) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = size; i > 0; i--)
			list.add((int) (Math.random() * 100));
		
		list = new ArrayList<>(list.stream().distinct().toList());
		
		return list;
	}
	
	private SearchingBenchmark() {}
}

class SearchingList extends ArrayList<Integer> {

	private int access = 0;
	
	public SearchingList(List<Integer> list) {
		super(list);
	}
	
	@Override
	public Integer get(int index) {
		access++;
		return super.get(index);
	}
	
	public int getAccess() {
		return access;
	}
}