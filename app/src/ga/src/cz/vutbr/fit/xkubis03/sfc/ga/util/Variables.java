/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.util;

import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.BinaryChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.DoubleChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.IntegerChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.PermutationChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.chromosome.StringChromosome;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.ToolBar;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.WorkingPlace;
import cz.vutbr.fit.xkubis03.sfc.ga.population.ChromosomePopulation;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Variables {

    private static ChromosomePopulation population;
    private static ChromosomePopulation newPopulation;
    private static JPanel actualScreen;
    private static ToolBar toolBar;
    private static WorkingPlace workingPlace;
    private static double requiredFitness;
    private static ArrayList parentSelection;
    private static ArrayList offspring;
    private static ArrayList mutatedOffspring;
    private static FitnessMethodType fitnessMethodType;
    private static int binNum;
    private static int allSameInt;
    private static String allSameString;
    private static String concrete;

    public static void setPopulation(ChromosomePopulation cp) {
        population = cp;
    }

    public static ChromosomePopulation getPopulation() {
        return population;
    }

    public static void setNewPopulation(ChromosomePopulation cp) {
        newPopulation = cp;
    }

    public static ChromosomePopulation getNewPopulation() {
        return newPopulation;
    }

    public static JPanel getActualScreen() {
        return actualScreen;
    }

    public static void setActualScreen(JPanel actualScreen) {
        Variables.actualScreen = actualScreen;
    }

    public static ToolBar getToolBar() {
        return toolBar;
    }

    public static void setToolBar(ToolBar toolBar) {
        Variables.toolBar = toolBar;
    }

    public static WorkingPlace getWorkingPlace() {
        return workingPlace;
    }

    public static void setWorkingPlace(WorkingPlace workingPlace) {
        Variables.workingPlace = workingPlace;
    }

    public static double getRequiredFitness() {
        return requiredFitness;
    }

    public static void setRequiredFitness(double requiredFitness) {
        Variables.requiredFitness = requiredFitness;
        workingPlace.getCecPanel().revalidateFitness();
    }

    public static ArrayList getParentSelection() {
        return parentSelection;
    }

    public static void setParentSelection(ArrayList parentSelection) {
        Variables.parentSelection = parentSelection;
    }

    public static ArrayList getOffspring() {
        return offspring;
    }

    public static void setOffspring(ArrayList offspring) {
        Variables.offspring = offspring;
    }

    public static ArrayList getMutatedOffspring() {
        return mutatedOffspring;
    }

    public static void setMutatedOffspring(ArrayList mutatedOffspring) {
        Variables.mutatedOffspring = mutatedOffspring;
    }

    public static FitnessMethodType getFitnessMethodType() {
        return fitnessMethodType;
    }

    public static void setFitnessMethodType(FitnessMethodType fitnessMethodType) {
        Variables.fitnessMethodType = fitnessMethodType;
    }

    public static int getBinNum() {
        return binNum;
    }

    public static void setBinNum(int binNum) {
        Variables.binNum = binNum;
    }

    public static int getAllSameInt() {
        return allSameInt;
    }

    public static void setAllSameInt(int allSameInt) {
        Variables.allSameInt = allSameInt;
    }

    public static void setAllSameString(String allSameString) {
        Variables.allSameString = allSameString;
    }

    public static String getAllSameString() {
        return allSameString;
    }

    public static void setConcrete(String concrete) {
        Variables.concrete = concrete;
    }

    public static String getConcrete() {
        return concrete;
    }

    public static void resizeTable(JTable table) {
        for (int i = (table.getColumnCount()-1); i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer;
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
                    false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 2);
        }

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for(int i = 0; i < (table.getColumnCount()-1); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn((table.getColumnCount()-1)).setCellRenderer(renderer);
    }

    // FITNESS

    public static double getOneMaxFitness(BinaryChromosome bc) {
        double ones = 0.0;

        Iterator cIt = bc.getChromosome().iterator();

        while(cIt.hasNext()) {
            int value = (int)cIt.next();

            ones += value == 1 ? 1 : 0;
        }

        return ones;
    }

    public static double getZeroMaxFitness(BinaryChromosome bc) {
        double zeros = 0.0;

        Iterator cIt = bc.getChromosome().iterator();

        while(cIt.hasNext()) {
            int value = (int)cIt.next();

            zeros += value == 0 ? 1 : 0;
        }

        return zeros;
    }

    public static double getBinNumFitness(BinaryChromosome bc, int num) {
        double goodOnes = 0.0;

        ArrayList binaryNum = new ArrayList();
        // num to binary
        while(num > 0) {
            binaryNum.add(num % 2);
            num /= 2;
        }

        int cLength = bc.getChromosome().size();

        for(; binaryNum.size() < cLength;) {
            binaryNum.add(0);
        }

        Collections.reverse(binaryNum);

        Iterator cIt = bc.getChromosome().iterator();
        Iterator numIt = binaryNum.iterator();

        while(cIt.hasNext()) {
            if(((int)cIt.next()) == ((int)numIt.next())) {
                goodOnes++;
            }
        }

        return goodOnes;
    }

    public static void computeMutatedOffspringFitness() {
        Iterator it = mutatedOffspring.iterator();

        while(it.hasNext()) {
            switch(Variables.fitnessMethodType) {
                case ONE_MAX: {
                    BinaryChromosome bc = (BinaryChromosome)it.next();
                    bc.setFitness(Variables.getOneMaxFitness(bc));
                    break;
                }
                case ZERO_MAX: {
                    BinaryChromosome bc = (BinaryChromosome)it.next();
                    bc.setFitness(Variables.getZeroMaxFitness(bc));
                    break;
                }
                case BIN_NUM: {
                    BinaryChromosome bc = (BinaryChromosome)it.next();
                    bc.setFitness(Variables.getBinNumFitness(bc, binNum));
                    break;
                }
                case ALL_SAME_INT: {
                    IntegerChromosome ic = (IntegerChromosome)it.next();
                    ic.setFitness(Variables.getAllSameFitness(ic, allSameInt));
                    break;
                }
                case NON_DOWN_INT: {
                    IntegerChromosome ic = (IntegerChromosome)it.next();
                    ic.setFitness(Variables.getNonDownFitness(ic));
                    break;
                }
                case NON_UP_INT: {
                    IntegerChromosome ic = (IntegerChromosome)it.next();
                    ic.setFitness(Variables.getNonUpFitness(ic));
                    break;
                }
                case NON_DOWN_DOUBLE: {
                    DoubleChromosome ic = (DoubleChromosome)it.next();
                    ic.setFitness(Variables.getNonDownFitness(ic));
                    break;
                }
                case NON_UP_DOUBLE: {
                    DoubleChromosome ic = (DoubleChromosome)it.next();
                    ic.setFitness(Variables.getNonUpFitness(ic));
                    break;
                }
                case ALL_SAME_STR: {
                    StringChromosome sc = (StringChromosome)it.next();
                    sc.setFitness(Variables.getAllSameFitness(sc, allSameString));
                    break;
                }
                case CONCRETE: {
                    PermutationChromosome pc = (PermutationChromosome)it.next();
                    pc.setFitness(Variables.getConcreteFitness(pc, concrete));
                    break;
                }
                default: {
                    throw new UnsupportedOperationException("Variables.compute... Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
        }
    }

    public static double getNonDownFitness(IntegerChromosome c) {
        double count = 0;
        ArrayList ch = c.getChromosome();

        for(int i = 0; i < (ch.size() - 1); i++) {
            if((int)ch.get(i) <= (int)ch.get(i+1)) {
                count++;
            }
        }

        return count;
    }

    public static double getNonUpFitness(IntegerChromosome c) {
        double count = 0;
        ArrayList ch = c.getChromosome();

        for(int i = 0; i < (ch.size() - 1); i++) {
            if((int)ch.get(i) >= (int)ch.get(i+1)) {
                count++;
            }
        }

        return count;
    }

    public static double getNonDownFitness(DoubleChromosome c) {
        double count = 0;
        ArrayList ch = c.getChromosome();

        for(int i = 0; i < (ch.size() - 1); i++) {
            if((double)ch.get(i) <= (double)ch.get(i+1)) {
                count++;
            }
        }

        return count;
    }

    public static double getNonUpFitness(DoubleChromosome c) {
        double count = 0;
        ArrayList ch = c.getChromosome();

        for(int i = 0; i < (ch.size() - 1); i++) {
            if((double)ch.get(i) >= (double)ch.get(i+1)) {
                count++;
            }
        }

        return count;
    }

    public static double getAllSameFitness(IntegerChromosome bc, int num) {
        double count = 0;

        Iterator it = bc.getChromosome().iterator();
        while(it.hasNext()) {
            if((int)it.next() == num) {
                count++;
            }
        }

        return count;
    }

    public static double getAllSameFitness(StringChromosome sc, String s) {
        double count = 0;
        ArrayList ch = sc.getChromosome();

        for(Object ch1 : ch) {
            if(((String)ch1).equals(s)) {
                count++;
            }
        }

        return count;
    }

    public static double getConcreteFitness(PermutationChromosome pc, String s) {
        double count = 0;

        String[] items = s.split("");
        ArrayList str = new ArrayList(Arrays.asList(items));
        ArrayList pcStr = pc.getChromosome();

        for(int i = 0; i < str.size(); i++) {
            if(((String)str.get(i)).equals((String)pcStr.get(i))) {
                count++;
            }
        }

        return count;
    }
}
