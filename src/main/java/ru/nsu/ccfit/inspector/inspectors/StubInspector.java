package ru.nsu.ccfit.inspector.inspectors;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.checkers.Checker;
import ru.nsu.ccfit.inspector.checkers.StubIfChecker;
import ru.nsu.ccfit.inspector.checkers.StubInsertChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 15.11.2016.
 */
public class StubInspector implements Inspector {
    private List<Checker> checkers;

    public StubInspector(Parser parser, ParseTree tree) {
        checkers = new ArrayList<>();
        checkers.add(new StubIfChecker(parser, tree));
        checkers.add(new StubInsertChecker(parser,tree));
    }

    @Override
    public void inspect(List<CodeSmell> codeSmells) {
        for (Checker checker : checkers) {
            checker.check(codeSmells);
        }
    }
}
