package helper;

import model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphOperationsHelperTest {

    private GraphOperationsHelper graphOperationsHelper = new GraphOperationsHelper();
    private Graph graph;

    @Before
    public void setUpGraph() {
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false, false, false},
                {true, false, true, false, true},
                {false, true, false, true, false},
                {false, false, true, false, true},
                {false, true, false, true, false}
        };
        graph = Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix);
    }

    @Test
    public void testCreateSubgraph() {
        int[] vertexLabels = new int[]{3, 4, 2, 0};
        boolean[][] expectedAdjacencyMatrix = new boolean[][]{
                {false, true, true, false},
                {true, false, false, false},
                {true, false, false, false},
                {false, false, false, false}
        };

        Graph expectedSubgraph = Graph.createGraphFromAdjacencyMatrix(expectedAdjacencyMatrix);
        expectedSubgraph.setVertexLabels(vertexLabels);

        Graph resultSubgraph = graphOperationsHelper.createSubgraph(graph,
                IntStream.of(vertexLabels).boxed().collect(Collectors.toList()));

        Assert.assertEquals(expectedSubgraph, resultSubgraph);
    }
}