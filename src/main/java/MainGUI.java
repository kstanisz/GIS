import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithm.ConnectedComponentsAlgorithm;
import helper.GraphDetailsAnalyzer;
import helper.GraphInputReader;
import helper.GraphOutputWriter;
import model.Graph;
import model.GraphDetails;

public class MainGUI {

	private JFrame frame;

	private JRadioButton graphFromFileRbtn;
	private JRadioButton randomGraphRbtn;

	private JTextField verticesTextField;
	private JTextField probabilityTextField;

	private JButton readGraphBtn;
	private JButton runAlgorithmBtn;

	private JLabel filePathInfo;
	private JLabel resultsLabel;

	private File graphFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();

		readGraphBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				String choosertitle = "Wybierz plik ze struktur¹ grafu";
				chooser.setDialogTitle(choosertitle);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
				chooser.setFileFilter(filter);

				if (chooser.showOpenDialog(readGraphBtn) == JFileChooser.APPROVE_OPTION) {
					graphFile = chooser.getSelectedFile();
					filePathInfo.setText(graphFile.getAbsolutePath());
				}
			}
		});

		runAlgorithmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (graphFromFileRbtn.isSelected()) {
					if(graphFile == null){
						resultsLabel.setText(
								"Nie podano œcie¿ki do pliku z grafem.");
						resultsLabel.setForeground(Color.RED);
						return;
					}
					
					GraphInputReader inputReader;
					try {
						inputReader = new GraphInputReader(graphFile);
					} catch (FileNotFoundException e) {
						resultsLabel.setText(
								"Nie uda³o siê poprawnie odczytaæ pliku z grafem.");
						resultsLabel.setForeground(Color.RED);
						return;
					}
					runAlgorithm(inputReader.createGraph());
				} else if (randomGraphRbtn.isSelected()) {
					int vertices;
					double edgeProbability;
					try {
						vertices = Integer.parseInt(verticesTextField.getText());
						edgeProbability = Double.parseDouble(probabilityTextField.getText());
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						resultsLabel.setText(
								"B³êdny format liczby wierzcho³ków lub prawdopodobieñstwa wyst¹pienia krawêdzi.");
						resultsLabel.setForeground(Color.RED);
						return;
					}

					runAlgorithm(Graph.generateRandomGraph(vertices, edgeProbability));

				} else {
					resultsLabel.setText("Nie wybrano trybu dzia³ania programu");
					resultsLabel.setForeground(Color.RED);
				}
			}
		});

	}

	private void runAlgorithm(Graph graph) {
		
		long startTime = System.currentTimeMillis();

		ConnectedComponentsAlgorithm connectedComponentsAlgorithm = new ConnectedComponentsAlgorithm();
		Graph connectedComponent = connectedComponentsAlgorithm.getGreatestConnectedComponent(graph);

		GraphDetailsAnalyzer graphDetailsAnalyzer = new GraphDetailsAnalyzer(connectedComponent);
		GraphDetails connectedComponentDetails = graphDetailsAnalyzer.analyze();

		long endTime = System.currentTimeMillis();

		GraphOutputWriter outputWriter;
		try {
			outputWriter = new GraphOutputWriter(connectedComponentDetails, (endTime - startTime));
		} catch (FileNotFoundException e) {
			resultsLabel.setText("Wyst¹pi³ b³¹d podczas tworzenia pliku wynikowego.");
			resultsLabel.setForeground(Color.RED);
			return;
		}

		outputWriter.createReport();
		resultsLabel.setText("Program pomyœlnie zakoñczy³ swoje dzia³anie.");
		resultsLabel.setForeground(Color.GREEN);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 501, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ButtonGroup buttonGroup = new ButtonGroup();

		graphFromFileRbtn = new JRadioButton("Wczytaj graf z pliku");
		graphFromFileRbtn.setSelected(true);
		graphFromFileRbtn.setFont(new Font("Arial", Font.PLAIN, 11));
		graphFromFileRbtn.setBounds(49, 21, 142, 23);
		frame.getContentPane().add(graphFromFileRbtn);

		randomGraphRbtn = new JRadioButton("Generuj losowy graf");
		randomGraphRbtn.setFont(new Font("Arial", Font.PLAIN, 11));
		randomGraphRbtn.setBounds(49, 84, 142, 23);
		frame.getContentPane().add(randomGraphRbtn);

		buttonGroup.add(graphFromFileRbtn);
		buttonGroup.add(randomGraphRbtn);

		readGraphBtn = new JButton("Wczytaj graf");
		readGraphBtn.setFont(new Font("Arial", Font.PLAIN, 11));
		readGraphBtn.setBounds(241, 21, 208, 23);
		readGraphBtn.setEnabled(true);
		frame.getContentPane().add(readGraphBtn);

		verticesTextField = new JTextField();
		verticesTextField.setBounds(363, 124, 86, 20);
		frame.getContentPane().add(verticesTextField);
		verticesTextField.setColumns(10);

		probabilityTextField = new JTextField();
		probabilityTextField.setBounds(363, 168, 86, 20);
		frame.getContentPane().add(probabilityTextField);
		probabilityTextField.setColumns(10);

		JLabel verticesLbl = new JLabel("Liczba wierzcho³ków:");
		verticesLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		verticesLbl.setBounds(241, 127, 112, 14);
		frame.getContentPane().add(verticesLbl);

		JLabel probabilityLbl = new JLabel("Prawdopodobieñstwo wyst¹pienia krawêdzi:");
		probabilityLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		probabilityLbl.setBounds(127, 167, 226, 23);
		frame.getContentPane().add(probabilityLbl);

		runAlgorithmBtn = new JButton("ZnajdŸ najwiêksz¹ spójn¹ sk³adow¹");
		runAlgorithmBtn.setFont(new Font("Arial", Font.PLAIN, 11));
		runAlgorithmBtn.setBounds(177, 230, 272, 23);
		frame.getContentPane().add(runAlgorithmBtn);

		JSeparator firstSeparator = new JSeparator();
		firstSeparator.setBounds(10, 75, 465, 2);
		frame.getContentPane().add(firstSeparator);

		JLabel autorsLbl = new JLabel("Autorzy: Piotr Ogrodnicki, Kacper Staniszewski");
		autorsLbl.setFont(new Font("Arial", Font.PLAIN, 9));
		autorsLbl.setBounds(10, 309, 239, 14);
		frame.getContentPane().add(autorsLbl);

		JSeparator secondSeparator = new JSeparator();
		secondSeparator.setBounds(10, 213, 465, 2);
		frame.getContentPane().add(secondSeparator);

		filePathInfo = new JLabel();
		filePathInfo.setFont(new Font("Arial", Font.PLAIN, 10));
		filePathInfo.setBounds(49, 51, 400, 14);
		frame.getContentPane().add(filePathInfo);

		resultsLabel = new JLabel();
		resultsLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		resultsLabel.setBounds(49, 274, 400, 14);
		frame.getContentPane().add(resultsLabel);
	}
}
