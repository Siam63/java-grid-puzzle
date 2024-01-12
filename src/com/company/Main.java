package com.company;
import java.util.Scanner;

public class Main {
    public static boolean isCorrectGrid(String[] grid){
        int n = grid.length;

        for(int i = 0; i < n; i++) {
            int rowCountBlack = 0, rowCountWhite = 0, colCountBlack = 0, colCountWhite = 0;

            // check condition 1 & 2
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == 'B') {
                    rowCountBlack++;
                } else {
                    rowCountWhite++;
                }

                if (grid[j].charAt(i) == 'B') {
                    colCountBlack++;
                } else {
                    colCountWhite++;
                }
            }

            // check if conditions 1 and 2 are satisfied (same num of black and white squares)
            if(rowCountBlack != rowCountWhite || colCountBlack != colCountWhite) return false;

            // check if rows and cols are even since that is one of our constraints 2 <= n <= 24
            if(rowCountBlack != n / 2 || colCountBlack != n / 2) return false;
        }

        // check condition 3
        for(int i = 0; i < n; i++){
            if(has3ConsecColors(grid[i]) || has3ConsecColors(getColumn(grid, i))) return false;
        }

        return true;
    }

    public static String getColumn(String[] grid, int colIndex){
        StringBuilder col = new StringBuilder();
        for(String row : grid){
            col.append(row.charAt(colIndex));
        }

        return col.toString();
    }

    public static boolean has3ConsecColors(String row){
        int counter = 1;

        for(int i = 1; i < row.length(); i++){
            if(row.charAt(i) == row.charAt(i - 1)){
                counter++;

                if(counter >= 3){
                    return true;
                }
            }else{
                // reset counter
                counter = 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // driver code to test the input and check the grid
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] grid = new String[n];

        for(int i = 0; i < n; i++){
            grid[i] = sc.nextLine();
        }

        int res = isCorrectGrid(grid) ? 1 : 0;
        System.out.println(res);
    }
}
