package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import ru.nsu.ccfit.inspector.CodeSmell;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.TsqlParser;

import java.util.List;

/**
 * Created by artem on 16.12.16.
 */
public class GotoChecker extends Checker {

    public GotoChecker(Parser parser, ParseTree parserTree)
    {
        super(parser,parserTree);
    }

    @Override
    public void check(List<CodeSmell> codeSmells) {
        String xpath = "//cfl_statement";
        String treePattern = "<GOTO> <id>";

        ParseTreePattern p = parser.compileParseTreePattern(treePattern,   TsqlParser.RULE_cfl_statement);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);

        for(ParseTreeMatch match : matches)
        {
            codeSmells.add(new CodeSmell(0, 0, "GOTO", "Using GOTO is bad practice for all computer languages"));
        }
    }

}
