package com.wokdsem.koncurrent;

import java.util.List;

public class Results<R> {
	
	private final List<R> results;
	
	Results(List<R> results) {
		this.results = results;
	}
	
	public R get(int position) {
		return results.get(position);
	}
	
	public int size() {
		return results.size();
	}
	
	@SuppressWarnings("unchecked")
	public static class Result2<R1, R2> {
		
		public final R1 first;
		public final R2 second;
		
		Result2(List results) {
			first = (R1) results.get(0);
			second = (R2) results.get(1);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result3<R1, R2, R3> extends Result2<R1, R2> {
		
		public final R3 third;
		
		Result3(List results) {
			super(results);
			this.third = (R3) results.get(2);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result4<R1, R2, R3, R4> extends Result3<R1, R2, R3> {
		
		public final R4 fourth;
		
		Result4(List results) {
			super(results);
			this.fourth = (R4) results.get(3);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result5<R1, R2, R3, R4, R5> extends Result4<R1, R2, R3, R4> {
		
		public final R5 fifth;
		
		Result5(List results) {
			super(results);
			this.fifth = (R5) results.get(4);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result6<R1, R2, R3, R4, R5, R6> extends Result5<R1, R2, R3, R4, R5> {
		
		public final R6 sixth;
		
		Result6(List results) {
			super(results);
			this.sixth = (R6) results.get(5);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result7<R1, R2, R3, R4, R5, R6, R7> extends Result6<R1, R2, R3, R4, R5, R6> {
		
		public final R7 seventh;
		
		Result7(List results) {
			super(results);
			this.seventh = (R7) results.get(6);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result8<R1, R2, R3, R4, R5, R6, R7, R8> extends Result7<R1, R2, R3, R4, R5, R6, R7> {
		
		public final R8 eighth;
		
		Result8(List results) {
			super(results);
			this.eighth = (R8) results.get(7);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static class Result9<R1, R2, R3, R4, R5, R6, R7, R8, R9> extends Result8<R1, R2, R3, R4, R5, R6, R7, R8> {
		
		public final R9 ninth;
		
		Result9(List results) {
			super(results);
			this.ninth = (R9) results.get(8);
		}
		
	}
	
}
