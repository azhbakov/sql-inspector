package ru.nsu.ccfit.inspector;

/**
 * Created by marting422 on 28.10.16.
 */
public class CodeSmell {
    int line;
    String message;

    public void print () {
        System.out.println("[CodeSmell in line " + line + "]: " + message);
    }
}
