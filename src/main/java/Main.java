import algorithm.ConnectedComponentsAlgorithm;
import helper.GraphDetailsAnalyzer;
import helper.GraphExportHelper;
import helper.GraphInputReader;
import helper.GraphOutputWriter;
import model.Graph;
import model.GraphDetails;

import java.io.FileNotFoundException;


public class Main {

    private enum ApplicationRunMode {
        GRAPH_FROM_FILE,
        GRAPH_RANDOM,
        UNDEFINED
    }
    /*

    Arguments:
    1) Read graph from file: java Main -f path_to_file // java Main test.csv
    2) Random graph: java Main -r vertices probability // java Main 3 0.2

     */
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Too few arguments.");
            return;
        }

        ApplicationRunMode mode = readRunMode(args[0]);
        if (mode.equals(ApplicationRunMode.UNDEFINED)) {
            System.out.println("Undefined application mode.");
            System.out.println("-f read graph from file");
            System.out.println("-r create random graph");
            return;
        }

        long startTime = System.currentTimeMillis();

        Graph graph;
        try {
            graph = createGraphDueToApplicationMode(mode, args);
        } catch (Exception e) {
            System.out.println("Can't read input file. Error message: " + e.getMessage());
            return;
        }

        ConnectedComponentsAlgorithm connectedComponentsAlgorithm = new ConnectedComponentsAlgorithm();
        Graph connectedComponent = connectedComponentsAlgorithm.getGreatestConnectedComponent(graph);

        GraphDetailsAnalyzer graphDetailsAnalyzer = new GraphDetailsAnalyzer(connectedComponent);
        GraphDetails connectedComponentDetails = graphDetailsAnalyzer.analyze();

        long endTime = System.currentTimeMillis();

        GraphOutputWriter outputWriter;
        try {
            outputWriter = new GraphOutputWriter(connectedComponentDetails,(endTime - startTime));
        } catch (FileNotFoundException e) {
            System.out.println("Error while creating output file: "+e.getMessage());
            return;
        }

        outputWriter.createReport();
        System.out.println("Application successfully ended.");
    }

    private static ApplicationRunMode readRunMode(String mode) {
        switch(mode){
            case "-f":
                return ApplicationRunMode.GRAPH_FROM_FILE;
            case "-r":
                return ApplicationRunMode.GRAPH_RANDOM;
            default:
                return ApplicationRunMode.UNDEFINED;
        }
    }

    private static Graph createGraphDueToApplicationMode(ApplicationRunMode mode, String[] args) throws Exception {
        if (mode.equals(ApplicationRunMode.GRAPH_FROM_FILE)) {
            String path = args[1];
            try {
                GraphInputReader inputReader = new GraphInputReader(path);
                return inputReader.createGraph();
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("File with specified path does not exist: " + path);
            } catch (Exception e){
            	throw new IllegalArgumentException("Read graph is a directed graph!");
            }
        } else {
            int vertices;
            double edgeProbability;
            try {
                vertices = Integer.parseInt(args[1]);
                edgeProbability = Double.parseDouble(args[2]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid vertices or edge probability format.");
            }

            Graph graph = Graph.generateRandomGraph(vertices, edgeProbability);

            GraphExportHelper graphExportHelper =  new GraphExportHelper();
            graphExportHelper.exportToCsv(graph);

            return graph;
        }
    }
}