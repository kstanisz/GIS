import helper.GraphInputReader;
import helper.ReportCreator;
import model.Graph;
import algorithm.GraphAnalyser;

public class Main {

    /*

    Arguments

     */
    public static void main(String[] args) {

        GraphInputReader graphInputReader = new GraphInputReader("input.csv");
        Graph graph = Graph.createGraphFromAdjacencyMatrix(graphInputReader.getAdjacencyMatrix());
        GraphAnalyser analizator = new GraphAnalyser(graph);

        boolean[][] matrix = graph.getAdjacencyMatrix();

        for (int i =0 ; i < graphInputReader.getVertexCount(); i++) {
            String line ="";
            for (int j = 0; j < graphInputReader.getVertexCount(); j++) {
                line +=  matrix[i][j] + " ";
            }
            System.out.println(line);
        }

        System.out.println();

        System.out.println("Liczba wierzchołków: " + analizator.getVertexCount());

        String line = "";

        for (int i =0; i < analizator.getVertexCount(); i++)
        {
            line += analizator.getDegreeDistribution()[i] + " ";
        }

        System.out.println("Rozklad stopni: " + line);

        line ="";

        for (int i =0; i < analizator.getVertexCount(); i++)
        {
            line += analizator.getVertexList()[i] + " ";
        }

        System.out.println("Lista wierzchołków: " + line);
        System.out.println("Liczba krawedzi: " + analizator.getEdgeCount());




        int[][] shortestWay = analizator.getShortestWay();



        for (int i =0 ; i < analizator.getVertexCount(); i++) {
            line ="";
            for (int j = 0; j < analizator.getVertexCount(); j++) {
                line +=  shortestWay[i][j] + " ";
            }
            System.out.println(line);
        }

        double srednia = analizator.getAverageLength();

        System.out.println("Srednia dlugosc drogi " + srednia);

        ReportCreator reportCreator = new ReportCreator(analizator);


    }
}
