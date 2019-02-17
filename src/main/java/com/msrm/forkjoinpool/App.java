package com.msrm.forkjoinpool;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

/**
 * ForkJoinPool is based on "work-stealing algorithm" - threads that run out of
 * tasks can “steal” work from other busy threads
 * 
 * @author srirammuthaiah
 *
 */
public class App {

	public static void main(String[] args) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		BigInteger result = pool.invoke(new FactorialTask(1, 50));
		System.out.println(result);
	}

}
