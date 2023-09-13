import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chromosome {
    public int[] genes;
    public ArrayList<Integer> storeStaticValues;
    public ArrayList<Integer> storeNonStaticValues;
    private ArrayList<Integer> StaticValues;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int score = 0;

    public Chromosome() {
        genes = new int[81];
    }

    public void generateChromosome(Board board){


//        Flatten grid to 1d array
        genes = Arrays.stream(board.getStartingGrid())
                .flatMapToInt(Arrays::stream)
                .toArray();

//        Storing static and non-static values for later use in evaluation
        storeStaticValues = getStaticValues();
        storeNonStaticValues = getNonStaticValues();

//        DEBUGGING STUFF
////        Base chromosome
//        System.out.println(Arrays.toString(genes));
////        this.getStaticValues();
//        System.out.println("Non-Static Values: " + this.getNonStaticValues().toString());
//        System.out.println("Static Values: " + this.getStaticValues().toString());

    }

    public ArrayList<Integer> getNonStaticValues(){
        ArrayList<Integer> nonStaticValues = new ArrayList<>();

        for (int i=0; i <genes.length; i++) {
            if (genes[i] == 0) {
                nonStaticValues.add(i);
            }
        }
        return(nonStaticValues);

    }

    public ArrayList<Integer> getStaticValues(){
         StaticValues = new ArrayList<>();

        for (int i=0; i <genes.length; i++) {
            if (genes[i] != 0) {
                StaticValues.add(i);
            }
        }
        return(StaticValues);

    }


    public void randomiseChromosome(){

        List<Integer> possibleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> existingNumbers = new ArrayList<>();
        int startPoint = 0;
        int endPoint = 9;
        int counter = 0;

//Creating the list of
//Loop for the rows
        for (int i = 0;i<9; i++){
//            System.out.println(i);
//            Loop for columns
            for (int l = 0;l<9; l++){

//                System.out.println("Counter: " + counter);
//                System.out.println( i + " " + l);
//              create list of values 1-9 that do not appear in the row
                if(counter < 81 && this.genes[counter] !=0){
                    existingNumbers.add(genes[counter]);

                }
                counter++;

            }


//            System.out.println("Existing values: " + existingNumbers);

//          Removes all values from possibleNumbers that already exist in existingNumbers.
            possibleNumbers.removeAll(existingNumbers);
//            System.out.println("Possible Numbers: " + possibleNumbers);

//            Shuffling the potential values for each row
            Collections.shuffle(possibleNumbers);
//            System.out.println("Shuffled Numbers: " + possibleNumbers);

            for (int n= startPoint; n < endPoint; n++){

//                System.out.println("N: " +n + " "+ "Genes N: "+ genes[n] );

                if (n < 81 && genes[n] == 0) {
                    int setValue = possibleNumbers.get(0);
                    genes[n] = setValue;

                    possibleNumbers.remove(0);
                }


            }

            startPoint+=9;
            endPoint+=9;

//            TODO For loop start point to endpoint -> make value of shuffled list replace 0s

//            Resetting possible numbers to default after each row
            possibleNumbers.clear();
            possibleNumbers.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));


            existingNumbers.clear();
        }

        System.out.println("New Chromosome: "+ Arrays.toString(genes));
    }

}

