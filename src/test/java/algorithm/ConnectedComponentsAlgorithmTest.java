package algorithm;

import model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectedComponentsAlgorithmTest {

    private ConnectedComponentsAlgorithm connectedComponentsAlgorithm = new ConnectedComponentsAlgorithm();
    private Graph graph;

    @Before
    public void setUpGraph() {
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, false, false, false, false},
                {false, false, true, false, true},
                {false, true, false, true, false},
                {false, false, true, false, true},
                {false, true, false, true, false}
        };
        graph = Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix);
    }

    @Test
    public void testGetGreatestConnectedComponent() {
        boolean[][] expectedAdjacencyMatrix = new boolean[][]{
                {false, true, false, true},
                {true, false, true, false},
                {false, true, false, true},
                {true, false, true, false}
        };
        int[] expectedVertexLabels = new int[]{1, 2, 3, 4};

        Graph expectedGraph = Graph.createGraphFromAdjacencyMatrix(expectedAdjacencyMatrix);
        expectedGraph.setVertexLabels(expectedVertexLabels);
        Graph resultGraph = connectedComponentsAlgorithm.getGreatestConnectedComponent(graph);

        Assert.assertEquals(expectedGraph, resultGraph);
    }
}