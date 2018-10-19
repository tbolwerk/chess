package TPN.players;

import TPN.Game;
import TPN.StockFish;
import TPN.board.Board;
import TPN.board.Box;
import TPN.states.GameState;

import java.util.ArrayList;

public class StockFishAI extends Player implements AI {
    StockFish stockFish;

    public StockFishAI(Game game, int color, boolean isWhite) {
        super(game, color, isWhite);
        this.stockFish = new StockFish();
        initStockFish();
        this.stockFish.startNewGame();
    }

    private void initStockFish() {
        this.stockFish.startEngine();
        this.stockFish.sendCommand("uci");
    }

    private ArrayList<Box> FenConverter(String FENPosition) {
        StringBuilder startpos = new StringBuilder();
        StringBuilder endpos = new StringBuilder();
        ArrayList<Box> fenboxes = new ArrayList<>();
        if (FENPosition.toCharArray().length == 0) {
            return null;
        }
        for (int i = 0; i < FENPosition.toCharArray().length; i++) {
            if (i < 2) {
                startpos.append(FENPosition.toCharArray()[i]);
            } else if (i < 4) {
                endpos.append(FENPosition.toCharArray()[i]);
            }
        }
        for (Box box : Board.getGrid()) {
            if (box.toString().contains(startpos.toString().toUpperCase())) {
                fenboxes.add(0, box);
            }

        }
        if (fenboxes.size() == 1) {
            for (Box box : Board.getGrid()) {
                if (box.toString().contains(endpos.toString().toUpperCase())) {
                    fenboxes.add(1, box);
                }

            }
        }
        return fenboxes;
    }

    @Override
    public void makeMove() {


        ArrayList<Box> bestmoveSTOCKFISH = new ArrayList<>();


        if (GameState.getCountMoves() == 0 && getIsWhite()) {
//makes initial move if white
            bestmoveSTOCKFISH.add(0, Board.getGrid().get(46));
            bestmoveSTOCKFISH.add(0, Board.getGrid().get(54));
//            System.out.println(bestmoveSTOCKFISH.get(0).toString());
//            System.out.println(bestmoveSTOCKFISH.get(1).toString());
        } else {
            String fenpos = stockFish.getBestMove(game.getOpponent(this).getLastMoveInFenNotation(), 100);
            if (fenpos == null){
                setHasLost(true);
            return;
        }
            bestmoveSTOCKFISH = FenConverter(fenpos);


        }

        if (bestmoveSTOCKFISH != null && bestmoveSTOCKFISH.size() > 1) {

            Box startbox = bestmoveSTOCKFISH.get(0);
            Box endBox = bestmoveSTOCKFISH.get(1);
            bestmoveSTOCKFISH.clear();
            if (startbox.getPiece() != null) {
                System.out.println(stockFish.getBestMove(game.getOpponent(this).getLastMoveInFenNotation(), 100));
                stockFish.drawBoard(game.getOpponent(this).getLastMoveInFenNotation());
            }
            game.getClickedBoxes().add(0, startbox);
            game.getClickedBoxes().add(1, endBox);
            game.mousePressed();


        }

    }

    @Override
    public boolean checksForCapture(Box endbox) {
        return false;
    }

    @Override
    public String toString() {
        return "StockFish";
    }
}
