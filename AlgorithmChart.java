import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class AlgorithmChart {
    public static void main(String[] args) {
        // Collect data for Algorithm 1
        int[] critOps1 = {10, 20, 30, 40};
        long[] execTime1 = {100, 200, 300, 400};

        // Collect data for Algorithm 2
        int[] critOps2 = {5, 10, 15, 20};
        long[] execTime2 = {50, 100, 150, 200};

        // Create datasets for each algorithm
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        for (int i = 0; i < critOps1.length; i++) {
            dataset1.addValue(critOps1[i], "Algorithm 1", "Benchmark " + (i+1));
        }
        for (int i = 0; i < execTime1.length; i++) {
            dataset1.addValue(execTime1[i], "Algorithm 1 (time)", "Benchmark " + (i+1));
        }

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (int i = 0; i < critOps2.length; i++) {
            dataset2.addValue(critOps2[i], "Algorithm 2", "Benchmark " + (i+1));
        }
        for (int i = 0; i < execTime2.length; i++) {
            dataset2.addValue(execTime2[i], "Algorithm 2 (time)", "Benchmark " + (i+1));
        }

        // Create chart and add datasets
        JFreeChart chart = ChartFactory.createLineChart(
                "Algorithm Comparison",
                "Benchmark",
                "Critical Operation Count / Execution Time",
                dataset1,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.getCategoryPlot().setDataset(1, dataset2);

        // Display chart in JFrame window
        JFrame frame = new JFrame("Algorithm Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
