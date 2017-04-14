package helper;


import model.GraphDetails;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class GraphOutputWriter {

    private GraphDetails graphDetails;
    private PrintWriter writer;

    public GraphOutputWriter(GraphDetails graphDetails) throws FileNotFoundException{
        this.graphDetails = graphDetails;
        openWriter();
    }

    public void createReport() {
        printHeader();
        printAdjacencyMatrix();
        printVertexCount();
        printVertexList();
        printEdgeCount();
        printDegreeDistribution();
        printShortestWay();
        printAverageLenght();
        closeWriter();
    }

    private void openWriter() throws FileNotFoundException{
        this.writer = new PrintWriter("output.txt");
    }

    private void closeWriter(){
        this.writer.close();
    }

    private void printHeader(){
        this.writer.println("%- Raport analizy najwiekszej skladowej wspolnej grafu - ");
        this.writer.println();
    }

    private void printAdjacencyMatrix(){
        this.writer.println("%Macierz sasiedztwa:");
        String line;
        for (int i=0; i < this.graphDetails.getVertexCount(); i++) {
            line ="";
            for (int j=0; j < this.graphDetails.getVertexCount(); j++) {
                if(this.graphDetails.getAdjacencyMatrix()[i][j])
                    line += "1, ";
                else
                    line +="0, ";
            }
            line = line.substring(0,line.lastIndexOf(','));
            this.writer.println(line);
        }
        this.writer.println();
    }

    private void printVertexCount(){
        printModule("Liczba wierzcholkow:", this.graphDetails.getVertexCount());
    }

    private void printEdgeCount(){
        printModule("Liczba krawedzi:", this.graphDetails.getEdgeCount());
    }

    private void printVertexList(){
        printModule("Lista wierzcholkow:", convertString(Arrays.toString(this.graphDetails.getVertexLabels())));
    }

    private void printDegreeDistribution(){
        printModule("Rozklad stopni wierzcholkow:", convertString(Arrays.toString(this.graphDetails.getDegreeDistribution())));
    }

    private void printShortestWay(){
        this.writer.println("%Macierz najkrotszych polaczen pomiedzy wierzcholkami: " );
        for (int i=0; i<this.graphDetails.getVertexCount(); i++) {
            this.writer.println(convertString(Arrays.toString(this.graphDetails.getShortestPaths()[i])));
        }
        this.writer.println();
    }

    private void printAverageLenght(){
        printModule("Srednia odleglosc pomiedzy wierzcholkami:", this.graphDetails.getPathAverageLength());
    }

    private void printModule(String header, Object value){
        this.writer.println("%" + header);
        this.writer.println(value);
        this.writer.println();
    }

    private String convertString ( String stringToConvert){
        return stringToConvert.replace("[","").replace("]","");
    }

}
