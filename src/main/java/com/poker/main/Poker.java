package com.poker.main;

import java.io.IOException;

import com.poker.model.Hand;
import com.poker.serviceImpl.HandServiceImpl;

public class Poker {

	public static void main(String[] args) throws IOException{
		   HandServiceImpl handServiceImpl= new HandServiceImpl();
			String blackHandString = "2H 3D 5S 9C KD";
			String whiteHandString = "2C 3H 4S 8C AH";
			Hand blackHand = new Hand(blackHandString,"Black");
			Hand whiteHand = new Hand(whiteHandString,"White");
			String result=handServiceImpl.getwinner(blackHand, whiteHand);
			if (result.equals("Tie.")) System.out.println(result);
			else System.out.println(result+" wins.");
			
				
	}
}



