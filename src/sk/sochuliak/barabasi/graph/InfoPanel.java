package sk.sochuliak.barabasi.graph;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 2490770620560535781L;
	
	private JLabel startPointLabel = new JLabel("Zaciatocny bod");
	private JLabel endPointLabel = new JLabel("Konecny bod");
	
	private JLabel startPointXLabel = new JLabel("X:");
	private JLabel startPointYLabel = new JLabel("Y:");
	
	private JLabel startPointXValueLabel = new JLabel(" ");
	private JLabel startPointYValueLabel = new JLabel(" ");
	
	private JLabel endPointXLabel = new JLabel("X:");
	private JLabel endPointYLabel = new JLabel("Y:");
	
	private JLabel endPointXValueLabel = new JLabel(" ");
	private JLabel endPointYValueLabel = new JLabel(" ");
	
	private JButton calculateButton = new JButton("Vypocitat");
	
	private JLabel resultLabel = new JLabel("Vysledok");
	private JLabel resultValueLabel = new JLabel(" ");
	
	private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	private double[] startPoint = null;
	private double[] endPoint = null;
	
	public InfoPanel() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.ipadx = 10;
		constrains.ipady = 10;
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 0, 3, 1);
		constrains.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.startPointLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 1, 1, 1);
		this.add(this.startPointXLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 1, 1, 2, 1);
		this.add(this.startPointXValueLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 2, 1, 1);
		this.add(this.startPointYLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 1, 2, 2, 1);
		this.add(this.startPointYValueLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 3, 3, 1);
		this.add(new JLabel(""), constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 4, 3, 1);
		this.add(this.endPointLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 5, 1, 1);
		this.add(this.endPointXLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 1, 5, 2, 1);
		this.add(this.endPointXValueLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 6, 1, 1);
		this.add(this.endPointYLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 1, 6, 2, 1);
		this.add(this.endPointYValueLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 7, 3, 1);
		this.add(new JLabel(""), constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 8, 3, 1);
		this.add(this.calculateButton, constrains);
		
		this.calculateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (startPoint != null && endPoint != null) {
					//List<double[]> points = ControllerService.getGraphController().getPointsBetweenXYItemsEntities(startPoint[0], endPoint[0]);
					//List<double[]> linearRegressionPoints = ControllerService.getGraphController().computeLine(points);
					//ControllerService.getGraphController().drawLinearRegression(linearRegressionPoints);
				} else {
					System.err.println("Nebol zvoleny zaciatocny alebo koncovy bod!");
				}
			}
		});
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 9, 3, 1);
		this.add(new JLabel(""), constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 10, 3, 1);
		this.add(this.resultLabel, constrains);
		
		constrains = this.setPropsToGridBagContraints(constrains, 0, 11, 3, 1);
		this.add(this.resultValueLabel, constrains);
	}
	
	private GridBagConstraints setPropsToGridBagContraints(
			GridBagConstraints constrains, int x, int y, int width, int height) {
		constrains.gridx = x;
		constrains.gridy = y;
		constrains.gridwidth = width;
		constrains.gridheight = height;
		return constrains;
	}
	
	public void setStartPoint(double x, double y) {
		this.startPoint = new double[]{x, y};
		String stringX = this.formatDouble(x);
		String stringY = this.formatDouble(y);
		this.startPointXValueLabel.setText(stringX);
		this.startPointYValueLabel.setText(stringY);
	}
	
	public void setEndPoint(double x, double y) {
		this.endPoint = new double[]{x, y};
		String stringX = this.formatDouble(x);
		String stringY = this.formatDouble(y);
		this.endPointXValueLabel.setText(stringX);
		this.endPointYValueLabel.setText(stringY);
	}
	
	public void setK(double k) {
		String stringK = this.formatDouble(k);
		this.resultValueLabel.setText(stringK);
	}
	
	private String formatDouble(double x) {
		return this.decimalFormat.format(x); 
	}
	
	public static InfoPanel create() {
		return new InfoPanel();
	}

}
