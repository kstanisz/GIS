package helper;

import algorithm.ShortestPathAlgorithm;
import model.Graph;
import model.GraphDetails;

public class GraphDetailsAnalyzer {

    private Graph analyzedGraph;

    public GraphDetailsAnalyzer(Graph inputGraph) {
        this.analyzedGraph = inputGraph;
    }

    public GraphDetails analyze() {
        GraphDetails graphDetails = new GraphDetails();

        graphDetails.setAdjacencyMatrix(analyzedGraph.getAdjacencyMatrix());
        graphDetails.setVertexCount(analyzedGraph.getVertexCount());
        graphDetails.setVertexLabels(analyzedGraph.getVertexLabels());
        graphDetails.setEdgeCount(calculateEdgeCount());
        graphDetails.setDegreeDistribution(calculateDegreeDistribution());
        int[][] shortestPaths = calculateShortestPaths();
        graphDetails.setShortestPaths(shortestPaths);
        graphDetails.setPathAverageLength(calculatePathsAverageLength(shortestPaths));

        return graphDetails;
    }

    private int calculateEdgeCount() {
        int vertexCount = analyzedGraph.getVertexCount();
        int edgeCount = 0;

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (analyzedGraph.getAdjacencyMatrix()[i][j])
                    edgeCount++;
            }
        }

        return edgeCount / 2;
    }

    private int[] calculateDegreeDistribution() {
        int vertexCount = analyzedGraph.getVertexCount();
        int[] degreeDistribution = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++){
            degreeDistribution[i] = calculateVertexDegree(i, vertexCount);
        }

        return degreeDistribution;
    }

    private int calculateVertexDegree(int index, int vertexCount) {
        int vertexDegree = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (analyzedGraph.getAdjacencyMatrix()[index][i])
                vertexDegree++;
        }
        return vertexDegree;
    }

    private int[][] calculateShortestPaths() {
        int vertexCount = analyzedGraph.getVertexCount();

        int[][] shortestPaths = new int[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (j > i)
                    shortestPaths[i][j] = findShortestPath(i, j);
                else
                    shortestPaths[i][j] = 0;
            }
        }

        shortestPaths = mirrorMatrix(shortestPaths);
        return shortestPaths;
    }

    private int findShortestPath(int startVertex, int finishVertex) {
        ShortestPathAlgorithm shortestPathAlgorithm =
                new ShortestPathAlgorithm(this.analyzedGraph, startVertex, finishVertex);

        return shortestPathAlgorithm.findShortestPath();
    }

    private int[][] mirrorMatrix(int[][] matrixToMirror) {
        int vertexCount = analyzedGraph.getVertexCount();

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (j < i)
                    matrixToMirror[i][j] = matrixToMirror[j][i];
            }
        }

        return matrixToMirror;
    }

    private double calculatePathsAverageLength(int[][] shortestPaths) {
        int vertexCount = analyzedGraph.getVertexCount();

        double sum = sumShortestPaths(shortestPaths);
        double factor = vertexCount * (vertexCount - 1);

        return sum / factor;
    }

    private double sumShortestPaths(int[][] shortestPaths) {
        int vertexCount = analyzedGraph.getVertexCount();
        double sum = 0;

        for (int i = 0; i < vertexCount; i++){
            for (int j = 0; j < vertexCount; j++){
                sum += shortestPaths[i][j];
            }
        }

        return sum;
    }
}
