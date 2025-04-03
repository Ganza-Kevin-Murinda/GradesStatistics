import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Obtain user inputs
        Scanner scanner = new Scanner(System.in);

        // Read input grades
        System.out.print("Enter the grades separated by spaces: ");
        String inputLine = scanner.nextLine();
        // Divide the string into an array of substrings after removing whitespace(empty space)
        String[] inputValues = inputLine.trim().split("\\s+");

        // Convert string input to integer array
        int[] scores = new int[inputValues.length];
        for (int i = 0; i < inputValues.length; i++) {
            scores[i] = Integer.parseInt(inputValues[i]);
        }

        // Calculate statistics
        int maxGrade = findMaximum(scores);
        int minGrade = findMinimum(scores);
        double avgGrade = calculateAverage(scores);

        // Calculate frequency
        int[] stats = calculateStats(scores);

        // Display results
        displayResults(maxGrade, minGrade, avgGrade, stats);

        scanner.close();
    }

    // Find the maximum grade
    public static int findMaximum(int[] scores) {
        int max = scores[0];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    // Find the minimum grade
    public static int findMinimum(int[] scores) {
        int min = scores[0];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    // Calculate the average grade
    public static double calculateAverage(int[] scores) {
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        // Explicit casting
        return (double) sum / scores.length;
    }

    // Creates a frequency distribution of the grades by counting how many grades fall into each of the 5 ranges (0-20, 21-40, 41-60, 61-80, 81-100)
    public static int[] calculateStats(int[] scores) {
        int[] stats = new int[5]; // Initialize all buckets to 0

        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            if (score >= 0 && score <= 20) {
                stats[0]++;
            } else if (score <= 40) {
                stats[1]++;
            } else if (score <= 60) {
                stats[2]++;
            } else if (score <= 80) {
                stats[3]++;
            } else { // score <= 100
                stats[4]++;
            }
        }

        return stats;
    }

    // Display results and graph
    public static void displayResults(int maxGrade, int minGrade, double avgGrade, int[] stats) {
        System.out.println("Values:");
        System.out.println("The maximum grade is " + maxGrade);
        System.out.println("The minimum grade is " + minGrade);
        System.out.printf("The average grade is %.6f%n", avgGrade);

        System.out.println("Graph:");

        // Find the maximum frequency for scaling (y-axis)
        int maxFreq = 0;
        for (int i = 0; i < stats.length; i++) {
            if (stats[i] > maxFreq) {
                maxFreq = stats[i];
            }
        }

        // Print the bars from top to bottom
        for (int height = maxFreq; height > 0; height--) {
            System.out.printf("%d >", height);
            for (int i = 0; i < stats.length; i++) {
                if (stats[i] >= height) {
                    System.out.print("  #######   ");
                } else {
                    System.out.print(" ".repeat(12));
                }
            }
            System.out.println();
        }

        // Print the x-axis
        System.out.println("  +-----------+-----------+-----------+-----------+-----------+-");
        System.out.println("  I    0-20   I   21-40   I   41-60   I   61-80   I   81-100  I");
    }
}