package main.java.board;

import card.GeneralCard;
import card.MinionCard;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store info about all the minions that are on the board.
 */
public class CreaturesOnBoard {

    private static Map<Integer, GeneralCard> allGeneralsOnBoard = new HashMap<>(3);

    private static Map<Integer, MinionCard> allMinionsOnBoard = new HashMap<>();

    /**
     * For getting all possible generals.
     *
     * @return Returns a HashMap of ID->General.
     */
    public static Map<Integer, GeneralCard> getAllGeneralsOnBoard() {
        return allGeneralsOnBoard;
    }

    /**
     * For getting all possible minions.
     *
     * @return Returns a HashMap of ID->Minion.
     */
    public static Map<Integer, MinionCard> getAllMinionsOnBoard() {
        return allMinionsOnBoard;
    }

    public void setAllGeneralsOnBoard(GeneralCard whiteGeneral, GeneralCard blackGeneral) {
        allGeneralsOnBoard.put(1, whiteGeneral);
        allGeneralsOnBoard.put(2, blackGeneral);
    }

}
