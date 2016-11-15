package ru.nsu.ccfit.inspector.inspectors;

import ru.nsu.ccfit.inspector.CodeSmell;

import java.util.List;

/**
 * Created by Mark on 15.11.2016.
 */
public interface Inspector {
    void inspect(List<CodeSmell> codeSmells);
}
