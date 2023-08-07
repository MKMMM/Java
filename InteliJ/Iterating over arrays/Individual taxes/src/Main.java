import java.util.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int numberOfCompanies = scanner.nextInt();

        // Read the incomes elements
        int[] incomes = new int[numberOfCompanies];
        for (int i = 0; i < numberOfCompanies; i++) {
            incomes[i] = scanner.nextInt();
        }
        // Read the incomes elements
        float[] taxRates = new float[numberOfCompanies];
        for (int i = 0; i < numberOfCompanies; i++) {
            taxRates[i] = scanner.nextFloat();
        }

        float[] taxesPaid = new float[numberOfCompanies];

        for (int i = 0; i < numberOfCompanies; i++) {
            taxesPaid[i] = incomes[i] * (taxRates[i] / 100 );
        }

        float max = taxesPaid[0];
        int position = 0;

        for (int i = 1; i < taxesPaid.length; i++) {
            if (taxesPaid[i] > max){
                max = taxesPaid[i];
                position = i;
            }
        }
        System.out.print(position + 1);
        scanner.close();
    }
}
