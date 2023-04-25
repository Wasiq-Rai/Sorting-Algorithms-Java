import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class DisplayChart {
    public static void createChart(List<Object[]> tableData) {
        // Collect data for execution time and critical operations
        double[] execTime = new double[tableData.size()];
        double[] critOps = new double[tableData.size()];
        for (int i = 0; i < tableData.size(); i++) {
            execTime[i] = (double) tableData.get(i)[3];
            critOps[i] = (double) tableData.get(i)[1];
        }

        // Create datasets for execution time and critical operations
        DefaultCategoryDataset execTimeDataset = new DefaultCategoryDataset();
        for (int i = 0; i < execTime.length; i++) {
            execTimeDataset.addValue(execTime[i], "Execution Time", "Benchmark " + (i+1));
        }

        DefaultCategoryDataset critOpsDataset = new DefaultCategoryDataset();
        for (int i = 0; i < critOps.length; i++) {
            critOpsDataset.addValue(critOps[i], "Critical Operations", "Benchmark " + (i+1));
        }

        // Create chart for execution time and critical operations
        JFreeChart execTimeChart = ChartFactory.createLineChart(
                "Execution Time",
                "Benchmark",
                "Time (ns)",
                execTimeDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        JFreeChart critOpsChart = ChartFactory.createLineChart(
                "Critical Operations",
                "Benchmark",
                "Count",
                critOpsDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Display charts in JFrame windows
        JFrame execTimeFrame = new JFrame("Execution Time");
        execTimeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        execTimeFrame.getContentPane().add(new ChartPanel(execTimeChart));
        execTimeFrame.pack();
        execTimeFrame.setLocationRelativeTo(null);
        execTimeFrame.setVisible(true);

        JFrame critOpsFrame = new JFrame("Critical Operations");
        critOpsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        critOpsFrame.getContentPane().add(new ChartPanel(critOpsChart));
        critOpsFrame.pack();
        critOpsFrame.setLocationRelativeTo(null);
        critOpsFrame.setVisible(true);
    }
}
