package TPN.moves;

import TPN.Game;
import TPN.board.Board;

public class FENOperator {
    private Board board;

    public FENOperator(Game game) {
        this.board = new Board(game);
        this.board.initGrid();
    }

    private static final char fenChars[] =
            {'K', 'P', 'Q', 'R', 'B', 'N', '-', 'n', 'b', 'r', 'q', 'p', 'k'};
    private static final String pieceArray[][] =
            {{"King", "k"}, {"Pawn", "p"}, {"Queen", "q"}, {"Rook", "r"}, {"Bischop", "b"}, {"Knight", "n"}};

    public Board fenConverter(String fen) {
        for (char c : fen.toCharArray()) {
            for (int rows = 0; rows < 8; rows++) {
                for (int cols = 0; cols < 8; cols++) {
                    if (fenChars.equals(c)) {
                        for (int i = 0; i < pieceArray.length; i++) {
                            if (fenChars.equals(pieceArray[i][c])) {
                                System.out.println(pieceArray[i][c]);
                            }
                        }

                    }
                }
            }
        }
        return null;
    }
}
