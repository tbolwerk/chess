package TPN.moves;


import TPN.board.Board;
import TPN.board.Box;

public class FENParser {

    private static int[][] fenArrayValue() {
        int[][] myArray = new int[8][8];
        int teller = 0;
        Box box;
        for (int cols = 0; cols < 8; cols++) {
            for (int rows = 0; rows < 8; rows++) {
                box = Board.getGrid().get(teller);
                teller++;

                if (box.getPiece() != null) {
                    if (box.getPiece().getIsWhite()) {
                        myArray[cols][rows] = -box.getPiece().valueOfPiece();
                    } else
                        myArray[cols][rows] = box.getPiece().valueOfPiece();
                } else {
                    myArray[cols][rows] = 0;
                }
            }
        }


        return myArray;
    }


    @SuppressWarnings("Duplicates")
    private static char[][] fenArray() {
        char[][] myArray = new char[8][8];
        int teller = 0;
        Box box;
        for (int cols = 0; cols < 8; cols++) {
            for (int rows = 0; rows < 8; rows++) {
                box = Board.getGrid().get(teller);
                teller++;

                if (box.getPiece() != null) {
                    if (!box.getPiece().getIsWhite()) {
                        myArray[cols][rows] = Character.toLowerCase(box.getPiece().getPieceName());
                    } else
                        myArray[cols][rows] = box.getPiece().getPieceName();

                } else {
                    myArray[cols][rows] = 't';
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
        System.out.println(translateFENNotation());
    }

    public static void printFENArrayValue() {

        System.out.println("board after move");

        for (int r = 0; r < fenArrayValue().length; r++) {
            for (int c = 0; c < fenArrayValue()[r].length; c++)
                System.out.print(fenArrayValue()[r][c] + " ");
            System.out.println();
        }
        System.out.println(translateFENNotation());
    }

    private static String translateFENNotation() {
        StringBuilder fenNotation = new StringBuilder();
        int check;
        char[][] myArray = fenArray();
        for (int rows = 0; rows < fenArray().length; rows++) {
            for (int cols = 0; cols < fenArray().length; cols++) {
                fenNotation.append(myArray[rows][cols]);
                if (cols % 8 == 0 && rows != 0) {
                    fenNotation.append("/");
                }
            }
        }
        return fenNotation.toString();
    }



}
