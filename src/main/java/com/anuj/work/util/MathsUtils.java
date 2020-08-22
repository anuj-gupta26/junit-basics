package com.anuj.work.util;

/**
 * @author Anuj Gupta
 * 
 * MathsUtils class to perform basic operations 
*/
public class MathsUtils {

	
	/**
	 * Add Two numbers and return result
	 * 
	 * @param a
	 * @param b
	 * @return int
	 */
	public int add(int a, int b) {
		return a+b;
	}
	
	/**
	 * Multiply two numbers and reult result
	 * 
	 * Note: checking if the protected method will be available to the Test class 
	 * 
	 * @param x
	 * @param y
	 * @return int
	 */
	protected int multiply(int x, int y) {
		return x*y;
	}
	
	
	/**
	 * Perform division of two numbers and return result
	 * 
	 * @param m
	 * @param n
	 * @return int
	 */
	public int divide(int m, int n) {
		return m/n;
	}
}
