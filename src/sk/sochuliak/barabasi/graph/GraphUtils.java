package sk.sochuliak.barabasi.graph;

import org.jfree.chart.entity.XYItemEntity;
import org.jfree.data.xy.XYDataset;

public class GraphUtils {

	public static double[] getCoordFromXYItemEntity(final XYItemEntity xyItemEntity) {
		XYDataset dataset = xyItemEntity.getDataset();
		double[] result = new double[2];
		int seriesIndex = xyItemEntity.getSeriesIndex();
		int itemIndex = xyItemEntity.getItem();
		result[0] = dataset.getXValue(seriesIndex, itemIndex);
		result[1] = dataset.getYValue(seriesIndex, itemIndex);
		return result;
	}
}
