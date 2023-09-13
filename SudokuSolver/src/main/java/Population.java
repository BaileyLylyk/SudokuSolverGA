import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//Class Declaration
public class Population {

    public int size;


    //    Class constructor
    public Population(int size){
        this.size = size;
    }

//    Map for storing the population
    public Map<Integer, Chromosome> populationMap = new HashMap<>();
//Defining population size
//    public int size = 15;

//    Method to create a population
    public void createPopulation(){

//        Creates new random chromosomes for the length of size
        for(int i=0; i<size;i++){
//            Adds a new chromosome to a map with the key i and value of a blank chromosome
            populationMap.put(i,new Chromosome());
//            Prints i + memory location of chromosome
            System.out.println(i+": "+populationMap.get(i));
//            Generates the chromosome using the existing board
            populationMap.get(i).generateChromosome(Driver.board);
//            Randomises the values of the chromosome
            populationMap.get(i).randomiseChromosome();

            System.out.println("Static Values: " +populationMap.get(i).storeStaticValues.toString());
        }
    }

//    Method to evaluate fitness of each chromosome
    public void evaluateFitness(Population population){


//For each member of the population
        for (int i=0; i < population.size;i++){
            Chromosome currChromosome = populationMap.get(i);
            int column = 0;
            int subGridStart = 0;
            int subGridCounter = 0;
            int currentNode = column;
            int [] vals = new int[9];
            currChromosome.setScore(0);


            //Evaluate Columns
            //For Each column
            for (int j=0; j<9;j++){
                //Looping through the array 9 times taking the value of each element in a column
                for( int k =0; k < 9; k++){
//                    System.out.println(currentNode);
                    // Building an array of the column values
                    vals[k] = currChromosome.genes[currentNode];
                    //Incrementing the current node by 9 to get the next value in a given column
                    currentNode+=9;

                }
//                For debugging, prints out the column and its values
//                System.out.println("Column " + j + " Values: " + Arrays.toString(vals));
                //Calls a method to create a list of repeating values and score the list based on how many values are in it
                scoreFitness(listRepeatedVals(vals), currChromosome);
                //Increments the column
                column++;
                //Sets the next starting node as the next column
                currentNode = column;
            }


            //Evaluate Sub-grids
            //For each sub-grid
            for (int j =0; j<9; j++){
                //int to count the number of times the k loop runs.
                int loopCounter = 0;
                //starting point for each subgrid
                currentNode = subGridStart;


                // For each cell
                for (int k =0; k<9;k++){
                    //if the loop has run more than twice, move to the next row
                    if(loopCounter > 2){
                        // +6 is the value to get to the next row from the most recent current node position
                        currentNode+=6;
                        //resets the counter
                        loopCounter = 0;

                    }

                    //updates the current vals entry to be the current node
                    vals[k] = currChromosome.genes[currentNode];
                    //current node increments by 1
                    currentNode++;
                    //loop counter incremented
                    loopCounter++;

                }

//                System.out.println("Subgrid " + j + " Values: " + Arrays.toString(vals));
                //Calls a method to create a list of repeating values and score the list based on how many values are in it
                scoreFitness(listRepeatedVals(vals), currChromosome);


                // Increments to the next "row" of 3 sub-grids every 3 sub-grids covered.
                if(subGridCounter == 2){
                    //increase of 21 always goes to next lot of sub-grids from last position when value of sub-grid counter is checked
                    subGridStart+=21;
                    //resets subGridCounter
                    subGridCounter=0;

                    //Move to next sub-grid, incrementing the counter by 1
                }else{subGridStart +=3; subGridCounter++;}

            }


            //Print fitness of each chromosome
            System.out.println("Chromosome " + i + " Fitness Score: " + currChromosome.getScore());
        }

    }

    private void scoreFitness(LinkedList<Integer> repeatedVals, Chromosome currChromosome) {
//        Sets the chromosomes score to its current value + (9 - the number of repeated values)
        currChromosome.setScore(currChromosome.getScore() + (9 - repeatedVals.size()));

    }

    //    Separated into its own method so the same method can be called for sub-grids
    public LinkedList<Integer> listRepeatedVals(int[] vals) {
        LinkedList <Integer> repeatedValues = new LinkedList<>();
//        Score the fitness based on repeating numbers
//        For each value
        for(int i = 0; i < vals.length; i++) {
            // Compare each value to each other value
            for(int j = i + 1; j < vals.length; j++) {
                // if any value is the same as another
                if(vals[i] == vals[j]){
//                    Adds any repeated value found to a linked list
                    repeatedValues.add(vals[j]);
                }
            }
        }
        //Returns the linked list
        return repeatedValues;
    }




}
