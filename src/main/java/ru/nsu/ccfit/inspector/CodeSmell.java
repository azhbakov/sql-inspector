package ru.nsu.ccfit.inspector;

/**
 * Created by marting422 on 28.10.16.
 */
public class CodeSmell {

    public CodeSmell(int line, int symbol, String type, String message) {
        this.line = line;
        this.symbol = symbol;
        this.type = type;
        this.message = message;
    }

    public void print() {
        System.out.println("(" + line + "; " + symbol + ") " + type + ": " + message);
        //System.out.println("[CodeSmell " + type +  "  in line " + line + "]: " + message);
    }

    private int line;
    private int symbol;
    private String type;
    private String message;

}
