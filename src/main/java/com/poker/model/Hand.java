package com.poker.model;

import java.util.Arrays;

import com.poker.enums.HandRank;

public class Hand {

	private Card[] hand;
	private String name;
	private HandRank handRankEnum;

	public HandRank getHandRankEnum() {
		return handRankEnum;
	}

	public void setHandRankEnum(HandRank handRankEnum) {
		this.handRankEnum = handRankEnum;
	}

	public Hand() {
		hand = null;
		name = "";
	}

	public Hand(String handString, String name) {
		String[] cards = handString.split(" ");
		hand = new Card[cards.length];
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new Card(cards[i]);
		}
		Arrays.sort(hand);
		this.name = name;
	}

	public Card[] getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}

}
