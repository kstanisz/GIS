package algorithm;

import model.Graph;

public class GraphAnalyser {

    private Graph analyzedGraph;
    private int graphSize;
    private int[][] shortestWay;
    private int edgeCount;
    private int[] degreeDistribution;

    public GraphAnalyser (Graph inputGraph){
       readGraph(inputGraph);
       this.shortestWay = calculateShortestWay();
       this.edgeCount = calculateEdgeCount();
       this.degreeDistribution = calculateDegreeDistribution();
    }

    private void readGraph(Graph inputGraph){
        this.analyzedGraph = inputGraph;
        this.graphSize = inputGraph.getVertexCount();
    }

    public int getVertexCount(){
        return this.analyzedGraph.getVertexCount();
    }

    public int[] getVerticesList(){
        return this.analyzedGraph.getVertexLabels();
    }

    public int[] getDegreeDistribution(){
        return this.degreeDistribution;
    }

    public int getEdgeCount(){
        return this.edgeCount;
    }

    public int[][] getShortestWay(){
        return this.shortestWay;
    }

    public double getAverageLength(){
        return calculateAverageLength();
    }
    private int[] calculateDegreeDistribution(){
        int[] degreeDistribution = new int [this.graphSize];
        for(int i =0; i < this.graphSize; i++)
            degreeDistribution[i] = calculateVertexDegree(i);
        return  degreeDistribution;
    }

    private int calculateVertexDegree(int index){
        int vertexDegree = 0;
        for(int i =0; i < this.graphSize; i++){
            if(analyzedGraph.getAdjacencyMatrix()[index][i])
                vertexDegree++;
        }
        return vertexDegree;
    }

    private int calculateEdgeCount(){
        double edgeCount = 0;
        for(int i = 0; i < this.graphSize; i++){
            for(int j =0; j < this.graphSize; j++){
                if(analyzedGraph.getAdjacencyMatrix()[i][j]) edgeCount++;
            }
        }
        return (int) edgeCount/2;
    }

    private int[][] calculateShortestWay(){
        int [][] shortestWay = new int [this.graphSize][this.graphSize];
        for(int i =0 ; i < this.graphSize; i++){
            for(int j = 0; j < this.graphSize; j++){
                if(j>i)
                    shortestWay[i][j] = findShortestPath(i,j);
                else
                    shortestWay[i][j] = 0;
            }
        }
        shortestWay = mirrorMatrix(shortestWay);
        return shortestWay;
    }

    private int findShortestPath(int startVertex, int finishVertex){
        ShortestPathAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithm(this.analyzedGraph, startVertex, finishVertex);
        return shortestPathAlgorithm.findShortestPath();
    }

    private int[][] mirrorMatrix (int[][] matrixToMirror){
        for(int i =0; i < this.graphSize; i++)
            for(int j = 0; j < this.graphSize; j++)
                if(j<i) matrixToMirror[i][j] = matrixToMirror[j][i];
        return matrixToMirror;
    }

    private double calculateAverageLength(){
        double sum = addShortestWay();
        double factor = this.graphSize * (this.graphSize-1);
        return sum/factor;
    }

    private double addShortestWay(){
        double sum = 0;
        for(int i =0; i < this.graphSize; i++)
            for (int j = 0; j < this.graphSize; j++)
                sum += this.shortestWay[i][j];
        return sum;
    }
}
