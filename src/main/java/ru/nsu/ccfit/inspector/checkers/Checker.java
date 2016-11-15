package ru.nsu.ccfit.inspector.checkers;

import ru.nsu.ccfit.inspector.CodeSmell;

import java.util.List;

/**
 * Created by Mark on 15.11.2016.
 */
public interface Checker {
    void check(List<CodeSmell> codeSmells);
}
