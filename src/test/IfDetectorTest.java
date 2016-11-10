import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.IfDetector;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by marting422 on 10.11.16.
 */
public class IfDetectorTest {

    @Test
    public void ifTest() {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";

        //inspect(query);
        ArrayList<CodeSmell> codeSmells = new ArrayList<CodeSmell>();
        try {
            IfDetector.detect(query, codeSmells);
        } catch (IOException ex) {
            fail();
        }
        assertEquals(2, codeSmells.size());
    }
}
