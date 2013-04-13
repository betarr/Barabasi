package sk.sochuliak.barabasi.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import sk.sochuliak.barabasi.util.LinearRegression;


public class GraphController {

	private JFreeChart chart;
	private XYPlot plot;
	private XYSeriesCollection dataset;
	
	public GraphController(JFreeChart chart) {
		this.chart = chart;
		
		Plot plot = this.chart.getPlot();
		if (plot instanceof XYPlot) {
			this.plot = (XYPlot) plot;
			this.dataset = (XYSeriesCollection) this.plot.getDataset();
		} else {
			System.err.println("Plot nie je instancia triedy XYPlot");
		}
	}

	public List<double[]> getPointsBetweenXYItemsEntities(double startX, double endX) {
		List<double[]> result = new ArrayList<double[]>();
		
		int seriesIndex = 0;
		int itemsCount = this.dataset.getItemCount(seriesIndex);
		for (int i = 0; i < itemsCount; i++) {
			double x = this.dataset.getXValue(seriesIndex, i);
			if (x >= startX && x <= endX) {
				double y = this.dataset.getYValue(seriesIndex, i);
				result.add(new double[]{x, y});
			}
		}
		return result;
	}

	public List<double[]> computeLine(List<double[]> points) {
		LinearRegression lr = new LinearRegression(points);
		List<double[]> linearRegression = lr.doLinearRegression();
		
		ControllerService.getInfoPanelController().setResult(lr.getK());
		
		return linearRegression;
	}

	public void drawLinearRegression(List<double[]> linearRegressionPoints) {
		String seriesName = "Linearna Regresia";
		
		int testSeriesIndex = this.dataset.getSeriesIndex(seriesName);
		
		if (testSeriesIndex != -1) {
			this.dataset.removeSeries(testSeriesIndex);
		}
		
		XYSeries linearRegressionSeries = this.createSeries("Linearna Regresia", linearRegressionPoints);
		this.dataset.addSeries(linearRegressionSeries);
		
		int seriesIndex = this.dataset.getSeriesIndex(seriesName);
		XYLineAndShapeRenderer lrRenderer = (XYLineAndShapeRenderer) this.plot.getRenderer();
		lrRenderer.setSeriesShapesVisible(seriesIndex, false);
		lrRenderer.setSeriesLinesVisible(seriesIndex, true);
		lrRenderer.setSeriesPaint(seriesIndex, Color.BLUE);
		this.plot.setRenderer(lrRenderer);
	}

	private XYSeries createSeries(String name, List<double[]> linearRegressionPoints) {
		XYSeries result = new XYSeries(name);
		for (double[] point : linearRegressionPoints) {
			result.add(point[0], point[1]);
		}
		return result;
	}
}
