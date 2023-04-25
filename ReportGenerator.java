import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                List<Object[]> tableData = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(" ");
                    int n = Integer.parseInt(tokens[0]);
                    int runs = (tokens.length - 1) / 2;
                    double avgCount = 0;
                    double avgTime = 0;
                    double varCount = 0;
                    double varTime = 0;

                    for (int i = 1; i < tokens.length; i += 2) {
                        int count = Integer.parseInt(tokens[i]);
                        int time = Integer.parseInt(tokens[i + 1]);
                        avgCount += count;
                        avgTime += time;
                    }

                    avgCount /= runs;
                    avgTime /= runs;

                    for (int i = 1; i < tokens.length; i += 2) {
                        int count = Integer.parseInt(tokens[i]);
                        int time = Integer.parseInt(tokens[i + 1]);
                                                double countDiff = count - avgCount;
                        double timeDiff = time - avgTime;
                        varCount += countDiff * countDiff;
                        varTime += timeDiff * timeDiff;
                    }

                    varCount /= runs;
                    varTime /= runs;

                    double cvCount = Math.sqrt(varCount) / avgCount * 100;
                    double cvTime = Math.sqrt(varTime) / avgTime * 100;

                    tableData.add(new Object[]{n, avgCount, cvCount, avgTime, cvTime});
                }

                showResults(tableData);

            } catch (IOException e) {
                System.err.println("Error reading file: " + selectedFile.getName());
                e.printStackTrace();
            }
        }
    }

    private static void showResults(List<Object[]> tableData) {
        String[] columnNames = {
            "Data Set Size",
            "Avg Critical Count",
            "CV Critical Count (%)",
            "Avg Time (ns)",
            "CV Time (%)"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Object[] rowData : tableData) {
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JFrame frame = new JFrame("Benchmark Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


