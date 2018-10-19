package TPN.moves;


import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.states.GameState;

public class FENParser {
    private static int turncounter = 0;
    private static int halfmovecounter = 0;
    private static boolean enPassantEnabled = false;
    private static boolean castlingEnabled = false;
    private static String EnPassantFENPosistion = " -";
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

    public static void setCastlingEnabled(boolean castlingEnabled) {
        FENParser.castlingEnabled = castlingEnabled;
    }

    public static boolean isEnPassantEnabled() {
        return enPassantEnabled;
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
                    myArray[cols][rows] = '1';
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
        System.out.println(extractor());
    }

    public static void printFENArrayValue() {

        System.out.println("board after move");

        for (int r = 0; r < fenArrayValue().length; r++) {
            for (int c = 0; c < fenArrayValue()[r].length; c++)
                System.out.print(fenArrayValue()[r][c] + " ");
            System.out.println();
        }
        System.out.println(MoveParser.getLastMove());
    }

    private static String translateFENNotation() {
        StringBuilder fenNotation = new StringBuilder();
        int emptyRowNumberNotation = 0;
        char[][] myArray = fenArray();
        for (int rows = 0; rows < fenArray().length; rows++) {
            for (int cols = 0; cols < fenArray().length; cols++) {
                if (cols % 8 == 0 && rows != 0) {
                    fenNotation.append("/");
                }
                fenNotation.append(myArray[rows][cols]);



            }
        }
        return fenNotation.toString();
    }

    private static String extractor() {
        String myString = translateFENNotation();
        String split[] = myString.split("/");
        StringBuilder sb = new StringBuilder();
        int count;
        for (int i = 0; i < split.length; i++) {
            count = 0;
            for (char c : split[i].toCharArray()) {
                if (Character.isDigit(c)) {
                    count++;

                }
                if (!Character.isDigit(c) && count == 0) {
                    sb.append(c);
                } else if (!Character.isDigit(c) && count != 0) {
                    sb.append(count);
                    sb.append(c);
                    count = 0;
                }
            }
            if (count != 0) {
                sb.append(count);
            }
            if (i != 7) {
                sb.append("/");
            }
        }
        return sb.toString();
    }

    private static String turnIndicatorFENNotation() {
        StringBuilder myString = new StringBuilder();
        myString.append(extractor());
        if (Game.getPlayers().size() > 0) {
            if (Game.getPlayers().get(0).getIsTurn() && Game.getPlayers().get(0).getIsWhite()) {
                myString.append(" w");
            } else {
                myString.append(" b");
            }
        }
        return myString.toString();
    }

    private static String CastlingAvailabilityFENNotation() {
        StringBuilder myString = new StringBuilder();
        myString.append(turnIndicatorFENNotation());
        if (castlingEnabled) {
            myString.append(" KQkq");
        } else
            myString.append(" -");
        return myString.toString();
    }

    public static void setEnPassantFENPosistion(String enPassantFENPosistion) {
        EnPassantFENPosistion = enPassantFENPosistion;
    }

    private static String EnpassantFENNotation() {
        StringBuilder myString = new StringBuilder();
        myString.append(CastlingAvailabilityFENNotation());
        if (enPassantEnabled) {
            myString.append(EnPassantFENPosistion);
        } else
            myString.append(" -");

        return myString.toString();
    }

    public static String MoveClockFENNotation() {
        StringBuilder myString = new StringBuilder();
        if (GameState.getCountHalfMoves() % 2 == 0) {
            halfmovecounter = GameState.getCountHalfMoves() / 2;
        }

        if (GameState.getCountMoves() % 2 == 0) {
            turncounter = GameState.getCountMoves() / 2;

        }

        myString.append(EnpassantFENNotation());
        myString.append(" " + halfmovecounter + " " + turncounter);
        return myString.toString();
    }

}




