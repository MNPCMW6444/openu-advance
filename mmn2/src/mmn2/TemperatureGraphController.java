package mmn2;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

import java.util.Arrays;
import java.util.List;

public class TemperatureGraphController {
    @FXML
    private Canvas canvas;

    @FXML
    private Button nextButton;

    private List<TemperatureData> dataList;
    private int currentYearIndex = 0;

    @FXML
    public void initialize() {
        // Initialize temperature data for different years
        dataList = Arrays.asList(
            new TemperatureData(2017, new double[]{5, 6, 10, 15, 20, 25, 30, 29, 24, 18, 10, 5}),
            new TemperatureData(2018, new double[]{4, 7, 12, 16, 21, 26, 31, 30, 23, 17, 9, 6}),
            new TemperatureData(2019, new double[]{6, 8, 13, 17, 22, 27, 32, 31, 26, 19, 11, 7}),
            new TemperatureData(2020, new double[]{3, 5, 11, 14, 18, 24, 28, 27, 22, 16, 8, 4}),
            new TemperatureData(2021, new double[]{2, 6, 9, 13, 19, 23, 29, 28, 21, 14, 7, 3})
        );
        drawGraph();
    }

    @FXML
    void handleNextButtonAction(ActionEvent event) {
        currentYearIndex = (currentYearIndex + 1) % dataList.size();
        drawGraph();
    }

    private void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        TemperatureData data = dataList.get(currentYearIndex);
        double[] temperatures = data.getMonthlyTemperatures();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // For Y axis
        double xOffset = 40;  // Space for Y-axis labels
        int yAxisSteps = 5;  // Number of intervals on the Y-axis
        double yAxisMaxHeight = canvas.getHeight() - 40;  // Leave space for title

        // Draw title
        gc.setFill(Color.BLACK);
        gc.strokeText("Year: " + data.getYear(), xOffset, 20);

        // Find min and max temperatures
        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;
        for (double temp : temperatures) {
            if (temp < minTemp) minTemp = temp;
            if (temp > maxTemp) maxTemp = temp;
        }
        
        for (int i = 0; i <= yAxisSteps; i++) {
            double yValue = i * (maxTemp / yAxisSteps);  // Temperature value for this label
            double yPosition = canvas.getHeight() - 20 - (yValue / maxTemp) * yAxisMaxHeight;

            // Draw label
            gc.setFill(Color.BLACK);
            gc.strokeText(String.format("%.1f", yValue), 10, yPosition);
        }

        // Draw bars
        double barWidth = (canvas.getWidth() - xOffset) / 14;  // Adjust for padding on both sides
        double maxHeight = canvas.getHeight() - 40; // Leave space for title and labels

        for (int i = 0; i < temperatures.length; i++) {
            double temp = temperatures[i];
            double barHeight = (temp / maxTemp) * maxHeight;
            double x = xOffset + 10 + i * barWidth * 1.2;  // Apply xOffset to shift bars
            double y = canvas.getHeight() - barHeight - 20;

            if (temp == maxTemp) {
                gc.setFill(Color.RED);
            } else if (temp == minTemp) {
                gc.setFill(Color.BLUE);
            } else {
                gc.setFill(Color.GRAY);
            }

            gc.fillRect(x, y, barWidth, barHeight);
            gc.setFill(Color.BLACK);
            gc.strokeText(String.valueOf(i + 1), x + barWidth / 4, canvas.getHeight() - 5); // Month label
            gc.strokeText(String.format("%.1f", temp), x, y - 5); // Temperature label
        }
    }
}