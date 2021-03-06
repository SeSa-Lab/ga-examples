package it.unisa.ga;

import it.unisa.ga.fitness.ConflictsFunction;
import it.unisa.ga.individual.ChessboardIndividual;
import it.unisa.ga.population.initializer.FixedSizeChessboardRandomInitializer;
import it.unisa.ga.metaheuristic.SGA;
import it.unisa.ga.operator.crossover.ChessboardSinglePointCrossover;
import it.unisa.ga.operator.mutation.ChessboardSinglePointMutation;
import it.unisa.ga.operator.selection.RouletteWheelSelection;
import it.unisa.ga.results.GAResults;

import java.util.Arrays;

public class EightQueensRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int numberOfIndividuals = 1;
        final int chessSize = 8;
        final double mutationProbability = 1;
        final int maxIterations = 1000;
        final int maxIterationsNoImprovements = 0;

        ConflictsFunction fitnessFunction = new ConflictsFunction();
        FixedSizeChessboardRandomInitializer initializer = new FixedSizeChessboardRandomInitializer(numberOfIndividuals, chessSize);
        RouletteWheelSelection<ChessboardIndividual> selectionOperator = new RouletteWheelSelection<>();
        ChessboardSinglePointCrossover crossoverOperator = new ChessboardSinglePointCrossover();
        ChessboardSinglePointMutation mutationOperator = new ChessboardSinglePointMutation();

        SGA<ChessboardIndividual> geneticAlgorithm = new SGA<>(fitnessFunction, initializer,
                selectionOperator, crossoverOperator, mutationOperator, mutationProbability, maxIterations, maxIterationsNoImprovements);
        GAResults<ChessboardIndividual> GAResults = geneticAlgorithm.run();
        ChessboardIndividual bestIndividual = GAResults.getBestIndividual();
        GAResults.getLog().forEach(System.out::println);
        System.out.printf("Search terminated in %d/%d iterations.%n", GAResults.getNumberOfIterations(), geneticAlgorithm.getMaxIterations());
        System.out.printf("Best individual is %s, with fitness %.2f.%n", Arrays.toString(bestIndividual.getCoding()), bestIndividual.getFitness());
    }

}
