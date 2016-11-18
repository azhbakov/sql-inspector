package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlParser;

import java.util.List;

/**
 * Created by marting422 on 10.11.16.
 */
public class StubIfChecker implements Checker {
    private Parser parser;
    private ParseTree tree;

    public StubIfChecker(Parser parser, ParseTree tree) {
        this.parser = parser;
        this.tree = tree;
    }

    @Override
    public void check(List<CodeSmell> codeSmells) {
        String xpath = "//cfl_statement";
        String treePattern = "<IF> <search_condition> <sql_clause>";

        ParseTreePattern p = parser.compileParseTreePattern(treePattern,   TsqlParser.RULE_cfl_statement);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);

        for (ParseTreeMatch match : matches) {
            //codeSmells.add(new CodeSmell(0, 0, "IF", match.getTree().getText()));
            codeSmells.add(new CodeSmell(0, 0, "IF", "Using IF is considered bad by this stub."));
            //System.out.println(match.getTree().getText());
        }
    }
}
