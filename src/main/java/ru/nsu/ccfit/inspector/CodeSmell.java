package ru.nsu.ccfit.inspector;

import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * Created by marting422 on 28.10.16.
 */
public class CodeSmell {
    int line;
    String type;
    String message;

    public CodeSmell (int line, String type, String message) {
        this.line = line;
        this.type = type;
        this.message = message;
    }

    public void print () {
        System.out.println("[CodeSmell " + type +  "  in line " + line + "]: " + message);
    }
}
