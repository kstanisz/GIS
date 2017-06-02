package model;

import java.util.Arrays;

public class Graph {

    private boolean[][] adjacencyMatrix;
    private int[] vertexLabels;
    private int vertexCount;

    private Graph(boolean[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertexCount = adjacencyMatrix[0].length;
        // Default vertex labels are from 0 to n-1
        this.vertexLabels = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            vertexLabels[i] = i;
        }
    }

    public static Graph createGraphFromAdjacencyMatrix(boolean[][] adjacencyMatrix) {
        return new Graph(adjacencyMatrix);
    }

    public static Graph generateRandomGraph(int vertices, double edgeProbability) {

        boolean[][] adjacencyMatrix = new boolean[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = i; j < vertices; j++) {
                double random = Math.random();
                if(j == i){
                    adjacencyMatrix[i][j] = false;
                }else{
                    adjacencyMatrix[i][j] = random < edgeProbability;
                }
            }
        }

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < i; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[j][i];
            }
        }

        return new Graph(adjacencyMatrix);
    }

    public boolean isEdge(int firstVertex, int secondVertex) {
        return adjacencyMatrix[firstVertex][secondVertex];
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexLabels(int[] vertexLabels) {
        this.vertexLabels = vertexLabels;
    }

    public int[] getVertexLabels() {
        return vertexLabels;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;

        return (other.getVertexCount() == this.getVertexCount()
                && Arrays.equals(other.getVertexLabels(), this.getVertexLabels())
                && Arrays.deepEquals(other.getAdjacencyMatrix(), this.getAdjacencyMatrix()));
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(adjacencyMatrix);
        result = 31 * result + Arrays.hashCode(vertexLabels);
        result = 31 * result + vertexCount;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nVertex count: ");
        builder.append(vertexCount);
        builder.append("\nVertex labels: \n");
        for (int label : vertexLabels) {
            builder.append(label);
            builder.append(" ");
        }
        builder.append("\nAdjacency matrix: \n");
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                builder.append(adjacencyMatrix[i][j]);
                builder.append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}