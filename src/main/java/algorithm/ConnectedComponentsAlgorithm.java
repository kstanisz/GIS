package algorithm;

import helper.GraphOperationsHelper;
import model.Graph;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsAlgorithm {

    private int[] vertexConnectedComponentIndexes;
    private int currentConnectedComponentIndex = 0;
    private int greatestConnectedComponentSize = 0;
    private int greatestConnectedComponentIndex = 0;

    private GraphOperationsHelper graphOperationsHelper = new GraphOperationsHelper();

    public Graph getGreatestConnectedComponent(Graph graph) {
        vertexConnectedComponentIndexes = new int[graph.getVertexCount()];

        for (int i = 0; i < vertexConnectedComponentIndexes.length; i++) {
            if (vertexConnectedComponentIndexes[i] != 0) {
                continue;
            }

            currentConnectedComponentIndex++;
            int connectedComponentSize = runDFS(graph, i, 0);
            if (connectedComponentSize > greatestConnectedComponentSize) {
                greatestConnectedComponentSize = connectedComponentSize;
                greatestConnectedComponentIndex = currentConnectedComponentIndex;
            }
        }

        List<Integer> greatestConnectedComponentVertexList = createVertexListOfGreatestConnectedComponent();
        Graph greatestConnectedComponent = graphOperationsHelper.createSubgraph(graph, greatestConnectedComponentVertexList);

        return greatestConnectedComponent;
    }

    /*
        Returns number of vertexes in connected component
     */
    private int runDFS(Graph graph, int vertexIndex, int connectedComponentSize) {
        vertexConnectedComponentIndexes[vertexIndex] = currentConnectedComponentIndex;
        connectedComponentSize++;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.isEdge(vertexIndex, i) && vertexConnectedComponentIndexes[i] == 0) {
                runDFS(graph, i, connectedComponentSize);
            }
        }

        return connectedComponentSize;
    }

    private List<Integer> createVertexListOfGreatestConnectedComponent() {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < vertexConnectedComponentIndexes.length; i++) {
            if (vertexConnectedComponentIndexes[i] == greatestConnectedComponentIndex) {
                vertices.add(i);
            }
        }

        return vertices;
    }
}