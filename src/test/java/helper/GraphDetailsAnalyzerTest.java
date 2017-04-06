package helper;

import model.Graph;
import model.GraphDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphDetailsAnalyzerTest {

    private GraphDetailsAnalyzer graphDetailsAnalyzer;
    private GraphDetails expectedGraphDetails;

    @Before
    public void setUpAnalyser() {
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false, false, true},
                {true, false, false, true, false},
                {false, false, false, true, true},
                {false, true, true, false, true},
                {true, false, true, true, false}
        };

        graphDetailsAnalyzer = new GraphDetailsAnalyzer(Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix));
    }

    @Before
    public void setUpExpectedGraphDetails() {
        expectedGraphDetails = new GraphDetails();

        expectedGraphDetails.setVertexCount(5);
        expectedGraphDetails.setEdgeCount(6);
        int[] expectedVertexLabels = {0, 1, 2, 3, 4};
        expectedGraphDetails.setVertexLabels(expectedVertexLabels);
        int[] expectedDegreeDistribution = {2, 2, 2, 3, 3};
        expectedGraphDetails.setDegreeDistribution(expectedDegreeDistribution);
        int[][] expectedShortestPaths = new int[][]{
                {0, 1, 2, 2, 1},
                {1, 0, 2, 1, 2},
                {2, 2, 0, 1, 1},
                {2, 1, 1, 0, 1},
                {1, 2, 1, 1, 0}
        };
        expectedGraphDetails.setShortestPathArray(expectedShortestPaths);
        double expectedPathAverageLength = 14.0 / 10.0;
        expectedGraphDetails.setPathAverageLength(expectedPathAverageLength);

    }

    @Test
    public void testGraphDetailsAnalyzer() {
        GraphDetails resultGraphDetails = graphDetailsAnalyzer.analyze();

        Assert.assertEquals(expectedGraphDetails, resultGraphDetails);
    }
}
