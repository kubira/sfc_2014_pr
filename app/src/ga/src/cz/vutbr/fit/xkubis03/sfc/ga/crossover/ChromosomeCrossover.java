/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.crossover;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChromosomeCrossover {

    private Generator generator = null;

    public ChromosomeCrossover() {
        generator = new Generator();
    }

    public ArrayList singlePointCrossover(IChromosome c1, IChromosome c2) {
        int crossoverPosition = (int)generator.getUniform(1, c1.getChromosome().size());

        return singlePointCrossover(c1, c2, crossoverPosition);
    }

    public ArrayList singlePointCrossover(IChromosome c1, IChromosome c2, int position) {
        IChromosome n1 = c1.cloneChromosome();
        IChromosome n2 = c2.cloneChromosome();

        n1.getChromosome().clear();
        n2.getChromosome().clear();

        for(int index = 0; index < c1.getChromosome().size(); index++) {
            if(index < position) {
                n1.getChromosome().add(c1.getChromosome().get(index));
                n2.getChromosome().add(c2.getChromosome().get(index));
            } else {
                n1.getChromosome().add(c2.getChromosome().get(index));
                n2.getChromosome().add(c1.getChromosome().get(index));
            }
        }

        ArrayList offspring = new ArrayList();
        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList multiPointCrossover(IChromosome c1, IChromosome c2) {
        int numberOfPoints = (int)generator.getUniform(2, c1.getChromosome().size());

        return multiPointCrossover(c1, c2, numberOfPoints);
    }

    public ArrayList multiPointCrossover(IChromosome c1, IChromosome c2, int numberOfPoints) {
        ArrayList positions = new ArrayList();
        int position;

        while(positions.size() < numberOfPoints) {
            position = (int)generator.getUniform(1, c1.getChromosome().size());

            if(positions.indexOf(position) < 0) {
                positions.add(position);
            }
        }

        return multiPointCrossover(c1, c2, positions);
    }

    public ArrayList multiPointCrossover(IChromosome c1, IChromosome c2, ArrayList positions) {
        Collections.sort(positions, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int pos1 = (int)o1;
                int pos2 = (int)o2;

                return pos1 < pos2 ? 1 : pos1 == pos2 ? 0 : -1;
            }
        });

        IChromosome n1 = c1.cloneChromosome();
        IChromosome n2 = c2.cloneChromosome();
        ArrayList offspring = new ArrayList();
        boolean change = false;

        n1.getChromosome().clear();
        n2.getChromosome().clear();

        for(int index = 0; index < c1.getChromosome().size(); index++) {
            if(positions.indexOf(index) >= 0) {
                change = !change;
            }

            if(change) {
                n1.getChromosome().add(c2.getChromosome().get(index));
                n2.getChromosome().add(c1.getChromosome().get(index));
            } else {
                n1.getChromosome().add(c1.getChromosome().get(index));
                n2.getChromosome().add(c2.getChromosome().get(index));
            }
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList uniformCrossover(IChromosome c1, IChromosome c2) {
        IChromosome n1 = c1.cloneChromosome();
        IChromosome n2 = c2.cloneChromosome();
        ArrayList offspring = new ArrayList();
        double threshold;

        n1.getChromosome().clear();
        n2.getChromosome().clear();

        for(int index = 0; index < c1.getChromosome().size(); index++) {
            threshold = generator.getUniform();

            if(threshold < 0.5) {
                n1.getChromosome().add(c1.getChromosome().get(index));
                n2.getChromosome().add(c2.getChromosome().get(index));
            } else {
                n1.getChromosome().add(c2.getChromosome().get(index));
                n2.getChromosome().add(c1.getChromosome().get(index));
            }
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList arithmeticCrossover(DoubleChromosome c1, DoubleChromosome c2) {
        double min = c1.getMin();
        double max = c1.getMax();

        return arithmeticCrossover(c1, c2, min, max);
    }

    public ArrayList arithmeticCrossover(DoubleChromosome c1, DoubleChromosome c2, double min, double max) {
        DoubleChromosome n1 = new DoubleChromosome(c1);
        DoubleChromosome n2 = new DoubleChromosome(c2);
        ArrayList offspring = new ArrayList();
        double coefficient;

        n1.getChromosome().clear();
        n2.getChromosome().clear();

        // Crossover
        for(int index = 0; index < c1.getChromosome().size(); index++) {
            coefficient = generator.getUniform(min, max);

            n1.getChromosome().add(((double)c1.getChromosome().get(index))*coefficient+((double)c2.getChromosome().get(index))*(1.0-coefficient));
            n2.getChromosome().add(((double)c1.getChromosome().get(index))*(1.0-coefficient)+((double)c2.getChromosome().get(index))*coefficient);
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList heuristicCrossover(DoubleChromosome c1, DoubleChromosome c2) {
        DoubleChromosome n1 = new DoubleChromosome(c1);
        DoubleChromosome n2 = new DoubleChromosome(c2);
        ArrayList offspring = new ArrayList();
        DoubleChromosome better;
        DoubleChromosome worse;
        double coefficient;

        n1.getChromosome().clear();
        n2.getChromosome().clear();

        if(c1.getFitness() > c2.getFitness()) {
            better = new DoubleChromosome(c1);
            worse = new DoubleChromosome(c2);
        } else {
            better = new DoubleChromosome(c2);
            worse = new DoubleChromosome(c1);
        }

        // Crossover
        coefficient = generator.getUniform();

        for(int index = 0; index < c1.getChromosome().size(); index++) {
            n1.getChromosome().add(((double)better.getChromosome().get(index))+coefficient*(((double)better.getChromosome().get(index))-((double)worse.getChromosome().get(index))));
            n2.getChromosome().add(better.getChromosome().get(index));
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList indexTableCrossover(PermutationChromosome c1, PermutationChromosome c2) {
        PermutationChromosome n1 = new PermutationChromosome(c1.getSet());
        PermutationChromosome n2 = new PermutationChromosome(c1.getSet());
        PermutationChromosome help = new PermutationChromosome(c1);
        IntegerChromosome n1indexes = new IntegerChromosome();
        IntegerChromosome n2indexes = new IntegerChromosome();
        int genIndex;

        ArrayList offspring = new ArrayList();

        // Create index chromosome of c1
        for(int index = 0; index < c1.getChromosome().size(); index++) {
            genIndex = help.getSet().indexOf(c1.getChromosome().get(index));
            n1indexes.getChromosome().add(genIndex);
            help.getSet().remove(genIndex);
        }

        help = new PermutationChromosome(c1);

        // Create index chromosome of c2
        for(int index = 0; index < c2.getChromosome().size(); index++) {
            genIndex = help.getSet().indexOf(c2.getChromosome().get(index));
            n2indexes.getChromosome().add(genIndex);
            help.getSet().remove(genIndex);
        }

        // Single point crossover chromozomes of indexes
        ArrayList crossovers = singlePointCrossover(n1indexes, n2indexes);

        help = new PermutationChromosome(c1);

        // First offspring
        for(int index = 0; index < c1.getSet().size(); index++) {
            genIndex = (int)((IChromosome)crossovers.get(0)).getChromosome().get(index);
            n1.getChromosome().add(help.getSet().get(genIndex));
            help.getSet().remove(genIndex);
        }

        help = new PermutationChromosome(c1);

        // Second offspring
        for(int index = 0; index < c2.getSet().size(); index++) {
            genIndex = (int)((IChromosome)crossovers.get(1)).getChromosome().get(index);
            n2.getChromosome().add(help.getSet().get(genIndex));
            help.getSet().remove(genIndex);
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }

    public ArrayList pmxCrossover(PermutationChromosome c1, PermutationChromosome c2) {
        ArrayList offspring = new ArrayList();
        PermutationChromosome n1 = new PermutationChromosome(c1);
        PermutationChromosome n2 = new PermutationChromosome(c2);
        int position1 = (int)generator.getUniform(0, c1.getChromosome().size());
        int position2 = (int)generator.getUniform(0, c2.getChromosome().size());

        while(position1 == position2) {
            position2 = (int)generator.getUniform(0, c2.getChromosome().size());
        }

        if(position1 > position2) {
            int swap = position1;
            position1 = position2;
            position2 = swap;
        }

        for(int index = position1; index < position2; index++) {
            Object o1 = c1.getChromosome().get(index);
            Object o2 = c2.getChromosome().get(index);

            int n1indexO1 = n1.getChromosome().indexOf(o1);
            int n1indexO2 = n1.getChromosome().indexOf(o2);
            int n2indexO1 = n2.getChromosome().indexOf(o1);
            int n2indexO2 = n2.getChromosome().indexOf(o2);

            n1.getChromosome().remove(n1indexO1);
            n1.getChromosome().add(n1indexO1, o2);
            n1.getChromosome().remove(n1indexO2);
            n1.getChromosome().add(n1indexO2, o1);

            n2.getChromosome().remove(n2indexO1);
            n2.getChromosome().add(n2indexO1, o2);
            n2.getChromosome().remove(n2indexO2);
            n2.getChromosome().add(n2indexO2, o1);
        }

        offspring.add(n1);
        offspring.add(n2);

        return offspring;
    }
}
