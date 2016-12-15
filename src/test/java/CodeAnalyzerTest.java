import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeAnalyzer;
import ru.nsu.ccfit.inspector.CodeSmell;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 15.12.2016.
 */
public class CodeAnalyzerTest {

    @Test
    public void check () throws Exception {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        CodeAnalyzer codeAnalyzer = new CodeAnalyzer();
        codeAnalyzer.analyze(query);

        List<CodeSmell> codeSmells = codeAnalyzer.GetCodeSmells();
        assertEquals(2, codeSmells.size());
    }
}
