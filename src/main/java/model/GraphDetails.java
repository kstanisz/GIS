package model;

import java.util.Arrays;

public class GraphDetails {

    private boolean[][] adjacencyMatrix;
    private int vertexCount;
    private int[] vertexLabels;
    private int edgeCount;
    private int[] degreeDistribution;
    private int[][] shortestPaths;
    private double pathAverageLength;

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(boolean[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public int[] getVertexLabels() {
        return vertexLabels;
    }

    public void setVertexLabels(int[] vertexLabels) {
        this.vertexLabels = vertexLabels;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int[] getDegreeDistribution() {
        return degreeDistribution;
    }

    public void setDegreeDistribution(int[] degreeDistribution) {
        this.degreeDistribution = degreeDistribution;
    }

    public int[][] getShortestPaths() {
        return shortestPaths;
    }

    public void setShortestPaths(int[][] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }

    public double getPathAverageLength() {
        return pathAverageLength;
    }

    public void setPathAverageLength(double pathAverageLength) {
        this.pathAverageLength = pathAverageLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphDetails that = (GraphDetails) o;

        if (vertexCount != that.vertexCount) return false;
        if (edgeCount != that.edgeCount) return false;
        if (Double.compare(that.pathAverageLength, pathAverageLength) != 0) return false;
        if (!Arrays.equals(vertexLabels, that.vertexLabels)) return false;
        if (!Arrays.equals(degreeDistribution, that.degreeDistribution)) return false;
        return Arrays.deepEquals(shortestPaths, that.shortestPaths);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = vertexCount;
        result = 31 * result + Arrays.hashCode(vertexLabels);
        result = 31 * result + edgeCount;
        result = 31 * result + Arrays.hashCode(degreeDistribution);
        result = 31 * result + Arrays.deepHashCode(shortestPaths);
        temp = Double.doubleToLongBits(pathAverageLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "GraphDetails{" +
                "vertexCount=" + vertexCount +
                ", vertexLabels=" + Arrays.toString(vertexLabels) +
                ", edgeCount=" + edgeCount +
                ", degreeDistribution=" + Arrays.toString(degreeDistribution) +
                ", shortestPaths=" + Arrays.toString(shortestPaths) +
                ", pathAverageLength=" + pathAverageLength +
                '}';
    }
}
