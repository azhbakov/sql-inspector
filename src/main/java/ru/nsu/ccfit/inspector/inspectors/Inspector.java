package ru.nsu.ccfit.inspector.inspectors;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;

import java.util.List;

/**
 * Created by Mark on 15.11.2016.
 */
public abstract class Inspector {
    Parser parser;
    ParseTree tree;

    public void initInspector (Parser parser, ParseTree tree) {
        this.parser = parser;
        this.tree = tree;
    }

    public void inspect(List<CodeSmell> codeSmells) throws IllegalStateException {
        if (parser == null || tree == null) {
            throw new IllegalStateException ("Inspector was not initialized!");
        }
    }
}
