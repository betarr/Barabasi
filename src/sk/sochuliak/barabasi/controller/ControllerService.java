package sk.sochuliak.barabasi.controller;

import org.jfree.chart.JFreeChart;

import sk.sochuliak.barabasi.graph.InfoPanel;

public class ControllerService {

	private static InfoPanelController leftPanelController;
	
	private static GraphController graphController;
	
	public static void registerInfoPanelController(InfoPanel infoPanel) {
		ControllerService.leftPanelController = new InfoPanelController(infoPanel);
	}
	
	public static void registerGraphPanelController(JFreeChart chart) {
		ControllerService.graphController = new GraphController(chart);
	}

	public static InfoPanelController getInfoPanelController() {
		return leftPanelController;
	}

	public static GraphController getGraphController() {
		return graphController;
	}
	
	
}
