package ru.nsu.ccfit.inspector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by Me on 10/20/2016.
 */
public class Inspector {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello Antlr");

        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";

        inspect(query);

    }

    public static void inspect(String query) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));

        ru.nsu.ccfit.inspector.TsqlLexer lexer = new ru.nsu.ccfit.inspector.TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ru.nsu.ccfit.inspector.TsqlParser parser = new ru.nsu.ccfit.inspector.TsqlParser(tokens);

        parser.setBuildParseTree(true);
        ParseTree tree = parser.tsql_file();


        String xpath = "//insert_statement";

        for (ParseTree t : XPath.findAll(tree, xpath, parser)) {

            System.out.println(t.getText());

        }


        xpath = "//cfl_statement";
        String treePattern = "<IF> <search_condition> <sql_clause>";

        ParseTreePattern p =
                parser.compileParseTreePattern(treePattern,
                        ru.nsu.ccfit.inspector.TsqlParser.RULE_cfl_statement);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);


        for (ParseTreeMatch match : matches) {
            //System.out.println(match.getTree().getText());
        }
    }

}
