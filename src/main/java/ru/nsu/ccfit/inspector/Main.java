package ru.nsu.ccfit.inspector;

import java.io.IOException;

/**
 * Created by marting422 on 25.11.16.
 */
public class Main {
    public static void main (String[] args) {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        CodeAnalyzer codeAnalyzer = new CodeAnalyzer();
        try {
            codeAnalyzer.analyze(query);
            codeAnalyzer.printCodeSmells();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
