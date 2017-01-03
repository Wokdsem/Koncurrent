package com.wokdsem.koncurrent;

import java.util.concurrent.Callable;

class RunnableAction<T> implements Runnable {
	
	private enum RunnableState {
		NEW,
		RUNNING,
		COMPLETED,
		CANCELED
	}
	
	private Callable<T> task;
	private ActionListener<T> listener;
	private Thread executor;
	private RunnableState state;
	
	RunnableAction(Callable<T> task, ActionListener<T> listener) {
		this.task = task;
		this.listener = listener;
		this.state = RunnableState.NEW;
	}
	
	@Override
	public final void run() {
		Callable<T> runnableAction;
		synchronized (this) {
			if (state != RunnableState.NEW) {
				return;
			}
			runnableAction = task;
			executor = Thread.currentThread();
			state = RunnableState.RUNNING;
		}
		try {
			T result = runnableAction.call();
			deliverResponse(result);
		} catch (Throwable e) {
			deliverError(e);
		} finally {
			synchronized (this) {
				task = null;
				listener = null;
				executor = null;
			}
		}
	}
	
	private synchronized void deliverResponse(final T response) {
		if (state != RunnableState.CANCELED) {
			listener.onResult(response);
			afterExecuted();
		}
	}
	
	private synchronized void deliverError(final Throwable e) {
		if (state != RunnableState.CANCELED) {
			listener.onError(e);
			afterExecuted();
		}
	}
	
	private void afterExecuted() {
		state = RunnableState.COMPLETED;
	}
	
	synchronized void cancel() {
		if (state == RunnableState.NEW || state == RunnableState.RUNNING) {
			if (executor != null) {
				executor.interrupt();
			}
			task = null;
			listener = null;
			state = RunnableState.CANCELED;
		}
	}
	
	interface ActionListener<T> {
		
		void onResult(T result);
		
		void onError(Throwable cause);
		
	}
	
}
