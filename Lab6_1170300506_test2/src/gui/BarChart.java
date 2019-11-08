package gui;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Generate bar chart.
 * 
 * @author chen
 *
 */
public class BarChart {

    /**
     * 创建BarChart.
     */
    public void createBarChart(String filePath, DefaultCategoryDataset dataset) {
        try {
            // 设置Chart主题
            setChartTheme();
            JFreeChart chart = ChartFactory.createBarChart3D("不同最大速度下吞吐率和公平性差异", "不同最大速度", "", dataset,
                    PlotOrientation.VERTICAL, true, true, false);

            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 800, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置ChartFactory主题，可解决乱码问题.
     */
    private void setChartTheme() {
        // 创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
    }

    /**
     * 获得复杂（组合）BarChart的数据集.
     * 
     * @return BarChart数据集
     */
    public DefaultCategoryDataset getBarDataSet1(int i) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (i == 1) {
            dataset.addValue(1.754, "吞吐率", "MV=5");
            dataset.addValue(0.754, "公平性", "MV=5");
            dataset.addValue(1.667, "吞吐率", "MV=8");
            dataset.addValue(0.744, "公平性", "MV=8");
            dataset.addValue(1.754, "吞吐率", "MV=10");
            dataset.addValue(0.815, "公平性", "MV=10");
            //dataset.addValue(0.588, "吞吐率", "t=4");
            //dataset.addValue(0.853, "公平性", "t=4");
            //dataset.addValue(0.472, "吞吐率", "t=5");
            //dataset.addValue(0.928, "公平性", "t=5");
        } else {
            dataset.addValue(594, "Strategy1", "file1-O");
            dataset.addValue(1058, "Strategy2", "file1-O");
            dataset.addValue(1637, "Strategy3", "file1-O");
            dataset.addValue(937, "Strategy1", "file2-O");
            dataset.addValue(1689, "Strategy2", "file2-O");
            dataset.addValue(1837, "Strategy3", "file2-O");
            dataset.addValue(959, "Strategy1", "file3-O");
            dataset.addValue(884, "Strategy2", "file3-O");
            dataset.addValue(1772, "Strategy3", "file3-O");
            dataset.addValue(723, "Strategy1", "file4-O");
            dataset.addValue(1646, "Strategy2", "file4-O");
            dataset.addValue(1821, "Strategy3", "file4-O");
        }

        return dataset;
    }


    /**
     * factory.
     */
    public static void factory(String filePath, DefaultCategoryDataset dataset) {
        BarChart piechart = new BarChart();
        piechart.createBarChart(filePath, dataset);
        System.out.println("PieChart file path : " + filePath);
        try {
            Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main.
     */
    public static void main(String[] args) {
        BarChart.factory("src/gui/CompareDifferentMV.jpg", new BarChart().getBarDataSet1(1));
        // BarChart.factory("src/io/output.jpg", new BarChart().getBarDataSet1(2));
    }


}
