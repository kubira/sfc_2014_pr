/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.population;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeCompareFitnessUpComparator;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeFitnessUpComparator;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeType;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Constants;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class ChromosomePopulation {

    protected ChromosomeType type;
    protected int size;
    protected ArrayList population;

    public ChromosomePopulation(ChromosomeType type, int size) {
        this.type = type;
        this.size = size;
        population = new ArrayList();
    }

    public ChromosomePopulation(ChromosomePopulation p) {
        this.type = p.type;
        this.size = p.size;
        population = new ArrayList();

        Iterator it = p.getPopulation().iterator();

        while(it.hasNext()) {
            IChromosome c = ((IChromosome)it.next()).cloneChromosome();
            population.add(c);
        }
    }

    public abstract ChromosomePopulation clonePopulation();

    public void generate() {
        generate(Constants.DEFAULT_LENGTH);
    }

    public abstract void generate(int length);

    public double getPopulationFitness() {
        double fitness = 0.0;

        Iterator it = population.iterator();

        while(it.hasNext()) {
            fitness += ((IChromosome)it.next()).getFitness();
        }

        return (fitness/size);
    }

    public void convertToFitness() {
        Iterator it = population.iterator();

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();
            c.setCompareFitness(c.getFitness());
        }
    }

    public void convertToProportion() {
        double sum = 0.0;

        Iterator it = population.iterator();

        while(it.hasNext()) {
            sum += ((IChromosome)it.next()).getFitness();
        }

        it = population.iterator();

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();
            c.setCompareFitness(c.getFitness()/sum);
        }
    }

    public void convertToLinearRank(int selectivePressure) {
        double sum = 0.0;

        Collections.sort(population, new ChromosomeCompareFitnessUpComparator());

        for(int index = 0; index < size; index++) {
            double itemFitness = 2 - selectivePressure + 2 * (selectivePressure - 1) * (index) / (size - 1);
            ((IChromosome)population.get(index)).setCompareFitness(itemFitness);
            sum += itemFitness;
        }

        Iterator it = population.iterator();

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();
            c.setCompareFitness(c.getCompareFitness()/sum);
        }
    }

    public ArrayList getPopulation() {
        return population;
    }

    public void nextGenerational(ArrayList offspring) {
        this.population = offspring;
    }

    public void nextIncremental(ArrayList offspring) {
        IChromosome minParent;
        IChromosome maxOffspring;

        minParent = (IChromosome)population.get(0);
        maxOffspring = (IChromosome)offspring.get(0);

        for(int parent = 1; parent < population.size(); parent++) {
            if(minParent.getFitness() > ((IChromosome)population.get(parent)).getFitness()) {
                minParent = (IChromosome)population.get(parent);
            }
        }

        for(int offspringIndex = 1; offspringIndex < offspring.size(); offspringIndex++) {
            if(maxOffspring.getFitness() < ((IChromosome)offspring.get(offspringIndex)).getFitness()) {
                maxOffspring = (IChromosome)offspring.get(offspringIndex);
            }
        }

        population.remove(minParent);
        population.add(maxOffspring);
    }

    public void nextPureReinsertion(ArrayList offspring) {
        nextGenerational(offspring);
    }

    public void nextUniformReinsertion(ArrayList offspring) {
        // Remove parents, number=offspring.size, uniform
        Generator generator = new Generator();
        int removeIndex;

        for (Iterator it = offspring.iterator(); it.hasNext();) {
            it.next();
            removeIndex = (int)generator.getUniform(0, population.size());
            population.remove(removeIndex);
        }

        // Insert all of offspring
        Iterator it = offspring.iterator();
        while(it.hasNext()) {
            population.add(it.next());
        }
    }

    public void nextElitistReinsertion(ArrayList offspring) {
        // Re-sort by pure fitness
        Collections.sort(population, new ChromosomeFitnessUpComparator());
        // Remove the worst of parents
        for (Iterator it = offspring.iterator(); it.hasNext();) {
            it.next();
            population.remove(0);
        }

        // Insert offspring
        Iterator it = offspring.iterator();
        while(it.hasNext()) {
            population.add(it.next());
        }
    }

    public void nextTournamentReinsertion(ArrayList offspring, double percentOfReplace) {
        Generator generator = new Generator();
        ArrayList replacement = new ArrayList();
        ArrayList myOffspring = new ArrayList();
        int numberOfReplace = (int)(population.size() * (percentOfReplace / 100.0));
        int randomParent;
        int randomOffspring;
        IChromosome parentC;
        IChromosome offspringC;

        Iterator it = offspring.iterator();
        while(it.hasNext()) {
            IChromosome c = ((IChromosome)it.next()).cloneChromosome();
            myOffspring.add(c);
        }

        if(numberOfReplace > myOffspring.size()) {
            numberOfReplace = myOffspring.size();
        }

        for(int replace = 0; replace < numberOfReplace; replace++) {
            // Get random parent
            randomParent = (int)generator.getUniform(0, population.size());
            parentC = (IChromosome)population.get(randomParent);
            population.remove(randomParent);
            // Get random offspring
            randomOffspring = (int)generator.getUniform(0, myOffspring.size());
            offspringC = (IChromosome)myOffspring.get(randomOffspring);
            myOffspring.remove(randomOffspring);
            // Tournament
            if(parentC.getFitness() > offspringC.getFitness()) {
                replacement.add(parentC);
            } else {
                replacement.add(offspringC);
            }
        }

        // Copy the rest from replacement
        it = replacement.iterator();
        while(it.hasNext()) {
            population.add(it.next());
        }
    }

    public void printPopulation() {
        Iterator it = population.iterator();
        int i = 0;

        System.out.println("POPULATION");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("ID          Fitness  Chromosome");

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();
            System.out.format("%2d     %12.5f  %s\n", i, c.getFitness(), c);
            i++;
        }
    }

    public ChromosomeType getChromosomeType() {
        return type;
    }
}
