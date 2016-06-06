/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.task;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;

public interface ITask {
    public double computeFitness(IChromosome c);

    public void generateInitialPopulation();

    public void evaluatePopulation();

    public boolean checkEndCondition();

    public void selectParents();

    public void createOffspring();

    public void mutateOffspring();

    public void createNewPopulation();

    public void incrementIteration();

    public void run();

    public void printResult();

    public String getResult();

    public String getSolution();
}
