package algorithm;

import model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphAnalyserTest {

    private GraphAnalyser analyser;

    @Before
    public void setUpAnalyser(){
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false, false, true},
                {true, false, false, true, false},
                {false, false, false, true, true},
                {false, true, true, false, true},
                {true, false, true, true, false}
        };

        analyser = new GraphAnalyser(Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix));
    }

    @Test
    public void testVertexCount(){
        int expectedVertexCount = 5;
        Assert.assertEquals(expectedVertexCount, analyser.getVertexCount());
    }

    @Test
    public void testEdgeCount(){
        int expectedEdgeCount = 6;
        Assert.assertEquals(expectedEdgeCount, analyser.getEdgeCount());
    }

    @Test
    public void testVertexList(){
        int[] expectedVertexList = {0,1,2,3,4};
        Assert.assertArrayEquals(expectedVertexList, analyser.getVertexList());
    }

    @Test
    public void testDegreeDistribution(){
        int[] expectedDegreeDistribution = {2,2,2,3,3};
        Assert.assertArrayEquals(expectedDegreeDistribution, analyser.getDegreeDistribution());
    }

    @Test
    public void testShortestWay(){
        int[][] expectedShortestWay = new int[][]{
                {0,1,2,2,1},
                {1,0,2,1,2},
                {2,2,0,1,1},
                {2,1,1,0,1},
                {1,2,1,1,0}
        };
        Assert.assertArrayEquals(expectedShortestWay, analyser.getShortestWay());
    }

    @Test
    public void testAverageLength(){
        double expectedEdgeCount = 14.0/10.0;
        Assert.assertEquals(expectedEdgeCount, analyser.getAverageLength(),0.0001);
    }

}
