package com.ojas;

public class SumOfTwo {

	static String calTotal(int firstNum,int secNum){
		return "SUM = " + ( firstNum + secNum);
	}
	static boolean isEven(int num) {
		boolean b = false;
		if( num % 2 == 0){
			b = false;
		}
		return b;
	}
	public static void main(String[] args) {
		
		System.out.println(calTotal(10,20));

	}

}
