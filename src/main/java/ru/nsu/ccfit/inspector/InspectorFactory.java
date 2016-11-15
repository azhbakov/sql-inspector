package ru.nsu.ccfit.inspector;

import ru.nsu.ccfit.inspector.inspectors.Inspector;

/**
 * Created by Mark on 16.11.2016.
 */
public interface InspectorFactory {
    Inspector createStubInspector();
    //TODO: implement more inspectors
}
