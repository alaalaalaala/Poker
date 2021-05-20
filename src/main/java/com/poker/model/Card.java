package com.poker.model;


public class Card implements Comparable<Card> {
	private int rank;
	private int value;
	private char suit;
	private final String values = "23456789TJQKA";
	private final String suits = "CDHS";
	
	public Card() {
		
	}
	
	public Card(String str){
		rank = ranking(str);
		value = rank/suits.length();
		suit = suits.charAt(rank%suits.length());
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getValue(){
		return value;
	}
	
	public char getSuit(){
		return suit;
	}
	
	public int ranking(String str){
		char value = str.charAt(0);
		char suit = str.charAt(1);
		for (int i=0;i<values.length();i++){
			if (values.charAt(i) == value){
				for (int j=0;j<suits.length();j++){
					if (suits.charAt(j) == suit) return (i+2)*suits.length() + j;
				}
			}
		}
		
		return -1;
	}
	
	
	@Override
	public int compareTo(Card anotherCard) {
		if (this.getValue()<anotherCard.getValue()) return -1;
		else if (this.getValue()>anotherCard.getValue()) return 1;
		else return 0;
	}
		
}

