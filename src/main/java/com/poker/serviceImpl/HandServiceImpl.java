package com.poker.serviceImpl;

import com.poker.enums.HandRank;
import com.poker.model.Card;
import com.poker.model.Hand;
import com.poker.service.HandService;

public class HandServiceImpl implements HandService {
	
	Hand hand= new Hand();
	
	private HandRank handRank;

	@Override
	public boolean isPair(Card[] cards) {
		int card1 = cards[0].getValue();
		int card2 = cards[1].getValue();
		int card3 = cards[2].getValue();
		int card4 = cards[3].getValue();
		int card5 = cards[4].getValue();
		
		return ((card1 == card2 && card2 != card3) ||
				(card2 == card3 && card3 != card4 && card2 != card1) ||
				(card3 == card4 && card4 != card5 && card3 != card2) ||
				(card4 == card5 && card4 != card3));
	}

	@Override
	public boolean isTwoPair(Card[] cards) {
		int card1 = cards[0].getValue();
		int card2 = cards[1].getValue();
		int card3 = cards[2].getValue();
		int card4 = cards[3].getValue();
		int card5 = cards[4].getValue();
		
		return ((card1 == card2 && card3 == card4 && card2!=card3 && card4 != card5) ||
				(card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5)
				);
	}

	@Override
	public boolean isThreeOfAKind(Card[] cards) {
		int card1 = cards[0].getValue();
		int card2 = cards[1].getValue();
		int card3 = cards[2].getValue();
		int card4 = cards[3].getValue();
		int card5 = cards[4].getValue();
		
		return (card1 == card3 || card2 == card4 || card3 == card5);
	}

	@Override
	public boolean isStraight(Card[] cards) {
		return cards[4].getValue() - cards[0].getValue() == 4;
	}

	@Override
	public boolean isFlush(Card[] cards) {
		for (int i=0;i<cards.length-1;i++){
			if (cards[i+1].getSuit() != cards[i].getSuit()) return false;
		}
		
		return true;
	}

	@Override
	public boolean isFullHouse(Card[] cards) {
		int card1 = cards[0].getValue();
		int card2 = cards[1].getValue();
		int card3 = cards[2].getValue();
		int card4 = cards[3].getValue();
		int card5 = cards[4].getValue();
		
		return ((card1 == card2 && card2 != card3 && card3 == card5) ||
				(card1 == card3 && card3 != card4 && card4 == card5));
	}

	@Override
	public boolean isFourOfAKind(Card[] cards) {
		return (cards[0].getValue() == cards[3].getValue() ||
				cards[1].getValue() == cards[4].getValue());
	}

	@Override
	public boolean isStraightFlush(Card[] cards) {
		return isStraight(cards) && isFlush(cards);
	}

	public String getwinner(Hand hand1, Hand hand2){
		HandRank hand1Rank = getHandRank(hand1);
		HandRank hand2Rank = getHandRank(hand2);
		int cmp = hand1Rank.compareTo(hand2Rank);
		if (cmp>0) return hand1.getName();
		else if (cmp<0) return hand2.getName();
		else{
			switch (hand1Rank){
				case STRAIGHTFLUSH : 
					cmp = compareHighCard(hand1,hand2);
					break;
				case FOUROFAKIND :
					cmp = compareFourOfAKind(hand1).compareTo(compareFourOfAKind(hand2));
					break;
				case FULLHOUSE :
					cmp = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
					break;
				case FLUSH :
					cmp = compareRecursiveHighCard(hand1,hand2);
					break;
				case STRAIGHT :
					cmp = compareHighCard(hand1,hand2);
					break;
				case THREEOFAKIND :
					cmp = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
					break;
					// **XX-  **-XX  **XX-
					//
				case TWOPAIR :
					cmp = compareTwoPair(hand1,hand2);
					break;
				case PAIR :
					break;
				case HIGHCARD :
					cmp = compareRecursiveHighCard(hand1,hand2);
					break;
			}
		}
		
		return cmp>0?hand1.getName():cmp<0?hand2.getName():"Tie.";
	}

	private  HandRank getHandRank(Hand hand) {

		Card[] cards = hand.getHand();
		if (isStraightFlush(cards)) return handRank.STRAIGHTFLUSH;
		else if (isFourOfAKind(cards)) return handRank.FOUROFAKIND;
		else if (isFullHouse(cards)) return handRank.FULLHOUSE;
		else if (isFlush(cards)) return handRank.FLUSH;
		else if (isStraight(cards)) return handRank.STRAIGHT;
		else if (isThreeOfAKind(cards)) return handRank.THREEOFAKIND;
		else if (isTwoPair(cards)) return handRank.TWOPAIR;
		else if (isPair(cards)) return handRank.PAIR;
		else return handRank.HIGHCARD;
	}
	
