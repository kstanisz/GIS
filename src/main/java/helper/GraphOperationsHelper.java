package helper;

import model.Graph;

import java.util.List;

public class GraphOperationsHelper {

    public Graph createSubgraph(Graph graph, List<Integer> vertices) {
        int vertexCount = vertices.size();
        boolean[][] adjacencyMatrix = new boolean[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                adjacencyMatrix[i][j] = graph.isEdge(vertices.get(i),vertices.get(j));
            }
        }

        Graph subgraph = Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix);
        subgraph.setVertexLabels(vertices.stream().mapToInt(i->i).toArray());

        return subgraph;
    }
}
