import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public class  Board {
    //Defines a blank grid 2d array
    public int[][] startingGrid = new int[9][9];


    public Board() {
        //Fill all the elements in the blank array with 0
        for(int i=0; i<9; i++){
            Arrays.fill(startingGrid[i], 0);
        }

    }

    //returns the starting grid for passing as a parameter
    public int[][] getStartingGrid() {
        return startingGrid;
    }

    //visualises the starting grid
    public void displayGrid() {
        for (int[] ints : startingGrid) {
            for (int anInt : ints) {
                System.out.print(anInt + " - ");
            }
            System.out.println();
        }
        }


        //Takes user input to populate the grid
    public void populateGrid() throws IOException {

        //Message instructing user
        System.out.println("Note: Enter blanks spaces as 0");

        //For each row
        for (int i=0; i <startingGrid.length; i++) {

            //int representing current row
            int line = i + 1;
            // User instruction
            System.out.println("Enter 9 numbers to populate line " + line + " with: ");
            //stores user input as a string
            String input = Driver.userInput.next().toLowerCase(Locale.ROOT);
            //validates the input taken
            validateInput(input);
            //turns the input into an array, separating the numbers
            String[] splitInput = input.split("");
            // Outputs the array to console
            System.out.println(Arrays.toString(splitInput));

            //inserts the split input array into each row,
            for (int j = 0; j < startingGrid.length; j++) {
                startingGrid[i][j] = Integer.parseInt(splitInput[j]);

            }
        }
        //outputs the grid to console
        this.displayGrid();
        //writes the grid to the csv file
        this.writeFile();
    }

    private void validateInput(String input) throws IOException {
        //check length
        if(input.length() != 9){
            System.out.println("Input length incorrect, try again...");
            populateGrid();
        }

        //Check if inputs are numbers
        try{Integer.parseInt(input);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Not a number, try again...");
            populateGrid();

        }
    }


    public void writeFile() throws IOException {

        // new string builder object
        StringBuilder builder = new StringBuilder();

        // for debugging, lets the user know when the method is running
        System.out.println("Writing to board.txt...");

        // for each row
        for (int[] ints : startingGrid) {
            // elements in each row
            for (int j = 0; j < startingGrid.length; j++) {

                // appends each element in starting grid to the builder
                builder.append(ints[j]);
                // add a comma after each value
                if (j < startingGrid.length - 1) {
                    builder.append(",");
                }
            }
            //go to new line
            builder.append("\n");
            // writer for writing to file
            BufferedWriter writer = new BufferedWriter(new FileWriter("board.txt"));
            //write the built string to the file
            writer.write(builder.toString());
            // close the writer
            writer.close();
        }
        //tell user writing is dnoe
        System.out.println("Writing complete!");
    }

    //reading file back from the csv
    public void readFile() throws IOException {
        //reader object
        BufferedReader reader = new BufferedReader(new FileReader("board.txt"));
        // hold current line as string
        String line;
        //hold current row value
        int row = 0;

        //while there are lines unread
        while((line = reader.readLine()) != null){
            //split line into an array holding each value
            String[] cols = line.split(",");
            //column
            int col = 0;

            //insert values into starting grid
            for (String c: cols){
                startingGrid[row][col] = Integer.parseInt(c);
                col++;
            }
            //increment row
            row++;
        }
        // close reader
        reader.close();
    }
}
