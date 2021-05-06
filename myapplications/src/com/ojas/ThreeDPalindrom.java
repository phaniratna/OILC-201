package com.ojas;

public class ThreeDPalindrom {

	static boolean isPalindrome(int num) {
		return num % 10 == num / 100;
	}
	public static void main(String[] args) {
         System.out.println(isPalindrome(Integer.parseInt(args[0])));


	}

}
