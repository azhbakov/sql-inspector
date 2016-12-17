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
public class StubInsertChecker implements Checker {
    private Parser parser;
    private ParseTree tree;

    public StubInsertChecker(Parser parser, ParseTree tree) {
        this.parser = parser;
        this.tree = tree;
    }

    @Override
    public void check(List<CodeSmell> codeSmells) {
        String xpath = "//insert_statement";
        String treePattern = "insert_statement" +
                ": with_expression ?" +
                " INSERT (TOP '(' expression ')' PERCENT?)?" +
                " INTO? (ddl_object | rowset_function_limited)" +
                " insert_with_table_hints?" +
                " ('(' column_name_list ')')?" +
                "output_clause?" +
                "insert_statement_value" +
                " for_clause? option_clause? ';'?";

        ParseTreePattern p = parser.compileParseTreePattern(treePattern, TsqlParser.RULE_insert_statement);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);

        for (ParseTreeMatch match : matches) {
            //codeSmells.add(new CodeSmell(0, 0, "IF", match.getTree().getText()));
            codeSmells.add(new CodeSmell(0, 0, "INSERT", "Using INSERT is considered bad by this stub."));
            //System.out.println(match.getTree().getText());
        }
    }
}
