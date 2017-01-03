package com.wokdsem.koncurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class Koncurrent {
	
	private final Executor executor;
	
	private Koncurrent(Executor executor) {
		this.executor = executor;
	}
	
	public static Koncurrent newInstance() {
		ThreadFactory threadFactory = new KoncurrentThreadFactory();
		ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
		return new Koncurrent(executorService);
	}
	
	public <R> Results<R> execute(List<? extends Callable<R>> tasks) throws ExecutionException {
		List<R> results = KoncurrentRunner.execute(tasks, executor);
		return new Results<>(results);
	}
	
	public <R1, R2> Results.Result2<R1, R2> execute(Callable<R1> task1, Callable<R2> task2) throws ExecutionException {
		List results = internalExecution(task1, task2);
		return new Results.Result2<>(results);
	}
	
	public <R1, R2, R3> Results.Result3<R1, R2, R3> execute(Callable<R1> task1, Callable<R2> task2,
		Callable<R3> tasks3) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3);
		return new Results.Result3<>(results);
	}
	
	public <R1, R2, R3, R4> Results.Result4<R1, R2, R3, R4> execute(Callable<R1> task1, Callable<R2> task2,
		Callable<R3> tasks3, Callable<R4> task4) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4);
		return new Results.Result4<>(results);
	}
	
	public <R1, R2, R3, R4, R5> Results.Result5<R1, R2, R3, R4, R5> execute(Callable<R1> task1, Callable<R2> task2,
		Callable<R3> tasks3, Callable<R4> task4, Callable<R5> task5) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4, task5);
		return new Results.Result5<>(results);
	}
	
	public <R1, R2, R3, R4, R5, R6> Results.Result6<R1, R2, R3, R4, R5, R6> execute(Callable<R1> task1,
		Callable<R2> task2, Callable<R3> tasks3, Callable<R4> task4, Callable<R5> task5,
		Callable<R6> task6) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4, task5, task6);
		return new Results.Result6<>(results);
	}
	
	public <R1, R2, R3, R4, R5, R6, R7> Results.Result7<R1, R2, R3, R4, R5, R6, R7> execute(Callable<R1> task1,
		Callable<R2> task2, Callable<R3> tasks3, Callable<R4> task4, Callable<R5> task5, Callable<R6> task6,
		Callable<R7> task7) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4, task5, task6, task7);
		return new Results.Result7<>(results);
	}
	
	public <R1, R2, R3, R4, R5, R6, R7, R8> Results.Result8<R1, R2, R3, R4, R5, R6, R7, R8> execute(Callable<R1> task1,
		Callable<R2> task2, Callable<R3> tasks3, Callable<R4> task4, Callable<R5> task5, Callable<R6> task6,
		Callable<R7> task7, Callable<R8> task8) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4, task5, task6, task7, task8);
		return new Results.Result8<>(results);
	}
	
	public <R1, R2, R3, R4, R5, R6, R7, R8, R9> Results.Result9<R1, R2, R3, R4, R5, R6, R7, R8, R9> execute(
		Callable<R1> task1, Callable<R2> task2, Callable<R3> tasks3, Callable<R4> task4, Callable<R5> task5,
		Callable<R6> task6, Callable<R7> task7, Callable<R8> task8, Callable<R9> task9) throws ExecutionException {
		List results = internalExecution(task1, task2, tasks3, task4, task5, task6, task7, task8, task9);
		return new Results.Result9<>(results);
	}
	
	@SuppressWarnings("unchecked")
	private List<?> internalExecution(Callable... tasks) throws ExecutionException {
		List<Callable<Object>> taskList = new ArrayList<>(tasks.length);
		for (Callable<?> task : tasks) {
			taskList.add((Callable<Object>) task);
		}
		return KoncurrentRunner.execute(taskList, executor);
	}
	
	private static class KoncurrentThreadFactory implements ThreadFactory {
		
		private static final AtomicLong threadCounter = new AtomicLong(0);
		
		@Override
		public Thread newThread(Runnable r) {
			String threadName = "KoncurrentExecutor #" + threadCounter.incrementAndGet();
			return new Thread(r, threadName);
		}
		
	}
	
}
