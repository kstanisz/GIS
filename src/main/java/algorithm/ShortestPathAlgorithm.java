package algorithm;

import model.Graph;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathAlgorithm {

    private Graph analyzedGraph;
    private int startVertex;
    private int finishVertex;
    private int [] pathOrder;
    private boolean [] visitedVertex;
    private Queue<Integer> vertexToVisit;

    public ShortestPathAlgorithm(Graph graph, int startVertex, int finishVertex){
        this.analyzedGraph = graph;
        this.startVertex = startVertex;
        this.finishVertex = finishVertex;
        this.pathOrder = new int[graph.getVertexCount()];
        this.visitedVertex = new boolean [graph.getVertexCount()];
        this.vertexToVisit = new LinkedList<>();

        this.pathOrder[startVertex] = -1;
        this.vertexToVisit.add(startVertex);
        this.visitedVertex[startVertex] = true;
    }

    public int findShortestPath(){
        int currentVertex;
        while(!this.vertexToVisit.isEmpty()){
            currentVertex = this.vertexToVisit.poll();
            this.visitedVertex[currentVertex] = true;
            if(isFinishVertexVisited())
                break;
            else
                visitAndCheckAllAdjacentVertex(currentVertex);
        }
        return calculatePathLength(this.pathOrder,this.startVertex,this.finishVertex);
    }

    private boolean isFinishVertexVisited(){
        return this.visitedVertex[this.finishVertex];
    }

    private void visitAndCheckAllAdjacentVertex(int vertexToCheck){
        Queue<Integer> adjacentVertex = createAdjacencyVertexQueue(vertexToCheck);
        int supportVertex;
        while(!adjacentVertex.isEmpty()){
            supportVertex = adjacentVertex.peek();
            if(!this.visitedVertex[supportVertex]) {
                this.pathOrder[supportVertex] = vertexToCheck;
                this.visitedVertex[supportVertex] = true;
                this.vertexToVisit.add(adjacentVertex.poll());
            }else adjacentVertex.poll();
        }
    }

    private Queue createAdjacencyVertexQueue (int vertex){
        Queue<Integer> adjacency = new LinkedList<>();
        for(int i = 0; i < analyzedGraph.getVertexCount(); i++)
            if(this.analyzedGraph.getAdjacencyMatrix()[vertex][i]) adjacency.add(i);
        return adjacency;
    }

    private int calculatePathLength(int[] pathOrder, int startVertex, int finishVertex){
        int indexVertex = finishVertex;
        int pathLength = 0;
        while(indexVertex != startVertex){
            indexVertex = pathOrder[indexVertex];
            pathLength++;
        }
        return pathLength;
    }
}