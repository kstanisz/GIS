package algorithm;

import model.Graph;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathAlgorithm {

    private Graph analyzedGraph;
    private int startVertex;
    private int finishVertex;
    private int [] antecedents;
    private boolean [] visitedVertices;
    private Queue<Integer> verticesToVisit;

    public ShortestPathAlgorithm(Graph graph, int startVertex, int finishVertex){
        this.analyzedGraph = graph;
        this.startVertex = startVertex;
        this.finishVertex = finishVertex;
        this.antecedents = new int[graph.getVertexCount()];
        this.visitedVertices = new boolean [graph.getVertexCount()];
        this.verticesToVisit = new LinkedList<>();

        this.antecedents[startVertex] = -1;
        this.verticesToVisit.add(startVertex);
        this.visitedVertices[startVertex] = true;
    }

    public int findShortestPath(){
        int currentVertex;
        while(!this.verticesToVisit.isEmpty()){
            currentVertex = this.verticesToVisit.poll();
            this.visitedVertices[currentVertex] = true;
            if(isFinishVertexVisited())
                break;
            else
                visitAndCheckAllAdjacentVertex(currentVertex);
        }
        return calculatePathLength(this.antecedents,this.startVertex,this.finishVertex);
    }

    private boolean isFinishVertexVisited(){
        return this.visitedVertices[this.finishVertex];
    }

    private void visitAndCheckAllAdjacentVertex(int vertexToCheck){
        Queue<Integer> adjacentVertex = createAdjacencyVertexQueue(vertexToCheck);
        int supportVertex;
        while(!adjacentVertex.isEmpty()){
            supportVertex = adjacentVertex.peek();

            if(!this.visitedVertices[supportVertex]) {
                this.antecedents[supportVertex] = vertexToCheck;
                this.visitedVertices[supportVertex] = true;
                this.verticesToVisit.add(adjacentVertex.poll());
            }else
                adjacentVertex.poll();
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