package ru.nsu.ccfit.inspector;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.inspectors.Inspector;
import ru.nsu.ccfit.inspector.inspectors.StubInspector;

/**
 * Created by Mark on 16.11.2016.
 */
public class InspectorFactoryImpl implements InspectorFactory {
    private Parser parser;
    private ParseTree tree;

    public InspectorFactoryImpl(Parser parser, ParseTree tree) {
        this.parser = parser;
        this.tree = tree;
    }

    @Override
    public Inspector createStubInspector() {
        return new StubInspector(parser, tree);
    }
}
