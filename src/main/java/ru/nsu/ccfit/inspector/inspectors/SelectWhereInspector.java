package ru.nsu.ccfit.inspector.inspectors;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.checkers.Checker;
import ru.nsu.ccfit.inspector.checkers.SelectWhereChecker;
import ru.nsu.ccfit.inspector.checkers.StubIfChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marting422 on 26.11.16.
 */
public class SelectWhereInspector implements Inspector {
    private List<Checker> checkers;

    public SelectWhereInspector(Parser parser, ParseTree tree) {
        checkers = new ArrayList<Checker>();
        checkers.add(new SelectWhereChecker(parser, tree));
    }

    @Override
    public void inspect(List<CodeSmell> codeSmells) {
        for (Checker checker : checkers) {
            checker.check(codeSmells);
        }
    }
}
