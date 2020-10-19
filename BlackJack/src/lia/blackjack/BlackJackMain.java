package lia.blackjack;

import java.util.Scanner;

public class BlackJackMain {
	static String name = ""; 
	static String experience = "";
	static double playerBalance = 1000.0;
	static boolean stopRound = true; 
	
	public static Scanner input = new Scanner(System.in);

	



	public static void main(String[] args) {
		
		 askName();

		 
		 Deck deckInPlay = new Deck();
		 deckInPlay.createDeck();
		 deckInPlay.shuffle();
		 
		 Deck playersDeck = new Deck();
		 Deck dealersDeck = new Deck();
		 		 
		 //System.out.println(deckInPlay);
		 
		 while(playerBalance > 0) {
			 System.out.println("Current balance: $" + playerBalance + "\n How much do you want to bet this round? (If you want to stop now and keep your money enter: 0000)");
			 double playerBet = input.nextDouble();
			 if(playerBet > playerBalance) {
				 System.out.println("DEALER: Hey dummy! You can't bet more money than you have! Don't waist my time!");
				 break;
			 }
			 if (playerBet == 0000) {
				 System.out.println("DEALER: Yeah, that's right, walk away you wuss!");
				 System.out.println("You leave with a balance of: $" + playerBalance);
				 break;
			 }
			 
			 
			 //Player gets two starter cards
			 playersDeck.draw(deckInPlay);
			 playersDeck.draw(deckInPlay);
			 
			 //Dealer gets two starter cards

			 dealersDeck.draw(deckInPlay);
			 dealersDeck.draw(deckInPlay);
			 
			 while(true) {
				 //Tell them their cards
				 System.out.println("Your hand: ");
				 System.out.println(playersDeck.toString());
				 
				 //Show dealer card
				 System.out.println("DEALER HAND: " + dealersDeck.getCard(0).toString() + "(Other Card is upside down)");
				 
				 //Player Action
				 
				 System.out.println("Do you want to Hit (1) or Stand (2) ?");
				 int hOs = input.nextInt();
				 
				 if(hOs == 1) {
					 playersDeck.draw(deckInPlay);
					 System.out.println("You draw a: " + playersDeck.getCard(playersDeck.deckSize()-1).toString());
				 //Done if over 21
					 if(playersDeck.cardsValue() > 21) {
						 System.out.println("DEALER: Haha! You busted at the value of: " + playersDeck.cardsValue());
						 playerBalance -= playerBet;
						 stopRound = false;
						 break;
					 }
				 }
				 
				 if(hOs == 2) {
					 break;
				 }
				 
			 }
			 
			 //show dealers hand
			 System.out.println("The dealer's cards are: " + dealersDeck.toString());
			 //See if dealer has more points than player
			 if((dealersDeck.cardsValue()> playersDeck.cardsValue()) && stopRound == true) {
				 System.out.println("DEALER: I won, you lose!!");
				 
				 playerBalance -= playerBet; 
				 stopRound = false;
			 }
			 
			 //Dealer can take cards unless he's at 17 or above
			 
			 while((dealersDeck.cardsValue() < 17) && stopRound == true) {
				 dealersDeck.draw(deckInPlay);
				 System.out.println("Dealer Draws: " + dealersDeck.getCard(dealersDeck.deckSize()-1).toString());
			 }
			 //Show dealers total
			 System.out.println("Dealer's hand is valued at: " + dealersDeck.cardsValue());
			 //Dealer bust checker
			 if((dealersDeck.cardsValue() > 21) && stopRound == true) {
				 System.out.println("DEALER: Screw you! I bet this is the first time you've ever won at anything!");
				 playerBalance += playerBet;
				 stopRound = false;
			 }
			 
			 //Determine if tie
			 if((playersDeck.cardsValue() == dealersDeck.cardsValue()) && stopRound == true) {
				 System.out.println("It's a tie!");
				 stopRound = false;
			 }
			 
			 if((playersDeck.cardsValue() > dealersDeck.cardsValue()) && stopRound == true) {
				 System.out.println("DEALER: So you won one round! Big deal...");
				 playerBalance+= playerBet;
				 stopRound = false;
			 }
			 
			 else if (stopRound == true) {
				 System.out.println("DEALER: Ha! I win!");
				 playerBalance -= playerBet;
				 stopRound = false;
			 }
			 
			 //move all player and dealer cards back into the deck
			 playersDeck.moveToDeck(deckInPlay);
			 dealersDeck.moveToDeck(deckInPlay);
			 System.out.println("HAND OVER");


			 
		 }
		 
		 System.out.println("Game over!");
		 
		 
		 


	}
	public static void askName() {

		
		System.out.println("DEALER: So you think you can beat me at Blackjack? Ha! That's hilarious. What's your name?");
		
		 name = input.nextLine();
		 
		 System.out.println("DEALER: So, " + name + ", you ever played Blackjack before? (Type 'Y' if you have or 'N' if you haven't)");
			
		 experience = input.nextLine();
		 
		 if(experience.equals("Y") || experience.equals("y")){
			 System.out.println("DEALER: Well then what are we waitin' for, " + name + ", let's get this over with!");
		 }
		 else {
			 System.out.println("DEALER: Of course you haven't! What a joke... Here's how were gonna play: ");
		 }

	}


}
