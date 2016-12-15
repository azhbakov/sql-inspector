package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;

import java.util.List;

/**
 * Created by Martin on 15.12.2016.
 */
public abstract class Checker {

    protected Parser parser;
    protected ParseTree tree;

    public Checker(Parser parser, ParseTree tree) {
        this.parser = parser;
        this.tree = tree;
    }
    public abstract void check(List<CodeSmell> codeSmells);
}
