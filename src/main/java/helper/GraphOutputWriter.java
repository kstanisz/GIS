package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import model.GraphDetails;

public class GraphOutputWriter {

	private GraphDetails graphDetails;
	private PrintWriter writer;
	private long executionTime;

	public GraphOutputWriter(GraphDetails graphDetails, Long exececutionTime) throws FileNotFoundException {
		this.graphDetails = graphDetails;
		this.executionTime = exececutionTime;
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
		printFooter();
		closeWriter();
	}

	private void openWriter() throws FileNotFoundException {
		String directoryName = "output";
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}

		Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String fileName = directoryName + "/output_" + formatter.format(Calendar.getInstance().getTime()) + ".txt";

		this.writer = new PrintWriter(fileName);
	}

	private void closeWriter() {
		this.writer.close();
	}

	private void printHeader() {
		this.writer.println("%- Raport analizy najwiekszej skladowej wspolnej grafu - ");
		this.writer.println();
	}

	private void printAdjacencyMatrix() {
		this.writer.println("%Macierz sasiedztwa:");
		String line;
		for (int i = 0; i < this.graphDetails.getVertexCount(); i++) {
			line = "";
			for (int j = 0; j < this.graphDetails.getVertexCount(); j++) {
				if (this.graphDetails.getAdjacencyMatrix()[i][j])
					line += "1, ";
				else
					line += "0, ";
			}
			line = line.substring(0, line.lastIndexOf(','));
			this.writer.println(line);
		}
		this.writer.println();
	}

	private void printVertexCount() {
		printModule("Liczba wierzcholkow:", this.graphDetails.getVertexCount());
	}

	private void printEdgeCount() {
		printModule("Liczba krawedzi:", this.graphDetails.getEdgeCount());
	}

	private void printVertexList() {
		printModule("Lista wierzcholkow:", convertString(Arrays.toString(this.graphDetails.getVertexLabels())));
	}

	private void printDegreeDistribution() {
		printModule("Rozklad stopni wierzcholkow:",
				convertString(Arrays.toString(this.graphDetails.getDegreeDistribution())));
	}

	private void printShortestWay() {
		this.writer.println("%Macierz najkrotszych polaczen pomiedzy wierzcholkami: ");
		for (int i = 0; i < this.graphDetails.getVertexCount(); i++) {
			this.writer.println(convertString(Arrays.toString(this.graphDetails.getShortestPaths()[i])));
		}
		this.writer.println();
	}

	private void printAverageLenght() {
		printModule("Srednia odleglosc pomiedzy wierzcholkami:", this.graphDetails.getPathAverageLength());
	}

	private void printModule(String header, Object value) {
		this.writer.println("%" + header);
		this.writer.println(value);
		this.writer.println();
	}

	private void printFooter() {
		this.writer.println("%Czas dzialania programu: " + this.executionTime + " ms.");
		this.writer.println();
	}

	private String convertString(String stringToConvert) {
		return stringToConvert.replace("[", "").replace("]", "");
	}

}
