// Required imports for the program
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.nio.file.FileSystems;


public class CountEmptyFolders {

    // A global variable to hold the count of empty directories.
    private static int count = 0;
    private static Path maxFilesDir = null;
    private static int maxFilesCount = 0;
    private static Path deepestPath = null;
    private static int deepestDepth = 0;

    public static void main(String[] args) {
        // Define the path you want to start searching from.
        Path startPath = Paths.get("C:\\dev\\Data");

        try {
            // walkFileTree will traverse the entire directory tree.
            Files.walkFileTree(startPath,
                    EnumSet.noneOf(FileVisitOption.class), // No special options needed for this search.
                    Integer.MAX_VALUE, // Maximum depth to search. MAX_VALUE means no limit.
                    new FileVisitor<Path>() {

                        // Invoked before a directory's entries are visited.
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                            // Compute depth by counting the name elements of the path
                            int depth = dir.getNameCount();
                            if (depth > deepestDepth) {
                                deepestDepth = depth;
                                deepestPath = dir;
                            }
                            return FileVisitResult.CONTINUE; // Continue the traversal.
                        }

                        // Invoked for a file in a directory.

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            // Compute depth by counting the name elements of the path
                            int depth = file.getNameCount();
                            if (depth > deepestDepth) {
                                deepestDepth = depth;
                                deepestPath = file;
                            }
                            return FileVisitResult.CONTINUE; // Continue the traversal.
                        }

                        // Invoked when there's an error accessing the file.
                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) {
                            return FileVisitResult.CONTINUE; // Continue the traversal, ignoring the error.
                        }

                        // Invoked after all the directory's entries have been visited.
                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                            if (isDirectoryEmpty(dir)) {
                                count++;
                                System.out.println("Empty directory: " + dir.toString());
                            }

                            int filesInDir = countFilesInDirectory(dir);
                            if (filesInDir > maxFilesCount) {
                                maxFilesCount = filesInDir;
                                maxFilesDir = dir;
                            }

                            return FileVisitResult.CONTINUE;
                        }

                    });

            // Print the total number of empty directories found.
            System.out.println("Total number of empty directories: " + count);

            if (maxFilesDir != null) {
                System.out.println("Directory with the maximum number of files: " + maxFilesDir.toString() + " with " + maxFilesCount + " files.");
            }

            if (deepestPath != null) {
                System.out.println("Deepest file or directory: " + deepestPath.toString() + " at depth " + deepestDepth);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Print any exceptions thrown during traversal.
        }
    }

    // Method to count the number of files in a directory (not including sub-directories).
    private static int countFilesInDirectory(Path dir) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            int count = 0;
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    count++;
                }
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Method to check if a directory is empty.
    private static boolean isDirectoryEmpty(Path dir) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            // If the directory has no entries, it's empty.
            return !directoryStream.iterator().hasNext();
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception if there's an error.
            return false; // Return false if there's an error checking the directory.
        }
    }
}
