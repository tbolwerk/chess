package TPN.moves;

public class MoveParser {
    private static String startpos;
    private static String endpos;


    public static void setEndpos(String endposition) {
        endpos = endposition;
    }

    public static void setStartpos(String startposition) {
        startpos = startposition;
    }

    public String getEndpos() {
        return endpos;
    }

    public String getStartpos() {
        return startpos;
    }

    @Override
    public String toString() {
        return " moves" + startpos + endpos;
    }

    public static String getLastMove() {
        if (startpos == null && endpos == null) {
            return FENParser.MoveClockFENNotation();
        }
        return FENParser.MoveClockFENNotation() + " moves " + startpos + endpos;
    }


}
