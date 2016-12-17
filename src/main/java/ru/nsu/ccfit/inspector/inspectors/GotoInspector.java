package ru.nsu.ccfit.inspector.inspectors;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.checkers.GotoChecker;

import java.util.List;

/**
 * Created by artem on 16.12.16.
 */
public class GotoInspector implements Inspector {

    public GotoInspector(Parser parser, ParseTree tree)
    {
        checker = new GotoChecker(parser,tree);
    }
    @Override
    public void inspect(List<CodeSmell> codeSmells) {
        checker.check(codeSmells);
    }

    private GotoChecker checker;
}
