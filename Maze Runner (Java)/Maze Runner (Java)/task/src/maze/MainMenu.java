package maze;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu {

    private boolean mazeGenerated = false;
    private boolean mazeLoaded = false;
    private Maze myMaze;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String input;
        boolean isMazeMenuVisible = false;

        // Check which menu is to be displayed
        do {
            if (!mazeGenerated && !mazeLoaded) {
                displayInitialMenu();
            } else {
                displayMazeMenu();
                isMazeMenuVisible = true;
            }

            input = scanner.next();
            char firstChar = input.charAt(0);
            choice = Character.getNumericValue(firstChar);

            // Clear the input buffer by reading the rest of the line
            scanner.nextLine();

            // Validate that choice is a single digit
            if (choice < 0 || choice > 7) {
                System.out.println("Incorrect option. Please try again.");
                continue;
            }

            // If maze menu is not visible, disallow options 3 and 4
            if (!isMazeMenuVisible && (choice == 3 || choice == 4 || choice == 5 || choice == 6|| choice == 7)) {
                System.out.println("Incorrect option. Please try again.");
                continue;
            }

            // Choice board
            switch (choice) {
                case 0 -> System.out.println("Exiting the program. Goodbye!");
                case 1 -> generateMaze();
                case 2 -> loadMaze();
                case 3 -> saveMaze();
                case 4 -> displayMaze();
                case 5 -> solveDisplayMazeDFS();
                case 6 -> solveDisplayMazeBFS();
                case 7 -> solveDisplayMazeAStar();
                default -> System.out.println("Incorrect option. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void displayInitialMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        System.out.println("0. Exit");
    }

    private void displayMazeMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        System.out.println("3. Save the maze");
        System.out.println("4. Display the maze");
        System.out.println("5. Find the escape DFS");
        System.out.println("6. Find the escape BFS");
        System.out.println("7. Find the escape A*");
        System.out.println("0. Exit");
    }

    private void generateMaze() {
        // Maze generation logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the size of a maze:");

        while (true) {
            String userInput = scanner.nextLine();
            String[] dimensions = userInput.split(" ");

            // Check the input = if only one dimension is given, create a "square" maze
            if (dimensions.length == 1) {
                try {
                    int dim = Integer.parseInt(dimensions[0]);
                    if (dim <= 0) {
                        System.out.println("Invalid input.");
                        continue; // Prompt the user again for valid input
                    }

                    myMaze = new Maze(dim, dim);
                    myMaze.generateMazeDFS();
                    myMaze.printMaze();
                    mazeGenerated = true;
                    break; // Exit the loop when valid dimensions are provided
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }

                // If two dimensions are given generate maze with both inputs.
            } else if (dimensions.length == 2) {
                try {
                    int rowDim = Integer.parseInt(dimensions[0]);
                    int colDim = Integer.parseInt(dimensions[1]);

                    if (rowDim <= 0 || colDim <= 0) {
                        System.out.println("Invalid input.");
                        continue; // Prompt the user again for valid input
                    }

                    myMaze = new Maze(rowDim, colDim);
                    myMaze.generateMazeDFS();
                    myMaze.printMaze();
                    mazeGenerated = true;
                    break; // Exit the loop when valid dimensions are provided
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else {
                System.out.println("Invalid input..");
            }
        }
    }


    private void loadMaze() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the maze file: ");
        String path = scanner.nextLine();

        int[][] maze = null;

        try {
            String content = readFileAsString(path);
            String[] lines = content.split(System.lineSeparator());
            int rows = lines.length;
            int cols = lines[0].length();
            maze = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maze[i][j] = Character.getNumericValue(lines[i].charAt(j));
                }
            }
            mazeLoaded = true;
        } catch (IOException e) {
            System.out.println("The file " + path + " does not exist.");
        }

        // Check if myMaze exists, if not initialize it first
        if (myMaze == null) {
            myMaze = new Maze(0,0);
        }

        myMaze.setMaze(maze);
        System.out.println("Maze loaded!");
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    private void saveMaze() {
        // Implement maze saving logic here

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the filename to write the array contents: ");
        String fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int[] row : myMaze.getMaze()) {
                for (int value : row) {
                    writer.write(String.valueOf(value));
                }
                writer.write(System.lineSeparator()); // Move to the next line after each row
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
        //    e.printStackTrace();
        }
    }

    private void displayMaze() {
        // Implement maze display logic here
        myMaze.printMaze();
    }

    private void solveDisplayMazeDFS() {

        // Let time the algorithms:
        long startTime = System.nanoTime();

        // Run method
        myMaze.solveMazeDFS();

        // Calculate the runtime
        long endTime = System.nanoTime();
        // Compute the elapsed time in milliseconds
        double elapsedTime = (double) (endTime - startTime) / 1000000;

        // Print the elapsed time
        System.out.println("solveMazeDFS execution time: " + elapsedTime + " ms");

        myMaze.printMaze();
    }

    private void solveDisplayMazeBFS() {

        // Let time the algorithms:
        long startTime = System.nanoTime();

        // Run method
        myMaze.solveMazeBFS();

        // Calculate the runtime
        long endTime = System.nanoTime();
        // Compute the elapsed time in milliseconds
        double elapsedTime = (double) (endTime - startTime) / 1000000;

        // Print the elapsed time
        System.out.println("solveMazeBFS execution time: " + elapsedTime + " ms");

        myMaze.printMaze();
    }

    private void solveDisplayMazeAStar() {

        // Let time the algorithms:
        long startTime = System.nanoTime();

        // Run method
        myMaze.solveMazeAStar();

        // Calculate the runtime
        long endTime = System.nanoTime();
        // Compute the elapsed time in milliseconds
        double elapsedTime = (double) (endTime - startTime) / 1000000;

        // Print the elapsed time
        System.out.println("A* execution time: " + elapsedTime + " ms");

        myMaze.printMaze();
    }

}
