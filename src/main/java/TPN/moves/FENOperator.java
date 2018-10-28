package TPN.moves;

import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.pieces.*;

import java.util.ArrayList;

public class FENOperator {
    private Board board;
    private Game game;

    public FENOperator(Game game) {
        this.game=game;
        this.board = new Board(game);
        this.board.initGrid();
    }

    private static final char fenChars[] =
            {'K', 'P', 'Q', 'R', 'B', 'N', '-', 'n', 'b', 'r', 'q', 'p', 'k'};


    private static final String pieceArray[][] =
            {{"King", "k"}, {"Pawn", "p"}, {"Queen", "q"}, {"Rook", "r"}, {"Bischop", "b"}, {"Knight", "n"}};







}
