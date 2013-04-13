package sk.sochuliak.barabasi.controller;

import sk.sochuliak.barabasi.graph.InfoPanel;

public class InfoPanelController {

	private InfoPanel infoPanel = null;
	
	private int pointSetterCounter = 0;
	
	public InfoPanelController(InfoPanel leftPanel) {
		this.infoPanel = leftPanel;
	}
	
	public void setPoint(double x, double y) {
		if (this.pointSetterCounter == 0) {
			this.setStartPoint(x, y);
			this.pointSetterCounter++;
		} else {
			this.setEndPoint(x, y);
			this.pointSetterCounter = 0;
		}
	}

	private void setStartPoint(double x, double y) {
		infoPanel.setStartPoint(x, y);
	}
	
	private void setEndPoint(double x, double y) {
		infoPanel.setEndPoint(x, y);
	}

	public void setResult(double k) {
		infoPanel.setK(k);
	}
}
