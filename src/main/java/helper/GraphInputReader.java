package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Piotr on 2017-03-26.
 */
public class GraphInputReader {

    private String url;
    private int vertexCount;
    private boolean [][] adjacencyMatrix;
    private char [][] charsMatrix;
    private File inputFile;
    private Scanner input;

    public GraphInputReader (String path) {
        this.url = path;
        openFile(this.url);
        this.vertexCount = countVertices();
        this.charsMatrix = createCharMatrix(this.vertexCount);
        this.adjacencyMatrix = createAdjacencyMatrix(this.charsMatrix,this.vertexCount);
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

    public boolean[][] getAdjacencyMatrix(){
        return this.adjacencyMatrix;
    }

    private void openFile(String path) {
        try {
            this.inputFile = new File(path);
            this.input = new Scanner(this.inputFile);
        }catch (FileNotFoundException e){
            System.err.println("File does not exist!");
        }
    }

    private int countVertices(){
        int counter = 0;
        char[] firstLineArray = input.nextLine().toCharArray();
        for (char value : firstLineArray){
            if(value == ';') counter++;
        }
        return counter;
    }

    private char[][] createCharMatrix(int size){
        char[][] temporaryCharMatrix = new char [size][size];
        for(int i =0; i < size; i++){
            temporaryCharMatrix [i] = readAndConvertLine(this.input).toCharArray();
        }
        return temporaryCharMatrix;
    }

    private String readAndConvertLine(Scanner in){
        String line = in.nextLine();
        line = line.substring(line.indexOf(";"));
        line = line.replace(".0","");
        line = line.replace(";","");
        return line;
    }

    private boolean[][] createAdjacencyMatrix(char[][] inputMatrix, int size){
        boolean[][] temporaryBooleanMatrix = new boolean[size][size];
        for (int i =0; i < size; i++){
            for (int j =0; j< size; j++){
                if(inputMatrix[i][j]!= ';' && inputMatrix[i][j]!= '0')
                    temporaryBooleanMatrix[i][j] = true;
                else temporaryBooleanMatrix[i][j] = false;
            }
        }
        return temporaryBooleanMatrix;
    }









}