	private int compareHighCard(Hand hand1, Hand hand2){
		Card[] hand1Cards = hand1.getHand();
		Card[] hand2Cards = hand2.getHand();
		return hand1Cards[hand1Cards.length-1].compareTo(hand2Cards[hand2Cards.length-1]);
	}
	
	private Card compareFourOfAKind(Hand tempHand){
		Card[] tempCards = tempHand.getHand();
		if (tempCards[0] == tempCards[3]) return tempCards[0];
		else if (tempCards[1] == tempCards[4]) return tempCards[1];
		return null;
	}
	
	private Card compareThreeOfAKind(Hand tempHand){
		Card[] tempCards = tempHand.getHand();
		if (tempCards[0] == tempCards[2]) return tempCards[0];
		else if (tempCards[1] == tempCards[3]) return tempCards[1];
		else if (tempCards[2] == tempCards[4]) return tempCards[2];
		return null;
	}

	private int compareRecursiveHighCard(Hand hand1, Hand hand2){
		Card[] hand1Cards = hand1.getHand();
		Card[] hand2Cards = hand2.getHand();
		int current = hand1Cards.length-1;
		int cmp = 0;
		while (current>=0){
			cmp = hand1Cards[current].compareTo(hand2Cards[current]);
			if (cmp!=0) break;
			current--;
			
		}
		return cmp;
	}
	
    private int compareTwoPair(Hand hand1, Hand hand2){
			Card[] hand1Cards = hand1.getHand();
			int card1 = hand1Cards[0].getValue();
			int card2 = hand1Cards[1].getValue();
			int card3 = hand1Cards[2].getValue();
			int card4 = hand1Cards[3].getValue();
			int card5 = hand1Cards[4].getValue();
			Card hand1Bigger = null;
			Card hand1Smaller = null;
			Card hand1Remain = null;
			if (card1 == card2 && card3 == card4 && card2!=card3 && card4 != card5){
				if (card1>card3) {
					hand1Bigger = hand1Cards[0];
					hand1Smaller = hand1Cards[2];
					hand1Remain = hand1Cards[4];
				}
				else{
					hand1Bigger = hand1Cards[2];
					hand1Smaller = hand1Cards[0];
					hand1Remain = hand1Cards[4];
				}
			}
			if (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5){
				if (card2>card4){
					hand1Bigger = hand1Cards[1];
					hand1Smaller = hand1Cards[3];
					hand1Remain = hand1Cards[0];
				}
				else {
					hand1Bigger = hand1Cards[3];
					hand1Smaller = hand1Cards[1];
					hand1Remain = hand1Cards[0];
				}
			}
			
			Card[] hand2Cards = hand1.getHand();
			int card11 = hand2Cards[0].getValue();
			int card22 = hand2Cards[1].getValue();
			int card33 = hand2Cards[2].getValue();
			int card44 = hand2Cards[3].getValue();
			int card55 = hand2Cards[4].getValue();
			Card hand2Bigger = null;
			Card hand2Smaller = null;
			Card hand2Remain = null;
			if (card11 == card22 && card33 == card44 && card22!=card33 && card44 != card55){
				if (card11>card33) {
					hand2Bigger = hand2Cards[0];
					hand2Smaller = hand2Cards[2];
					hand2Remain = hand2Cards[4];
				}
				else{
					hand2Bigger = hand2Cards[2];
					hand2Smaller = hand2Cards[0];
					hand2Remain = hand2Cards[4];
				}
			}
			if (card11 != card22 && card22 == card33 && card33 != card44 && card44 == card55){
				if (card22>card44){
					hand2Bigger = hand2Cards[1];
					hand2Smaller = hand2Cards[3];
					hand2Remain = hand2Cards[0];
				}
				else {
					hand2Bigger = hand2Cards[3];
					hand2Smaller = hand2Cards[1];
					hand2Remain = hand2Cards[0];
				}
			}
			
			if (hand1Bigger.compareTo(hand2Bigger)!=0) return hand1Bigger.compareTo(hand2Bigger);
			else if (hand1Smaller.compareTo(hand2Smaller)!=0) return hand1Smaller.compareTo(hand2Smaller);
			else return hand1Remain.compareTo(hand2Remain);
		}

		
}
