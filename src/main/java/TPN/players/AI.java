package TPN.players;

import TPN.board.Box;

/**
 * The public interface src.main.java.TPN.AI provides a single method
 * makeMove.  Classes that implement src.main.java.TPN.AI must implement this method,
 * and this allows the View class to generically refer
 * to src.main.java.TPN.AI players as of type src.main.java.TPN.AI.
 *
 * @author sid123
 */
public interface AI {
    /**
     * The makeMove(src.main.java.TPN.Board b) method takes a board as a parameter
     * and should make a move.  It should return as a String the move that
     * was made so that the JTextArea can be updated with the move.
     *
     * @param b
     * @return the move that was made as a String
     */
    public void makeMove();

    public boolean checksForCapture(Box endbox);
}