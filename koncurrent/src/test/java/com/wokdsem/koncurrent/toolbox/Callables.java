package com.wokdsem.koncurrent.toolbox;

import java.util.concurrent.Callable;

public class Callables {
	
	private Callables() {
	}
	
	public static Callable<Integer> getIntCallable(final int value) {
		return new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return value;
			}
		};
	}
	
}
