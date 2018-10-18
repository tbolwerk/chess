package TPN.moves;


import TPN.board.Board;
import TPN.board.Box;

public class FENParser {

    private static int[][] fenArray() {
        int[][] myArray = new int[8][8];
        int teller = 0;
        Box box;
        for (int cols = 0; cols < 8; cols++) {
            for (int rows = 0; rows < 8; rows++) {
                box = Board.getGrid().get(teller);
                teller++;

                if (box.getPiece() != null) {
                    myArray[cols][rows] = box.getPiece().valueOfPiece();
                } else {
                    myArray[cols][rows] = 0;
                }
            }
        }


        return myArray;
    }

    public static void printFENArray() {

        System.out.println("board after move");

        for (int r = 0; r < fenArray().length; r++) {
            for (int c = 0; c < fenArray()[r].length; c++)
                System.out.print(fenArray()[r][c] + " ");
            System.out.println();
        }
    }




}
