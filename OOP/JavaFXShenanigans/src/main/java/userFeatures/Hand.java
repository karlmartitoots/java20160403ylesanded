package main.java.userFeatures;

import card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * For holding all cards in hand and operating with them through gamelogic.
 */
public class Hand {

    private List<Card> hand = new ArrayList<>();

    /**
     * Adds a specific card to the players hand
     *
     * @param card Card to add to hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Discards all the cards in the players hand.
     */
    public void discardHand() {
        hand.clear();
    }

    /**
     * Discards a random card from the hand of the player
     */
    public void discardRandomCard() {
        hand.remove((int) (Math.random() * hand.size()));
    }

    /**
     * Discards a specific card from the players hand
     *
     * @param i index of the card
     */
    public void discardSpecificCard(int i) {
        hand.remove(i);
    }

    /**
     * Get cards in ur hand as list
     *
     * @return Cards in hand
     */
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Hand{" +
                ", \nhand=" + hand +
                '}';
    }
}
