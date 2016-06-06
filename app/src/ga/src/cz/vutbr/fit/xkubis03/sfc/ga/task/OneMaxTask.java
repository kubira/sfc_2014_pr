/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.task;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.mutation.ChromosomeMutation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.BinaryChromosomePopulation;
import java.util.Iterator;

public class OneMaxTask extends Task {

    //int numberOfPoints,
    //int numberOfSamples,
    //int numberOfParents,
    //int selectivePressure,
    //double percentOfReplace,
    //double percentOfParents,
    //ParentSelectionType psType,
    //FitnessConversionType fcType,
    //ChromosomeCrossoverType coType,
    //ChromosomeNextPopulationType cnpType

    public OneMaxTask(int pSize, int cLength, int numberOfIterations, double mutationThreshold) {
        super();
        chType = ChromosomeType.BINARY;
        this.numberOfIterations = numberOfIterations;
        this.cLength = cLength;
        cMutation = new ChromosomeMutation(mutationThreshold);
        cPopulation = new BinaryChromosomePopulation(pSize);
    }

    @Override
    public double computeFitness(IChromosome c) {
        Iterator it = c.getChromosome().iterator();
        double fitness = 0.0;

        while(it.hasNext()) {
            fitness += Double.parseDouble(it.next().toString());
        }

        return fitness;
    }

    @Override
    public boolean checkEndCondition() {
        // Check condition
        Iterator it = cPopulation.getPopulation().iterator();

        while(it.hasNext()) {
            BinaryChromosome bc = (BinaryChromosome)it.next();

            if(bc.getFitness() == cLength) {
                indexOfSolution = cPopulation.getPopulation().indexOf(bc);
                return true;
            }
        }

        return false;
    }

    @Override
    public void printResult() {
        super.printResult();
    }

    public String getSolutionString() {
        return super.getSolution();
    }
}
