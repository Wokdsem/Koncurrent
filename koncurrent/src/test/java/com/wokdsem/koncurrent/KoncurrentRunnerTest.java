package com.wokdsem.koncurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Test;

import static com.wokdsem.koncurrent.toolbox.Callables.getIntCallable;
import static com.wokdsem.koncurrent.KoncurrentRunner.execute;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class KoncurrentRunnerTest {
	
	@Test
	public void executeMultipleAll_nullInput_IllegalArgumentExceptionThrown() throws ExecutionException {
		try {
			execute(null, getExecutor());
			fail();
		} catch (IllegalArgumentException ignored) {
		}
	}
	
	@Test
	public void executeMultipleAll_emptyInput_emptyMultipleResults() throws ExecutionException {
		List<Callable<Integer>> task = new ArrayList<>();
		List<Integer> results = execute(task, getExecutor());
		assertThat(results.size(), is(0));
	}
	
	@Test
	public void executeCallableList_fiftyIntCallables_resultsSizeIsFifty() throws ExecutionException {
		int size = 50;
		List<Callable<Integer>> tasks = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			tasks.add(getIntCallable(i));
		}
		List<Integer> results = execute(tasks, getExecutor());
		assertThat(results.size(), is(size));
	}
	
	@Test
	public void execute_integerCallable_resultValueRecovered() throws ExecutionException {
		int value = 5;
		List<Callable<Integer>> tasks = singletonList(getIntCallable(value));
		List<Integer> results = execute(tasks, getExecutor());
		assertThat(results.get(0), is(value));
	}
	
	@Test
	public void execute_onFailureCallable_executionExceptionThrown() {
		List<? extends Callable<Integer>> tasks = singletonList(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				throw new NullPointerException();
			}
		});
		try {
			execute(tasks, getExecutor());
			fail();
		} catch (ExecutionException ignored) {
		}
	}
	
	@Test
	public void execute_onEmptyInput_resultSizeIsZero() throws ExecutionException {
		List<String> results = execute(Collections.<Callable<String>>emptyList(), getExecutor());
		assertThat(results.size(), is(0));
	}
	
	private Executor getExecutor() {
		return Executors.newCachedThreadPool();
	}
	
}
