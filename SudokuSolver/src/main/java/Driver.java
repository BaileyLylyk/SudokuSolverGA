import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Driver {
    //Scanner to get user inputs
    public static Scanner userInput = new Scanner(System.in);
    static Board board = new Board();
    static Population population = new Population(15);
    public static Boolean solutionFound = false;

    public static void main(String[] args) throws IOException {

        System.out.println("Do you want to create a new Board (y/n): ");
         String input = userInput.next();

         if (input.toLowerCase(Locale.ROOT).equals("y")){
             System.out.println(Arrays.deepToString(board.getStartingGrid()));
             System.out.println("Grid Created...");
             board.displayGrid();

             System.out.println("Do you want to populate the grid? (y/n) ");
             input = userInput.next();
             if (input.toLowerCase(Locale.ROOT).equals("y")){
                 board.populateGrid();
             }

             else{
                 main(args);

             }
         }

        if (input.toLowerCase(Locale.ROOT).equals("n")){
            board.readFile();
            board.displayGrid();
            population.createPopulation();
//            population.evaluateFitness(population);
//            Algorithm.crossover(population, Algorithm.tournament(population,3));


            while(!solutionFound){
                for (int i=0; i<population.size; i++){
                    int currentScore = population.populationMap.get(i).getScore();

                    if (currentScore == 162){
                       Chromosome solution =  population.populationMap.get(i);
                       System.out.println("Solution: " + Arrays.toString(solution.genes));
                       System.exit(0);
                    }else{
                        population.evaluateFitness(population);
                        Algorithm.crossover(population, Algorithm.tournament(population,3));
                        Algorithm.mutation(population);

                    }
                }
            }
        }

        else{
            main(args);
        }
    }
}
