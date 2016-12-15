package ru.nsu.ccfit.inspector.inspectors;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.checkers.Checker;
import ru.nsu.ccfit.inspector.checkers.StubIfChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 15.11.2016.
 */
public class StubInspector extends Inspector {
    private List<Checker> checkers;

    public StubInspector () {
        checkers = new ArrayList<>();
    }

    @Override
    public void initInspector (Parser parser, ParseTree tree) {
        super.initInspector(parser, tree);
        checkers.add(new StubIfChecker(parser, tree));
    }

    @Override
    public void inspect(List<CodeSmell> codeSmells) throws IllegalStateException {
        super.inspect(codeSmells);
        for (Checker checker : checkers) {
            checker.check(codeSmells);
        }
    }
}
