package com.wokdsem.koncurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

import static com.wokdsem.koncurrent.Callables.getIntCallable;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RunnableActionsTest {
	
	@Test
	public void run_normalExecutor_onResultCalled() {
		int value = 5;
		final Reference<Integer> integerReference = new Reference<>();
		new RunnableAction<>(getIntCallable(value), new SimpleActionListener<Integer>() {
			@Override
			public void onResult(Integer result) {
				integerReference.setReference(result);
			}
		}).run();
		assertThat(integerReference.getReference(), is(value));
	}
	
	@Test
	public void run_onActionThrowAnException_onErrorCalled() {
		final NullPointerException npe = new NullPointerException();
		final Reference<Throwable> throwableReference = new Reference<>();
		new RunnableAction<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				throw npe;
			}
		}, new SimpleActionListener<Integer>() {
			@Override
			public void onError(Throwable cause) {
				throwableReference.setReference(cause);
			}
		}).run();
		assertThat(npe, equalTo(throwableReference.getReference()));
	}
	
	@Test
	public void run_tryToRunOnCancelledState_listenerIsNotCalled() {
		RunnableAction.ActionListener<Integer> failListener = RunnableActionsTest.getFailListener();
		RunnableAction<Integer> runnableAction = new RunnableAction<>(getIntCallable(5), failListener);
		runnableAction.cancel();
		runnableAction.run();
	}
	
	@Test
	public void cancel_whileRunning_listenerIsNotCalled() throws InterruptedException {
		final CountDownLatch inputLatch = new CountDownLatch(1);
		final CountDownLatch outputLatch = new CountDownLatch(1);
		RunnableAction<Void> runnableAction = new RunnableAction<>(new Callable<Void>() {
			@Override
			public synchronized Void call() throws Exception {
				try {
					inputLatch.countDown();
					wait(500);
					fail();
				} catch (InterruptedException ignored) {
				}
				outputLatch.countDown();
				return null;
			}
		}, RunnableActionsTest.<Void>getFailListener());
		newFixedThreadPool(1).execute(runnableAction);
		inputLatch.await();
		runnableAction.cancel();
		boolean releasedByRunnable = outputLatch.await(1_000, TimeUnit.MILLISECONDS);
		assertThat(releasedByRunnable, is(true));
	}
	
	private static <T> RunnableAction.ActionListener<T> getFailListener() {
		return new RunnableAction.ActionListener<T>() {
			@Override
			public void onResult(T result) {
				fail();
			}
			
			@Override
			public void onError(Throwable cause) {
				fail();
			}
		};
	}
	
	private static class SimpleActionListener<T> implements RunnableAction.ActionListener<T> {
		
		@Override
		public void onResult(T result) {
			// do nothing
		}
		
		@Override
		public void onError(Throwable cause) {
			//	do nothing
		}
	}
	
}
