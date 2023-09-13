import java.util.*;

public class Algorithm {

    // Random number in defined range
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Population tournament(Population population, int noOfTournaments) {

        // holds the winners of the tournament
        Population winners = new Population(noOfTournaments);
        // For determining winner of tournament
        double comparator = 0.75;

        for (int i = 0; i < noOfTournaments; i++) {
            //Generate a value from within the population
            int random1 = getRandomNumber(0, population.size);
            //Fetch the chromosome of the associated value
            Chromosome participant1 = population.populationMap.get(random1);
            int random2 = getRandomNumber(0, population.size);
            Chromosome participant2 = population.populationMap.get(random2);

            double probability = Math.random();
            System.out.println("Probability: " + probability);

            //Compare score of participants
            Chromosome higherScore;
            Chromosome lowerScore;

            if (participant1.getScore() > participant2.getScore()) {
                higherScore = participant1;
                lowerScore = participant2;

            } else {
                higherScore = participant2;
                lowerScore = participant1;
            }
            System.out.println("Higher Score: " + higherScore.getScore());
            System.out.println("Lower Score: " + lowerScore.getScore());

            if (probability < comparator) {
                // Add winning participant to linked list of winners
                winners.populationMap.put(i, higherScore);

            } else {
                winners.populationMap.put(i, lowerScore);
            }
        }
        return winners;
    }

    public static void crossover(Population originalPopulation, Population winnerPopulation) {

        //Int array of possible positions for a row to start on 0-9
        int[] rowStartPositions = new int[]{0, 9, 18, 27, 36, 45, 54, 63, 72};

        //For each member of the original population
        for (int i = 0; i < originalPopulation.size; i++) {
            Chromosome currentChromosome = originalPopulation.populationMap.get(i);
            //For each row start position
            for (int j = 0; j < rowStartPositions.length; j++) {
                //Select a random winner in range 0 -> to winner population size
                int randomWinnerPosition = getRandomNumber(0, winnerPopulation.size);
                Chromosome randomWinner = winnerPopulation.populationMap.get(randomWinnerPosition);

//                System.out.println("Chromosome genes before crossover: "+ Arrays.toString(currentChromosome.genes));
                // Filling row - k as row offset
                for (int k = 0;k<9;k++){
                    int currentRowStart = rowStartPositions[j];
                    int nextElement = currentRowStart + k;
                    currentChromosome.genes[nextElement] = randomWinner.genes[nextElement];
                }
//                System.out.println("genes after Crossover" +Arrays.toString(currentChromosome.genes));

            }
            originalPopulation.populationMap.put(i, currentChromosome);
        }
    }



    public static void mutation(Population population){
//    Randomise mutation
//        Set mutation rate
        // 10 percent chance to
        double mutationRate = 0.1;
        int[] row =  new int[9];

        //for each chromosome in the population
        for(int i=0; i<15; i++){
            Chromosome currChromosome = population.populationMap.get(i);
            for(int j=0; j < currChromosome.genes.length; j++){
               int currNode = j;
               int currNodeVal = currChromosome.genes[currNode];

               //variable for current node
            //Placeholder chromosome to grab non-static values from
            Chromosome placeholder = new Chromosome();
            //current chromosome
            placeholder.generateChromosome(Driver.board);
            //putting non-static values into their own list for reference later
            ArrayList<Integer> nonStaticValues = placeholder.getNonStaticValues();

               if (nonStaticValues.contains(currNode)){
                   double probability = Math.random();
                   if(probability <= mutationRate ){
                       int randomNumber = getRandomNumber(0, 9);
                       currChromosome.genes[currNode] = randomNumber;
                   }
               }
//               System.out.println("Genes: " + );
            }
//            //variable for current node
//            int currentNode = 0;
//            //Placeholder chromosome to grab non-static values from
//            Chromosome placeholder = new Chromosome();
//            //current chromosome
//            placeholder.generateChromosome(Driver.board);
//            //putting non-static values into their own list for reference later
//            ArrayList<Integer> nonStaticValues = placeholder.getNonStaticValues();
//
//
//            // for each row in each chromosome
//            for (int j=0; j<9; j++){
//                //determine if row will be mutated
//                //Generate a random probability to determine whether
//                double probability = Math.random();
//
//                for (int k = 0; k < 9; k++){
//                    if(probability <= mutationRate ){
//                        placeholder.genes[currentNode] = currChromosome.genes[currentNode];
//
//
//                    }
//                    System.out.println("Current Node: " + currentNode);
//                    System.out.println("Current Node Data: "+ currChromosome.genes[currentNode]);
//                    currentNode++;
//                }
//            }



        }
    }
}
