package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlParser;

import java.util.List;

/**
 * Created by artem on 22.11.16.
 */
public class StubInsertChecker extends Checker {


    public StubInsertChecker(Parser parser, ParseTree tree) {
        super(parser,tree);
    }

    @Override
    public void check(List<CodeSmell> codeSmells) {
        String xpath = "//dml_clause";
        String treePattern = "<insert_statement>";

        ParseTreePattern p = parser.compileParseTreePattern(treePattern, TsqlParser.RULE_dml_clause);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);

        for (ParseTreeMatch match : matches) {
            //codeSmells.add(new CodeSmell(0, 0, "IF", match.getTree().getText()));
            codeSmells.add(new CodeSmell(0, 0, "INSERT", "Using INSERT is considered bad by this stub."));
            //System.out.println(match.getTree().getText());
        }
    }
}
