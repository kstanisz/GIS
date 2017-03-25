package helper;

import model.Graph;

import java.util.List;

public class GraphOperationsHelper {

    public Graph createSubgraph(Graph graph, List<Integer> vertices) {
        int vertexCounter = vertices.size();
        boolean[][] adjacencyMatrix = new boolean[vertexCounter][vertexCounter];

        for (int i = 0; i < vertexCounter; i++) {
            for (int j = 0; j < vertexCounter; j++) {
                adjacencyMatrix[i][j] = graph.isEdge(vertices.get(i),vertices.get(j));
            }
        }

        Graph subgraph = Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix);
        subgraph.setVertexLabels(vertices.stream().mapToInt(i->i).toArray());

        return subgraph;
    }
}
