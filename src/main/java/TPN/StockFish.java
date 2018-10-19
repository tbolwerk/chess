package TPN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StockFish {

    private static final String PATH = "C:/Users/twanb/Desktop/chess/src/main/resources/engine/stockfish.exe";
    private Process engineProcess;
    private BufferedReader processReader;
    private OutputStreamWriter processWriter;
    private final int EXTRAWAITTIME = 100;
    public StockFish() {
    }

    public boolean startEngine() {
        try {
            this.engineProcess = Runtime.getRuntime().exec(PATH);
            this.processReader = new BufferedReader(new InputStreamReader(this.engineProcess.getInputStream()));
            this.processWriter = new OutputStreamWriter(this.engineProcess.getOutputStream());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public void startNewGame() {
        this.sendCommand("ucinewgame");

    }
    public void sendCommand(String command) {
        try {
            this.processWriter.write(command + "\n");
            this.processWriter.flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public String getOutput(int waitTime) {
        StringBuffer buffer = new StringBuffer();

        try {
            Thread.sleep((long) waitTime);
            this.sendCommand("isready");

            while (true) {
                String text = this.processReader.readLine();
                if (text.equals("readyok")) {
                    break;
                }

                buffer.append(text + "\n");
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return buffer.toString();
    }


    public String getBestMove(String fen, int waitTime) {
        this.sendCommand("position fen " + fen);
        this.sendCommand("go movetime " + waitTime);
        String output = this.getOutput(waitTime + EXTRAWAITTIME);
        System.out.println(output.split("bestmove ")[1].split(" ")[0]);
//        System.out.println(this.getOutput(waitTime));
        return output.split("bestmove ")[1].split(" ")[0];
    }

    public void stopEngine() {
        try {
            this.sendCommand("quit");
            this.processReader.close();
            this.processWriter.close();
        } catch (IOException var2) {

        }

    }

    public String getLegalMoves(String fen) {
        this.sendCommand("position fen " + fen);
        this.sendCommand("d");
        return this.getOutput(0).split("Legal moves: ")[1];
    }

    public void drawBoard(String fen) {
        this.sendCommand("position fen " + fen);
        this.sendCommand("d");
        String[] rows = this.getOutput(0).split("\n");

        for (int i = 1; i < 18; ++i) {
            System.out.println(rows[i]);
        }

    }

    public float getEvalScore(String fen, int waitTime) {
        this.sendCommand("position fen " + fen);
        this.sendCommand("go movetime " + waitTime);
        float evalScore = 0.0F;
        String[] dump = this.getOutput(waitTime + 20).split("\n");

        for (int i = dump.length - 1; i >= 0; --i) {
            if (dump[i].startsWith("info depth ")) {
                evalScore = Float.parseFloat(dump[i].split("score cp ")[1].split(" nodes")[0]);
            }
        }

        return evalScore / 100.0F;
    }
}


