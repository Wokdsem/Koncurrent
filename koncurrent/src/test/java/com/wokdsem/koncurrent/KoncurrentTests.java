package com.wokdsem.koncurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

import static com.wokdsem.koncurrent.toolbox.Callables.getIntCallable;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KoncurrentTests {
	
	private final Koncurrent koncurrent;
	
	public KoncurrentTests() {
		this.koncurrent = Koncurrent.newInstance();
	}
	
	@Test
	public void execute_tasksList_executed() throws ExecutionException {
		int size = 25;
		ArrayList<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			tasks.add(getIntCallable(i));
		}
		Results<Integer> results = koncurrent.execute(tasks);
		for (int i = 0; i < size; i++) {
			assertThat(results.get(i), is(i));
		}
	}
	
	@Test
	public void execute_tasks2_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Results.Result2<Integer, Integer> result2 = koncurrent.execute(task1, task2);
		assertThat(result2.first, is(1));
		assertThat(result2.second, is(2));
	}
	
	@Test
	public void execute_tasks3_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Results.Result3<Integer, Integer, Integer> result3 = koncurrent.execute(task1, task2, task3);
		assertThat(result3.first, is(1));
		assertThat(result3.second, is(2));
		assertThat(result3.third, is(3));
	}
	
	@Test
	public void execute_tasks4_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Results.Result4<Integer, Integer, Integer, Integer> result4 = koncurrent.execute(task1, task2, task3, task4);
		assertThat(result4.first, is(1));
		assertThat(result4.second, is(2));
		assertThat(result4.third, is(3));
		assertThat(result4.fourth, is(4));
	}
	
	@Test
	public void execute_tasks5_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Callable<Integer> task5 = getIntCallable(5);
		Results.Result5<Integer, Integer, Integer, Integer, Integer> result5 = koncurrent.execute(task1, task2, task3,
																								  task4, task5);
		assertThat(result5.first, is(1));
		assertThat(result5.second, is(2));
		assertThat(result5.third, is(3));
		assertThat(result5.fourth, is(4));
		assertThat(result5.fifth, is(5));
	}
	
	@Test
	public void execute_tasks6_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Callable<Integer> task5 = getIntCallable(5);
		Callable<Integer> task6 = getIntCallable(6);
		Results.Result6<Integer, Integer, Integer, Integer, Integer, Integer> result6 = koncurrent.execute(task1, task2,
																										   task3, task4,
																										   task5,
																										   task6);
		assertThat(result6.first, is(1));
		assertThat(result6.second, is(2));
		assertThat(result6.third, is(3));
		assertThat(result6.fourth, is(4));
		assertThat(result6.fifth, is(5));
		assertThat(result6.sixth, is(6));
	}
	
	@Test
	public void execute_tasks7_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Callable<Integer> task5 = getIntCallable(5);
		Callable<Integer> task6 = getIntCallable(6);
		Callable<Integer> task7 = getIntCallable(7);
		Results.Result7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> result7 = koncurrent.execute(
			task1, task2, task3, task4, task5, task6, task7);
		assertThat(result7.first, is(1));
		assertThat(result7.second, is(2));
		assertThat(result7.third, is(3));
		assertThat(result7.fourth, is(4));
		assertThat(result7.fifth, is(5));
		assertThat(result7.sixth, is(6));
		assertThat(result7.seventh, is(7));
	}
	
	@Test
	public void execute_tasks8_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Callable<Integer> task5 = getIntCallable(5);
		Callable<Integer> task6 = getIntCallable(6);
		Callable<Integer> task7 = getIntCallable(7);
		Callable<Integer> task8 = getIntCallable(8);
		Results.Result8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> result8 = koncurrent.execute(
			task1, task2, task3, task4, task5, task6, task7, task8);
		assertThat(result8.first, is(1));
		assertThat(result8.second, is(2));
		assertThat(result8.third, is(3));
		assertThat(result8.fourth, is(4));
		assertThat(result8.fifth, is(5));
		assertThat(result8.sixth, is(6));
		assertThat(result8.seventh, is(7));
		assertThat(result8.eighth, is(8));
	}
	
	@Test
	public void execute_tasks9_executed() throws ExecutionException {
		Callable<Integer> task1 = getIntCallable(1);
		Callable<Integer> task2 = getIntCallable(2);
		Callable<Integer> task3 = getIntCallable(3);
		Callable<Integer> task4 = getIntCallable(4);
		Callable<Integer> task5 = getIntCallable(5);
		Callable<Integer> task6 = getIntCallable(6);
		Callable<Integer> task7 = getIntCallable(7);
		Callable<Integer> task8 = getIntCallable(8);
		Callable<Integer> task9 = getIntCallable(9);
		Results.Result9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> result9 = koncurrent.execute(
			task1, task2, task3, task4, task5, task6, task7, task8, task9);
		assertThat(result9.first, is(1));
		assertThat(result9.second, is(2));
		assertThat(result9.third, is(3));
		assertThat(result9.fourth, is(4));
		assertThat(result9.fifth, is(5));
		assertThat(result9.sixth, is(6));
		assertThat(result9.seventh, is(7));
		assertThat(result9.eighth, is(8));
		assertThat(result9.ninth, is(9));
	}
	
}
