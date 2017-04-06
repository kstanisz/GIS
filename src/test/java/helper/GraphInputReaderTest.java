package helper;

import model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class GraphInputReaderTest {
    GraphInputReader graphInputReader;

    @Before
    public void setGraph() {
        try {
            graphInputReader = new GraphInputReader("input.csv");
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist!");
        }
    }

    @Test
    public void testReadInputGraph() {
        boolean[][] expectedAdjacencyMatrix = new boolean[][]{
                {false, false, false, true, false},
                {true, false, true, false, false},
                {false, false, false, false, true},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };

        Graph expectedGraph = Graph.createGraphFromAdjacencyMatrix(expectedAdjacencyMatrix);
        Graph resultGraph = graphInputReader.createGraph();

        Assert.assertEquals(expectedGraph, resultGraph);
    }
}
