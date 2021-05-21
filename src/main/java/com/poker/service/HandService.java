package com.poker.service;

import com.poker.model.Card;
import com.poker.model.Hand;

public interface HandService {
	
	boolean pair(Card[] cards);
	
	boolean twoPair(Card[] cards);
	
	boolean threeOfAKind(Card[] cards);
	
	boolean straight(Card[] cards);
	
	boolean flush(Card[] cards);
	
	boolean fullHouse(Card[] cards);
	
	boolean fourOfAKind(Card[] cards);
	
	boolean straightFlush(Card[] cards);
	
	public  String getwinner(Hand hand1, Hand hand2);
	

}
