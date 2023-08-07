package maze;
import java.util.*;

public class Maze {

    private int[][] maze;

    private static final List<Position> Directions = Arrays.asList(
            new Position(-1, 0),
            new Position(1, 0),
            new Position(0, -1),
            new Position(0, 1)
    );

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Maze(int rowDim, int colDim) {
        this.maze = new int[rowDim][colDim];
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void initializeMaze() {

        for (int[] ints : maze) {
            Arrays.fill(ints, 1);
        }
    }

    public void generateMazeDFS() {

        // Depth-First Search (DFS)
        // Call a method to Initialize the maze with all walls (1s)
        initializeMaze();
        Random random = new Random();

        // Initialize data
        int startRow = 1 + random.nextInt(maze.length - 2); // Random row for entrance
        int startCol = 1; // Start at second column, first column is a wall
        int endRow = 1 + random.nextInt(maze.length - 2); // Random row for exit
//        int endCol = maze[0].length - 2; // End at second last column, last column is a wall

        //Mark the start cell as 0 (valid cell)
        maze[startRow][startCol] = 0;

        // Create an empty stack
        Stack<int[]> stack = new Stack<>();

        // Push the start cell onto the stack
        stack.push(new int[]{startRow, startCol});

        // While the stack is not empty
        while(!stack.isEmpty()) {

            // Pop the top cell from the stack
            int[] currentCell = stack.pop();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            // Mark the current cell as 0 (valid cell)
            maze[currentRow][currentCol] = 0;

            // Randomly shuffle the directions (up, down, left, right)
            List<String> randDirections = randDirections();

            // Iterate for each direction in the shuffled directions
            for (String dir : randDirections) {
                int nextRow = currentRow;
                int nextCol = currentCol;

                // Calculate the new cell coordinates based on the current cell and the direction
                switch (dir) {
                    case "up" -> nextRow -= 2;
                    case "down" -> nextRow += 2;
                    case "left" -> nextCol -= 2;
                    case "right" -> nextCol += 2;
                }

                // Check if the new cell is a valid neighboring cell and is a wall
                if (nextRow > 0 && nextRow < maze.length - 1 && nextCol > 0 && nextCol < maze[0].length - 1) {
                    if (maze[nextRow][nextCol] == 1) {
                        if (dir.equals("up") || dir.equals("down")) {
                            maze[nextRow][nextCol] = 0;
                            maze[nextRow + (dir.equals("up") ? 1 : -1)][nextCol] = 0;
                        } else {
                            maze[nextRow][nextCol] = 0;
                            maze[nextRow][nextCol + (dir.equals("left") ? 1 : -1)] = 0;
                        }
                        stack.push(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        // Reopen the entrance and create the exit
        maze[startRow][0] = 0; // Entrance
        maze[endRow][maze[0].length - 1] = 0; // Exit
        maze[endRow][maze[0].length - 2] = 0; // Cell adjacent to the exit
    }


//    public void generateMazePrim() {
//
//        Random random = new Random();
//        // Call a method to Initialize the maze with all walls (1s)
//        initializeMaze();
//
//        // Initialize data
//        int startRow = 1 + random.nextInt(maze.length - 2); // Random row for entrance
//        int startCol = 1; // Start at second column, first column is a wall
//        int endRow = 1 + random.nextInt(maze.length - 2); // Random row for exit
////        int endCol = maze[0].length - 2; // End at second last column, last column is a wall
//
//        // Mark the start, end cells and their adjacent cells as 0 (valid cells)
//        maze[startRow][0] = 0; // Entrance
//        maze[startRow][1] = 0; // Cell adjacent to the entrance
//
//
//        int width = maze[0].length;
//        int height = maze.length;
//
//        // List of walls to consider
//        List<int[]> walls = new ArrayList<>();
//
//        // Add the neighbors of the starting cell to the wall list
//        addNeighbors(startCol, startRow, width, height, walls);
//
//        while (!walls.isEmpty()) {
//
//            // Get a random wall from the list
//            int randWallIndex = random.nextInt(walls.size());
//            int[] wall = walls.get(randWallIndex);
//
//            // Get the neighbor cells on both sides of the wall
//            int cellX = wall[0];
//            int cellY = wall[1];
//            int neighborX = wall[2];
//            int neighborY = wall[3];
//
//            // If exactly one of the cells is a wall, mark the other cell as part of the maze
//            if (maze[cellY][cellX] == 1 && maze[neighborY][neighborX] == 0) {
//                maze[cellY][cellX] = 0;
//                maze[cellY + (neighborY - cellY) / 2][cellX + (neighborX - cellX) / 2] = 0;
//
//                // Add the neighbors of the newly marked cell to the wall list
//                addNeighbors(cellX, cellY, width, height, walls);
//            } else if (maze[cellY][cellX] == 0 && maze[neighborY][neighborX] == 1) {
//                maze[neighborY][neighborX] = 0;
//                maze[cellY + (neighborY - cellY) / 2][cellX + (neighborX - cellX) / 2] = 0;
//
//                // Add the neighbors of the newly marked cell to the wall list
//                addNeighbors(neighborX, neighborY, width, height, walls);
//            }
//
//            // Remove the wall from the list
//            walls.remove(randWallIndex);
//        }
//
//
//        // Surround the maze with walls (1s) - including the leftmost and rightmost columns
//        for (int i = 0; i < height; i++) {
//            maze[i][0] = 1;                 // Leftmost column
//            maze[i][width - 1] = 1;         // Rightmost column
//        }
//        for (int i = 0; i < width; i++) {
//            maze[0][i] = 1;                 // Top row
//            maze[height - 1][i] = 1;        // Bottom row
//        }
//
//        // Reopen the entrance and create the exit
//        maze[startRow][0] = 0; // Entrance
//        maze[endRow][maze[0].length - 1] = 0; // Exit
//        maze[endRow][maze[0].length - 2] = 0; // Cell adjacent to the exit
//
//
//
//    }


//    public void addNeighbors(int x, int y, int width, int height, List<int[]> walls) {
//
//        // Takes coordinates of the cell x, y, width, height, and walls as input
//        //  to add the walls connecting the current cell to its neighboring cells to the list of walls.
//        //  These walls will be considered during the maze generation process to determine which walls to remove.
//
//
//        // Define array neighbors for coordinates of neighbors 2 places away
//        int[][] neighbors = {{x - 2, y}, {x + 2, y}, {x, y - 2}, {x, y + 2}};
//
//        // Iterate over the array
//        for (int[] neighbor : neighbors) {
//
//            // Define X Y coordinates for each neighbor from the array
//            int neighborX = neighbor[0];
//            int neighborY = neighbor[1];
//
//            // Check if neighbor coordinates are within bounds, add wall between current cell
//            // and the neighbor to the walls list
//            if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
//                if (maze[neighborY][neighborX] == 1) {
//                    walls.add(new int[]{x, y, neighborX, neighborY});
//                }
//            }
//        }
//    }

//    private boolean isWall(int row, int col) {
//        return maze[row][col] == 1;
//    }
//
//
//    private boolean isEmpty(int row, int col) {
//        return maze[row][col] == 0;
//    }
//
//    private int randGenerator (int lowerLimit, int upperLimit ) {
//
//        // Generate random numbers between the two ranges
//        Random random = new Random();
//        return random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
//    }

    public void solveMazeDFS() {

        // Find start position
        Position startPos = findStart(maze);
        Position endPos = findFinish(maze);

        // Create an empty stack and push the start position
        Stack<Position> stack = new Stack<>();
        stack.push(startPos);

        // Create a "came from" map and initialize it with the start position
        Map<Position, Position> cameFrom = new HashMap<>();

        // Initialize came from with the start position
        cameFrom.put(startPos, null);

        while(!stack.isEmpty()) {

            // Initialize our position variable
            Position currentPos = stack.pop();

            if (currentPos.equals(endPos)) {

                // Backtrack to find the path and mark it in the maze array
                for (Position tempPos = endPos; tempPos != null; tempPos = cameFrom.get(tempPos)) {
                    maze[tempPos.x][tempPos.y] = 9;
                }
                return;
            }


            for (Position direction : Directions) {
                // Initialize the 'neighbor' of the current position
                Position neighbor = new Position(currentPos.x + direction.x, currentPos.y + direction.y);

                if (isInBounds(maze, neighbor) && maze[neighbor.x][neighbor.y] == 0 && !cameFrom.containsKey(neighbor)) {
                    stack.push(neighbor);
                    cameFrom.put(neighbor, currentPos);
                }
            }
        }
    }

    public void solveMazeBFS() {

        // Find Start and end position
        Position startPos = findStart(maze);
        Position endPos = findFinish(maze);

        // Create a Linked List queue and add startPos to it
        Queue<Position> queue = new LinkedList<>();
        queue.add(startPos);

        // Create Map of cameFrom coordinates and initialize add startPos to it
        Map<Position, Position> cameFrom = new HashMap<>();
        cameFrom.put(startPos, null);

        while (!queue.isEmpty()) {
            Position currentPos = queue.remove();

            if (currentPos.equals(endPos)) {
                for (Position tempPos = endPos; tempPos != null; tempPos = cameFrom.get(tempPos)) {
                    maze[tempPos.x][tempPos.y] = 9;
                }
                return;
            }

            for (Position direction : Directions) {
                Position neighbor = new Position(currentPos.x + direction.x, currentPos.y + direction.y);

                if (isInBounds(maze, neighbor) && maze[neighbor.x][neighbor.y] == 0 && !cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, currentPos);
                }
            }
        }
    }

    public void solveMazeAStar() {
        Position startPos = findStart(maze);
        Position endPos = findFinish(maze);

        PriorityQueue<Position> queue = new PriorityQueue<>(Comparator.comparingInt(pos -> heuristic(pos, endPos)));
        queue.add(startPos);

        Map<Position, Position> cameFrom = new HashMap<>();
        cameFrom.put(startPos, null);

        Map<Position, Integer> costSoFar = new HashMap<>();
        costSoFar.put(startPos, 0);

        while (!queue.isEmpty()) {
            Position currentPos = queue.remove();

            if (currentPos.equals(endPos)) {
                for (Position tempPos = endPos; tempPos != null; tempPos = cameFrom.get(tempPos)) {
                    maze[tempPos.x][tempPos.y] = 9;
                }
                return;
            }

            for (Position direction : Directions) {
                Position neighbor = new Position(currentPos.x + direction.x, currentPos.y + direction.y);
                int newCost = costSoFar.get(currentPos) + 1;  // assuming all steps cost 1

                if (isInBounds(maze, neighbor) && maze[neighbor.x][neighbor.y] == 0 &&
                        (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor))) {
                    costSoFar.put(neighbor, newCost);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, currentPos);
                }
            }
        }
    }

    private int heuristic(Position pos, Position goal) {
        // using Manhattan distance as heuristic
        return Math.abs(pos.x - goal.x) + Math.abs(pos.y - goal.y);
    }


    private Position findStart(int[][] maze) {

        int row = 0;
        int col = 0;

        for(int i = 0 ; i < maze.length; i++) {
            if (maze[i][col] == 0) {
                row = i;
            }
        }
        return new Position(row, col);
    }

    private Position findFinish(int[][] maze) {

        int row = 0;
        int col = maze[0].length - 1;

        for(int i = 0 ; i < maze[0].length; i++){
            if (maze[i][col] == 0) {
                row = i;
            }
        }
        return new Position(row, col);
    }

    private static boolean isInBounds(int[][] maze, Position pos) {
        return pos.x >= 0 && pos.y >= 0 && pos.x < maze.length && pos.y < maze[0].length;
    }

    private List<String> randDirections() {

        // Randomly shuffle the directions (up, down, left, right)
        List<String> directions = new ArrayList<>();
        directions.add("up");
        directions.add("down");
        directions.add("left");
        directions.add("right");
        Collections.shuffle(directions);
        return directions;
    }

    public void printMaze() {

        for (int[] ints : maze) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    // Two blocks for walls
                    System.out.print(ANSI_CYAN  + "\u2588\u2588" + ANSI_RESET);
                } else if (anInt == 0) {
                    // Two spaces for empty space
                    System.out.print("  ");
                } else if (anInt == 9 ) {
                    // Two slashes for the path
                    System.out.print(ANSI_RED  + "//" + ANSI_RESET);
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
