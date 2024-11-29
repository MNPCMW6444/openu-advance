package q2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int GRID_SIZE = 10; // 10x10 grid
    private static final int CELL_SIZE = 50; // Size of each cell
    private boolean[][] grid; // Current grid state
    private boolean initialized = false;

    @Override
    public void start(Stage primaryStage) {
        grid = new boolean[GRID_SIZE][GRID_SIZE]; // Initialize grid

        Canvas canvas = new Canvas(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        Button nextGenButton = new Button("Next Generation");

        nextGenButton.setOnAction(e -> {
            if (!initialized) {
                initializeRandomGrid();
                initialized = true;
            } else {
                grid = computeNextGeneration(grid);
            }
            drawGrid(canvas);
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        layout.setBottom(nextGenButton);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();

        drawGrid(canvas);
    }

    private void initializeRandomGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                grid[row][col] = Math.random() < 0.5; // Random true/false
            }
        }
    }

    private boolean[][] computeNextGeneration(boolean[][] currentGrid) {
        boolean[][] newGrid = new boolean[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int liveNeighbors = countLiveNeighbors(currentGrid, row, col);

                if (currentGrid[row][col]) {
                    newGrid[row][col] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    newGrid[row][col] = liveNeighbors == 3;
                }
            }
        }

        return newGrid;
    }

    private int countLiveNeighbors(boolean[][] grid, int row, int col) {
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself

                int neighborRow = row + i;
                int neighborCol = col + j;

                if (neighborRow >= 0 && neighborRow < GRID_SIZE &&
                        neighborCol >= 0 && neighborCol < GRID_SIZE &&
                        grid[neighborRow][neighborCol]) {
                    liveNeighbors++;
                }
            }
        }

        return liveNeighbors;
    }

    private void drawGrid(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col]) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    gc.setStroke(Color.LIGHTGRAY);
                    gc.strokeRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
