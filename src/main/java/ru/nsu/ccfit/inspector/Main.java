package ru.nsu.ccfit.inspector;

import ru.nsu.ccfit.inspector.inspectors.Inspector;
import ru.nsu.ccfit.inspector.inspectors.StubInspector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marting422 on 25.11.16.
 */
public class Main {
    public static void main (String[] args) {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        if (args.length > 0)
        {
            try
            {
                String name = args[0];
                BufferedReader F = new BufferedReader(new FileReader(name));
                String str;
                StringBuilder builder = new StringBuilder();
                while((str = F.readLine()) != null)
                {
                    builder.append(str);
                }
                query = builder.toString();
            }
            catch(IOException er)
            {
                System.err.println("Something goes wrong...");
            }
        }


        ArrayList<Inspector> inspectors = new ArrayList<>();
        inspectors.add(new StubInspector());

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(inspectors);
        try {
            codeAnalyzer.analyze(query);
            codeAnalyzer.printCodeSmells();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}