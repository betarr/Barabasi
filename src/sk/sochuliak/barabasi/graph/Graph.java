package sk.sochuliak.barabasi.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import sk.sochuliak.barabasi.controller.ControllerService;

public class Graph extends JFrame {
	private static final long serialVersionUID = 16957900680253030L;
	
	public Graph(GraphConfiguration config) {
		this.setTitle(config.getTitle());
		
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(this.createChartPanel(config), BorderLayout.CENTER);
		
		InfoPanel infoPanel = InfoPanel.create();
		container.add(infoPanel, BorderLayout.EAST);
		ControllerService.registerInfoPanelController(infoPanel);
	}
	
	private ChartPanel createChartPanel(final GraphConfiguration config) {
		final JFreeChart chart = this.createChart(config);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(640, 480));
		
		chartPanel.addChartMouseListener(new ChartMouseListener() {

			@Override
			public void chartMouseClicked(ChartMouseEvent e) {
				ChartEntity entity = e.getEntity();
				if (entity instanceof XYItemEntity) {
					XYItemEntity itemEntity = (XYItemEntity) entity;
					double[] coords = GraphUtils.getCoordFromXYItemEntity(itemEntity);
					ControllerService.getInfoPanelController().setPoint(coords[0], coords[1]);
				}
			}

			@Override
			public void chartMouseMoved(ChartMouseEvent e) {}
		});
		
		ControllerService.registerGraphPanelController(chart);
		return chartPanel;
	}
	
	private JFreeChart createChart(final GraphConfiguration config) {
		final XYDataset dataset = this.createDataset(config.getData());
		
		final JFreeChart chart = ChartFactory.createXYLineChart(
				config.getTitle(),
				config.getxAxisLabel(),
				config.getyAxisLabel(),
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		
		chart.setBackgroundPaint(Color.WHITE);
		
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.WHITE);
		
		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
		renderer.setSeriesPaint(0, Color.RED);
		plot.setRenderer(renderer);
		
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return chart;
	}
	
	private XYDataset createDataset(final Map<String, List<double[]>> data) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		Set<String> seriesNames = data.keySet();
		for (String serieName : seriesNames) {
			List<double[]> points = data.get(serieName);
			if (points.size() > 0) {
				XYSeries series = new XYSeries(serieName);
				for (double[] point : points) {
					series.add(point[0], point[1]);
				}
				dataset.addSeries(series);
			}		
		}	
		return dataset;
	}
	
	public static void showGraph(final GraphConfiguration config) {
		Graph graph = new Graph(config);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(config.getGraphSize());
		graph.setLocationRelativeTo(null);
		graph.setVisible(true);
	}

}
