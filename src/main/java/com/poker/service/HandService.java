package com.poker.service;

import com.poker.model.Card;
import com.poker.model.Hand;

public interface HandService {
	
	boolean isPair(Card[] cards);
	
	boolean isTwoPair(Card[] cards);
	
	boolean isThreeOfAKind(Card[] cards);
	
	boolean isStraight(Card[] cards);
	
	boolean isFlush(Card[] cards);
	
	boolean isFullHouse(Card[] cards);
	
	boolean isFourOfAKind(Card[] cards);
	
	boolean isStraightFlush(Card[] cards);
	
	public  String getwinner(Hand hand1, Hand hand2);
	

}
