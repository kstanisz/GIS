package helper;

import model.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphInputReader {

    private Scanner input;

    public GraphInputReader(String path) throws FileNotFoundException {
        openFile(path);
    }

    private void openFile(String path) throws FileNotFoundException {
        File inputFile = new File(path);
        this.input = new Scanner(inputFile);
    }

    public Graph createGraph() throws Exception {
        int vertexCount = countVertices();
        char[][] charsMatrix = createCharMatrix(vertexCount);
        boolean[][] adjacencyMatrix = createAdjacencyMatrix(charsMatrix, vertexCount);


        if(!checkMatrix(adjacencyMatrix)){
            throw new Exception();
        }

        return Graph.createGraphFromAdjacencyMatrix(adjacencyMatrix);
    }

    private boolean checkMatrix(boolean[][] adjacencyMatrix){
        boolean isSymetrical = true;

        for(int i = 0; i < adjacencyMatrix.length;i++){
            for(int j = 0; j < adjacencyMatrix.length; j++){
                if(i!=j && adjacencyMatrix[i][j] != adjacencyMatrix[j][i]){
                    return false;
                }
            }
        }

        return true;

    }

    private int countVertices() {
        int counter = 0;
        char[] firstLineArray = input.nextLine().toCharArray();
        for (char value : firstLineArray) {
            if (value == ';') counter++;
        }
        return counter;
    }

    private char[][] createCharMatrix(int size) {
        char[][] temporaryCharMatrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            temporaryCharMatrix[i] = readAndConvertLine(this.input).toCharArray();
        }
        return temporaryCharMatrix;
    }

    private String readAndConvertLine(Scanner in) {
        String line = in.nextLine();
        line = line.substring(line.indexOf(";"));
        line = line.replace(".0", "");
        line = line.replace(";", "");
        return line;
    }

    private boolean[][] createAdjacencyMatrix(char[][] inputMatrix, int size) {
        boolean[][] temporaryBooleanMatrix = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (inputMatrix[i][j] != ';' && inputMatrix[i][j] != '0')
                    temporaryBooleanMatrix[i][j] = true;
                else temporaryBooleanMatrix[i][j] = false;
            }
        }
        return temporaryBooleanMatrix;
    }
}
