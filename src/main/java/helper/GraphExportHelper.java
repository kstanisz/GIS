package helper;

import model.Graph;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GraphExportHelper {

    private static final char CSV_SEPARATOR = ';';

    public void exportToCsv(Graph graph) throws IOException{

		String directoryName = "generated_graphs";
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		
        String fileName = directoryName + "/random_graph_"+getCurrentTimeInStringFormat()+".csv";
        PrintWriter writer = new PrintWriter(new File(fileName));

        int[] vertexLabels = graph.getVertexLabels();
        boolean[][] adjacencyMatrix  = graph.getAdjacencyMatrix();

        writer.write(createVertexLabelsLine(vertexLabels));
        for(int i = 0; i<vertexLabels.length; i++){
            writer.write(createLineForVertex(vertexLabels[i],adjacencyMatrix[i]));
        }

        writer.close();
    }

    private String getCurrentTimeInStringFormat(){
        Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

        return formatter.format(Calendar.getInstance().getTime());
    }


    private String createVertexLabelsLine(int[] vertexLabels){
        StringBuilder builder = new StringBuilder();

        for(int label: vertexLabels){
            builder.append(CSV_SEPARATOR);
            builder.append(label);
        }
        builder.append("\n");

        return builder.toString();
    }

    private String createLineForVertex(int vertex, boolean[] adjacencyMatrixRow){
        StringBuilder builder = new StringBuilder();

        builder.append(vertex);
        for(boolean val : adjacencyMatrixRow){
            builder.append(CSV_SEPARATOR);
            builder.append(val ? 1 : 0);
        }
        builder.append("\n");

        return builder.toString();
    }
}