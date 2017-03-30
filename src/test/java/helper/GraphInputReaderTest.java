package helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphInputReaderTest {
    GraphInputReader graphInputReader;

    @Before
    public void setGraph() {
        graphInputReader = new GraphInputReader("input.csv");
    }

    @Test
    public void testReadInputGraph(){
        boolean[][] expectedMatrix = new boolean[][]{
                {false, false, false, true, false},
                {true, false, true, false, false},
                {false, false, false, false, true},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };

        boolean [][] resultMatrix = graphInputReader.getAdjacencyMatrix();

        Assert.assertArrayEquals(expectedMatrix, resultMatrix);
    }

}
