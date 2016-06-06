/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.selection;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeCompareFitnessDownComparator;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.ChromosomeCompareFitnessUpComparator;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.population.FitnessConversionType;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Constants;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ParentSelection {

    private final Generator generator;

    public ParentSelection() {
        this.generator = new Generator();
    }

    public ArrayList roulette(ChromosomePopulation p, int selectionPressure, int numberOfParents, FitnessConversionType convType) {
        ArrayList result = new ArrayList();

        switch(convType) {
            case PROPORTIONAL: {
                p.convertToProportion();
                break;
            }
            case RANK_BASED: {
                p.convertToLinearRank(selectionPressure);
                break;
            }
            case NONE: {
                p.convertToFitness();
                break;
            }
        }

        Collections.sort(p.getPopulation(), new ChromosomeCompareFitnessUpComparator());

        // Re-calculate fitness for roulette
        Iterator it = p.getPopulation().iterator();
        double sum = 0.0;

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();

            sum += c.getCompareFitness();
            c.setCompareFitness(sum);
        }

        if(numberOfParents % 2 == 1) numberOfParents++;
        // Select parents
        double probability;
        for(int parent = 0; parent < numberOfParents; parent++) {
            probability = generator.getUniform();

            it = p.getPopulation().iterator();
            IChromosome c;

            do {
                c = (IChromosome)it.next();
            } while(it.hasNext() && c.getCompareFitness() < probability);

            result.add(c);
        }

        return result;
    }

    public ArrayList stochastic(ChromosomePopulation p, int selectionPressure, int numberOfParents, FitnessConversionType convType) {
        ArrayList result = new ArrayList();

        switch(convType) {
            case PROPORTIONAL: {
                p.convertToProportion();
                break;
            }
            case RANK_BASED: {
                p.convertToLinearRank(selectionPressure);
                break;
            }
            case NONE: {
                p.convertToFitness();
                break;
            }
        }

        Collections.sort(p.getPopulation(), new ChromosomeCompareFitnessUpComparator());

        // Re-calculate fitness for roulette
        Iterator it = p.getPopulation().iterator();
        double sum = 0.0;

        while(it.hasNext()) {
            IChromosome c = (IChromosome)it.next();

            sum += c.getCompareFitness();
            c.setCompareFitness(sum);
        }

        if(numberOfParents % 2 == 1) numberOfParents++;

        for(int parent = 0; parent < numberOfParents; parent++) {
            // Get two chromosomes from parents
            double c1probability = generator.getUniform();
            double c2probability = generator.getUniform();

            it = p.getPopulation().iterator();
            IChromosome c1;

            do {
                c1 = (IChromosome)it.next();
            } while(it.hasNext() && c1.getCompareFitness() < c1probability);

            it = p.getPopulation().iterator();
            IChromosome c2;

            do {
                c2 = (IChromosome)it.next();
            } while(it.hasNext() && c2.getCompareFitness() < c2probability);

            // Select better of two and add to result
            if(c1.getCompareFitness() > c2.getCompareFitness()) {
                result.add((IChromosome)c1);
            } else {
                result.add((IChromosome)c2);
            }
        }

        return result;
    }

    public ArrayList elite(ChromosomePopulation p, int selectionPressure, double percentOfParents, FitnessConversionType convType) {
        ArrayList result = new ArrayList();

        switch(convType) {
            case PROPORTIONAL: {
                p.convertToProportion();
                break;
            }
            case RANK_BASED: {
                p.convertToLinearRank(selectionPressure);
                break;
            }
            case NONE: {
                p.convertToFitness();
                break;
            }
        }

        Collections.sort(p.getPopulation(), new ChromosomeCompareFitnessDownComparator());

        double percent = percentOfParents / 100.0;
        int numberOfParents = (int)(p.getPopulation().size() * percent);

        if(numberOfParents < Constants.MINIMAL_NUMBER_OF_PARENTS) {
            numberOfParents = Constants.MINIMAL_NUMBER_OF_PARENTS;
        }

        if(numberOfParents % 2 == 1) numberOfParents++;

        for(int parent = 0; parent < numberOfParents; parent++) {
            result.add(p.getPopulation().get(parent));
        }

        return result;
    }

    public ArrayList tournament(ChromosomePopulation p, int selectionPressure, int numberOfSamples, int numberOfParents, FitnessConversionType convType) {
        ArrayList result = new ArrayList();

        switch(convType) {
            case PROPORTIONAL: {
                p.convertToProportion();
                break;
            }
            case RANK_BASED: {
                p.convertToLinearRank(selectionPressure);
                break;
            }
            case NONE: {
                p.convertToFitness();
                break;
            }
        }

        if(numberOfParents % 2 == 1) numberOfParents++;

        ArrayList samples = new ArrayList();
        // For number of parents
        for(int parent = 0; parent < numberOfParents; parent++) {
            samples.clear();

            // Select some chromosomes
            for(int sample = 0; sample < numberOfSamples; sample++) {
                int index = (int)generator.getUniform(0, p.getPopulation().size());

                samples.add(p.getPopulation().get(index));
            }

            // Select the best of selection
            Iterator it = samples.iterator();
            IChromosome best = (IChromosome)it.next();

            while(it.hasNext()) {
                IChromosome c = (IChromosome)it.next();

                if(c.getCompareFitness() > best.getCompareFitness()) {
                    best = c;
                }
            }

            result.add(best);
        }

        return result;
    }
}
