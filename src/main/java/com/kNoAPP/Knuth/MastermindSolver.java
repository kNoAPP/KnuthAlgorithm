package com.kNoAPP.Knuth;

import java.util.ArrayList;
import java.util.List;

public class MastermindSolver {

	private List<String> possibilities = new ArrayList<String>();
	private int pins, lower, upper;
	
	public MastermindSolver(int pins, int lower, int upper) {
		this.pins = pins;
		
		this.lower = Math.min(lower, upper);
		this.upper = Math.max(lower, upper);
		
		reset();
	}
	
	private static List<String> possibilities(int pins, int lower, int upper) {
		List<String> possibilities = new ArrayList<String>();
		if(pins == 0) return possibilities;
		else if(pins == 1) {
			for(int i=lower; i<=upper; i++) possibilities.add(i+"");
			return possibilities;
		} else {
			for(String s : possibilities(pins - 1, lower, upper)) for(int i=lower; i<=upper; i++) possibilities.add(s+i);
			return possibilities;
		}
	}
	
	public List<String> getPossibilities() {
		return possibilities;
	}
	
	/**
	 * Send a result in.
	 * @param guess - What sequence did you try?
	 * @param result - Format should be BBWW or bbww
	 */
	public void sendResult(String guess, String result) {
		List<String> toRemove = new ArrayList<String>();
		for(String code : possibilities) if(!getResult(guess, code).equalsIgnoreCase(result)) toRemove.add(code);
		possibilities.removeAll(toRemove);
	}
	
	public String tryNext() {
		if(possibilities.size() > 0) return possibilities.get(0);
		else return "NOTHING";
	}
	
	public boolean hasFound() {
		return possibilities.size() == 1;
	}
	
	public static String getResult(String guess, String code) {
		char[] g = guess.toCharArray();
		char[] c = code.toCharArray();
		String result = "";
		
		for(int i=0; i<Math.min(guess.length(), code.length()); i++) {
			if(g[i] == c[i]) {
				result += "B";
				g[i] = 'X';
				c[i] = 'Y';
			}
		}
		for(int x=0; x<guess.length(); x++) {
			for(int y=0; y<code.length(); y++) {
				if(g[x] == c[y] && x != y) {
					result += "W";
					g[x] = 'X';
					c[y] = 'Y';
				}
			}
		}
		return result;
	}
	
	public void reset() {
		this.possibilities = possibilities(pins, lower, upper);
	}
}
