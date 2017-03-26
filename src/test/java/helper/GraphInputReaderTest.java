package helper;

import model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
