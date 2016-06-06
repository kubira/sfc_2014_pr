/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.task;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossover;
import cz.vutbr.fit.xkubis03.sfc.ga.crossover.ChromosomeCrossoverType;
import cz.vutbr.fit.xkubis03.sfc.ga.mutation.ChromosomeMutation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomeNextPopulationType;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.population.FitnessConversionType;
import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelection;
import cz.vutbr.fit.xkubis03.sfc.ga.selection.ParentSelectionType;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Task implements ITask {

    protected int iMin;
    protected int iMax;
    protected int pSize;
    protected int cLength;
    protected double dMin;
    protected double dMax;
    protected ArrayList set;
    protected int iteration; /**/
    protected int mutationIndex;
    protected int numberOfPoints;
    protected int indexOfSolution; /**/
    protected int numberOfSamples;
    protected int numberOfParents;
    protected int selectivePressure;
    protected ChromosomeType chType;
    protected int numberOfIterations;
    protected double percentOfReplace;
    protected double percentOfParents;
    protected double mutationThreshold;
    protected ParentSelection pSelection;
    protected ParentSelectionType psType;
    protected int chromosomeCrossoverIndex;
    protected ChromosomeMutation cMutation;
    protected FitnessConversionType fcType;
    protected ChromosomeCrossover cCrossover;
    protected ChromosomeCrossoverType coType;
    protected ChromosomePopulation cPopulation;
    protected ChromosomeNextPopulationType cnpType;

    protected ArrayList parents;
    protected ArrayList offspring;

    public Task() {
        pSelection = new ParentSelection();
        cCrossover = new ChromosomeCrossover();
        iteration = 0;
        indexOfSolution = -1;

        offspring = new ArrayList();
    }

    @Override
    public void generateInitialPopulation() {
        // Generate initial population
        cPopulation.generate(cLength);
    }

    @Override
    public void evaluatePopulation() {
        Iterator it = cPopulation.getPopulation().iterator();

        switch(chType) {
            case BINARY: {
                while(it.hasNext()) {
                    BinaryChromosome bc = (BinaryChromosome)it.next();
                    bc.setFitness(computeFitness(bc));
                }
                break;
            }
            case INTEGER: {
                while(it.hasNext()) {
                    IntegerChromosome ic = (IntegerChromosome)it.next();
                    ic.setFitness(computeFitness(ic));
                }
                break;
            }
            case DOUBLE: {
                while(it.hasNext()) {
                    DoubleChromosome dc = (DoubleChromosome)it.next();
                    dc.setFitness(computeFitness(dc));
                }
                break;
            }
            case STRING: {
                while(it.hasNext()) {
                    StringChromosome sc = (StringChromosome)it.next();
                    sc.setFitness(computeFitness(sc));
                }
                break;
            }
            case PERMUTATION: {
                while(it.hasNext()) {
                    PermutationChromosome pc = (PermutationChromosome)it.next();
                    pc.setFitness(computeFitness(pc));
                }
                break;
            }
        }
    }

    @Override
    public void selectParents() {
        switch(psType) {
            case ROULETTE: {
                parents = pSelection.roulette(cPopulation, selectivePressure, numberOfParents, fcType);
                break;
            }
            case STOCHASTIC: {
                parents = pSelection.stochastic(cPopulation, selectivePressure, numberOfParents, fcType);
                break;
            }
            case ELITE: {
                parents = pSelection.elite(cPopulation, selectivePressure, percentOfParents, fcType);
                break;
            }
            case TOURNAMENT: {
                parents = pSelection.tournament(cPopulation, selectivePressure, numberOfSamples, numberOfParents, fcType);
                break;
            }
        }
    }

    @Override
    public void createOffspring() {
        Iterator it = parents.iterator();

        offspring.clear();

        switch(coType) {
            case SINGLE_POINT: {
                while(it.hasNext()) {
                    IChromosome c1 = (IChromosome)it.next();
                    IChromosome c2 = (IChromosome)it.next();

                    if(chromosomeCrossoverIndex == 0) {
                        offspring.addAll(cCrossover.singlePointCrossover(c1, c2));
                    } else {
                        offspring.addAll(cCrossover.singlePointCrossover(c1, c2, chromosomeCrossoverIndex));
                    }
                }
                break;
            }
            case MULTI_POINT: {
                while(it.hasNext()) {
                    IChromosome c1 = (IChromosome)it.next();
                    IChromosome c2 = (IChromosome)it.next();

                    if(numberOfPoints == 0) {
                        offspring.addAll(cCrossover.multiPointCrossover(c1, c2));
                    } else {
                        offspring.addAll(cCrossover.multiPointCrossover(c1, c2, numberOfPoints));
                    }
                }
                break;
            }
            case UNIFORM: {
                while(it.hasNext()) {
                    IChromosome c1 = (IChromosome)it.next();
                    IChromosome c2 = (IChromosome)it.next();

                    offspring.addAll(cCrossover.uniformCrossover(c1, c2));
                }
                break;
            }
            case ARITHMETIC: {
                while(it.hasNext()) {
                    DoubleChromosome c1 = (DoubleChromosome)it.next();
                    DoubleChromosome c2 = (DoubleChromosome)it.next();

                    offspring.addAll(cCrossover.arithmeticCrossover(c1, c2));
                }
                break;
            }
            case HEURISTIC: {
                while(it.hasNext()) {
                    DoubleChromosome c1 = (DoubleChromosome)it.next();
                    DoubleChromosome c2 = (DoubleChromosome)it.next();

                    offspring.addAll(cCrossover.heuristicCrossover(c1, c2));
                }
                break;
            }
            case INDEX_TABLE: {
                while(it.hasNext()) {
                    PermutationChromosome c1 = (PermutationChromosome)it.next();
                    PermutationChromosome c2 = (PermutationChromosome)it.next();

                    offspring.addAll(cCrossover.indexTableCrossover(c1, c2));
                }
                break;
            }
            case PMX: {
                while(it.hasNext()) {
                    PermutationChromosome c1 = (PermutationChromosome)it.next();
                    PermutationChromosome c2 = (PermutationChromosome)it.next();

                    offspring.addAll(cCrossover.pmxCrossover(c1, c2));
                }
                break;
            }
        }
    }

    @Override
    public void mutateOffspring() {
        Iterator it = offspring.iterator();
        ArrayList mutatedOffspring = new ArrayList();

        switch(chType) {
            case BINARY: {
                while(it.hasNext()) {
                    if(mutationIndex == -1) {
                        mutatedOffspring.add(cMutation.mutate((BinaryChromosome)it.next()));
                    } else {
                        mutatedOffspring.add(cMutation.mutate((BinaryChromosome)it.next(), mutationIndex));
                    }
                }
                break;
            }
            case INTEGER: {
                while(it.hasNext()) {
                    mutatedOffspring.add(cMutation.mutate((IntegerChromosome)it.next()));
                }
                break;
            }
            case DOUBLE: {
                while(it.hasNext()) {
                    mutatedOffspring.add(cMutation.mutate((DoubleChromosome)it.next()));
                }
                break;
            }
            case STRING: {
                while(it.hasNext()) {
                    mutatedOffspring.add(cMutation.mutate((StringChromosome)it.next()));
                }
                break;
            }
            case PERMUTATION: {
                while(it.hasNext()) {
                    mutatedOffspring.add(cMutation.mutate((PermutationChromosome)it.next()));
                }
                break;
            }
        }

        offspring = mutatedOffspring;
    }

    @Override
    public void createNewPopulation() {
        switch(cnpType) {
            case GENERATIONAL: {
                cPopulation.nextGenerational(offspring);
                break;
            }
            case INCREMENTAL: {
                cPopulation.nextIncremental(offspring);
                break;
            }
            case PURE_REINSERTION: {
                cPopulation.nextPureReinsertion(offspring);
                break;
            }
            case UNIFORM_REINSERTION: {
                cPopulation.nextUniformReinsertion(offspring);
                break;
            }
            case ELITIST_REINSERTION: {
                cPopulation.nextElitistReinsertion(offspring);
                break;
            }
            case TOURNAMENT_REINSERTION: {
                cPopulation.nextTournamentReinsertion(offspring, percentOfReplace);
                break;
            }
        }
    }

    @Override
    public void incrementIteration() {
        iteration++;
    }

    @Override
    public void printResult() {
        // Show solution
        System.out.print(getResult());
    }

    @Override
    public String getResult() {
        String result = "VÝSLEDEK\n";
        result += "--------------------------------------------------------------------------------\n";
        result += "Počet iterací (maximum): " + iteration + " (" + numberOfIterations + ")\n";
        result += "ŘEŠENÍ: " + getSolution() + "\n";

        return result;
    }

    @Override
    public String getSolution() {
        if(indexOfSolution >= 0) {
            return ((IChromosome)cPopulation.getPopulation().get(indexOfSolution)).toString();
        } else {
            return "Řešení nebylo nalezeno.";
        }
    }

    @Override
    public void run() {
        // Initial population
        generateInitialPopulation();

        // LOOP over iteration
        while(iteration < numberOfIterations) {

            // Evaluate
            evaluatePopulation();

            // End condition ? -> CONTINUE/END
            if(checkEndCondition()) {
                break;
            }

            // Select parents
            selectParents();

            // Create offspring
            createOffspring();

            // Mutate offspring
            mutateOffspring();

            // Create new population
            createNewPopulation();

            // Increment iteration
            incrementIteration();
        }

        printResult();
    }

    public int getiMin() {
        return iMin;
    }

    public int getiMax() {
        return iMax;
    }

    public int getpSize() {
        return pSize;
    }

    public int getcLength() {
        return cLength;
    }

    public double getdMin() {
        return dMin;
    }

    public double getdMax() {
        return dMax;
    }

    public ArrayList getSet() {
        return set;
    }

    public int getIteration() {
        return iteration;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public int getNumberOfParents() {
        return numberOfParents;
    }

    public int getSelectivePressure() {
        return selectivePressure;
    }

    public ChromosomeType getChType() {
        return chType;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public double getPercentOfReplace() {
        return percentOfReplace;
    }

    public double getPercentOfParents() {
        return percentOfParents;
    }

    public double getMutationThreshold() {
        return mutationThreshold;
    }

    public ParentSelection getpSelection() {
        return pSelection;
    }

    public ParentSelectionType getPsType() {
        return psType;
    }

    public ChromosomeMutation getcMutation() {
        return cMutation;
    }

    public FitnessConversionType getFcType() {
        return fcType;
    }

    public ChromosomeCrossover getcCrossover() {
        return cCrossover;
    }

    public ChromosomeCrossoverType getCoType() {
        return coType;
    }

    public ChromosomePopulation getcPopulation() {
        return cPopulation;
    }

    public ChromosomeNextPopulationType getCnpType() {
        return cnpType;
    }

    public void setiMin(int iMin) {
        this.iMin = iMin;
    }

    public void setiMax(int iMax) {
        this.iMax = iMax;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public void setcLength(int cLength) {
        this.cLength = cLength;
    }

    public void setdMin(double dMin) {
        this.dMin = dMin;
    }

    public void setdMax(double dMax) {
        this.dMax = dMax;
    }

    public void setSet(ArrayList set) {
        this.set = set;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public void setNumberOfSamples(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public void setNumberOfParents(int numberOfParents) {
        this.numberOfParents = numberOfParents;
    }

    public void setSelectivePressure(int selectivePressure) {
        this.selectivePressure = selectivePressure;
    }

    public void setChType(ChromosomeType chType) {
        this.chType = chType;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public void setPercentOfReplace(double percentOfReplace) {
        this.percentOfReplace = percentOfReplace;
    }

    public void setPercentOfParents(double percentOfParents) {
        this.percentOfParents = percentOfParents;
    }

    public void setMutationThreshold(double mutationThreshold) {
        this.mutationThreshold = mutationThreshold;
    }

    public void setpSelection(ParentSelection pSelection) {
        this.pSelection = pSelection;
    }

    public void setPsType(ParentSelectionType psType) {
        this.psType = psType;
    }

    public void setcMutation(ChromosomeMutation cMutation) {
        this.cMutation = cMutation;
    }

    public void setFcType(FitnessConversionType fcType) {
        this.fcType = fcType;
    }

    public void setcCrossover(ChromosomeCrossover cCrossover) {
        this.cCrossover = cCrossover;
    }

    public void setCoType(ChromosomeCrossoverType coType) {
        this.coType = coType;
    }

    public void setcPopulation(ChromosomePopulation cPopulation) {
        this.cPopulation = cPopulation;
    }

    public void setCnpType(ChromosomeNextPopulationType cnpType) {
        this.cnpType = cnpType;
    }

    public int getChromosomeCrossoverIndex() {
        return chromosomeCrossoverIndex;
    }

    public void setChromosomeCrossoverIndex(int chromosomeCrossoverIndex) {
        this.chromosomeCrossoverIndex = chromosomeCrossoverIndex;
    }

    public int getMutationIndex() {
        return mutationIndex;
    }

    public void setMutationIndex(int mutationIndex) {
        this.mutationIndex = mutationIndex;
    }
}
