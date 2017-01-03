package com.wokdsem.koncurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

class KoncurrentRunner<T> {
	
	private final List<? extends Callable<T>> tasks;
	private final List<RunnableAction> actions;
	private final Executor executor;
	private final Object[] results;
	private final CountDownLock lock;
	private Throwable errCause;
	
	private KoncurrentRunner(List<? extends Callable<T>> tasks, Executor executor) {
		this.tasks = tasks;
		this.actions = new ArrayList<>(tasks.size());
		this.executor = executor;
		this.results = new Object[tasks.size()];
		lock = new CountDownLock(tasks.size());
	}
	
	static <T> List<T> execute(List<? extends Callable<T>> tasks, Executor executor) throws ExecutionException {
		assertTask(tasks);
		return new KoncurrentRunner<>(tasks, executor).run();
	}
	
	private static <T> void assertTask(List<? extends Callable<T>> tasks) {
		if (tasks == null) {
			throw new IllegalArgumentException("Koncurrent: Null tasks input is not allowed.");
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<T> run() throws ExecutionException {
		int index = -1;
		for (Callable<T> task : tasks) {
			index++;
			RunnableAction<T> action = buildAction(task, index);
			actions.add(action);
			executor.execute(action);
		}
		waitResponses();
		return (List<T>) Arrays.asList(results);
	}
	
	private RunnableAction<T> buildAction(Callable<T> task, final int index) {
		return new RunnableAction<>(task, new RunnableAction.ActionListener<T>() {
			@Override
			public void onResult(T result) {
				results[index] = result;
				lock.countDown();
			}
			
			@Override
			public void onError(Throwable cause) {
				onExecutionError(cause);
			}
		});
	}
	
	private synchronized void onExecutionError(Throwable cause) {
		if (this.errCause == null) {
			this.errCause = cause;
			lock.releaseLock();
		}
	}
	
	private void waitResponses() throws ExecutionException {
		try {
			if (lock.await()) {
				return;
			}
		} catch (InterruptedException e) {
			errCause = e;
		}
		for (RunnableAction action : actions) {
			action.cancel();
		}
		throw new ExecutionException(errCause);
	}
	
}
