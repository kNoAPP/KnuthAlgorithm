package com.kNoAPP.Knuth;

import java.util.Scanner;

public class Knuth {
	
	public static void main(String[] args) {
		MastermindSolver ms = new MastermindSolver(4, 1, 6);
		String guess = "";
		String result = "";
		Scanner reader = new Scanner(System.in);
		
		while(true) {
			System.out.print("What did you try? -> ");
			guess = reader.nextLine();
			System.out.print("What did you get? -> ");
			result = reader.nextLine();
			
			ms.sendResult(guess, result);
			if(!ms.hasFound()) System.out.println("Try " + ms.tryNext() + ".");
			else break;
		}
		
		System.out.println("It should be " + ms.tryNext() + ".");
		reader.close();
	}
}
