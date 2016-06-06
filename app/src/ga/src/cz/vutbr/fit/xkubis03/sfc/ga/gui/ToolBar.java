/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.PanelType;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class ToolBar extends JPanel implements ActionListener {

    protected LineBorder redBorder = new LineBorder(Color.RED, 1);
    protected Border oldBorder;

    protected JButton ipBtn;
    protected JButton epBtn;
    protected JButton cecBtn;
    protected JButton psBtn;
    protected JButton coBtn;
    protected JButton mBtn;
    protected JButton npBtn;
    protected JButton niBtn;
    protected JButton exBtn;

    public ToolBar() {
        super();

        setLayout(new GridLayout(9, 1, 5, 5));

        setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        ipBtn = new JButton("<html><center>Tvorba<br>počáteční<br>populace</center></html>");
        oldBorder = ipBtn.getBorder();
        ipBtn.setBorder(redBorder);
        ipBtn.setActionCommand("ip");
        ipBtn.addActionListener(this);
        add(ipBtn);

        epBtn = new JButton("<html><center>Ohodnocení<br>populace</center></html>");
        epBtn.setActionCommand("ep");
        epBtn.addActionListener(this);
        add(epBtn);

        cecBtn = new JButton("<html><center>Kontrola<br>ukončovací<br>podmínky</center></html>");
        cecBtn.setActionCommand("cec");
        cecBtn.addActionListener(this);
        add(cecBtn);

        psBtn = new JButton("<html><center>Výběr<br>rodičů</center></html>");
        psBtn.setActionCommand("ps");
        psBtn.addActionListener(this);
        add(psBtn);

        coBtn = new JButton("<html><center>Rekombinace<br>(tvorba potomků)</center></html>");
        coBtn.setActionCommand("co");
        coBtn.addActionListener(this);
        add(coBtn);

        mBtn = new JButton("<html><center>Mutace<br>potomků</center></html>");
        mBtn.setActionCommand("m");
        mBtn.addActionListener(this);
        add(mBtn);

        npBtn = new JButton("<html><center>Tvorba<br>nové<br>populace</center></html>");
        npBtn.setActionCommand("np");
        npBtn.addActionListener(this);
        add(npBtn);

        niBtn = new JButton("<html><center>Další<br>iterace</center></html>");
        niBtn.setActionCommand("ni");
        niBtn.addActionListener(this);
        add(niBtn);

        exBtn = new JButton("<html><center>Funkční<br>příklad<br>OneMax</center></html>");
        exBtn.setActionCommand("ex");
        exBtn.addActionListener(this);
        add(exBtn);

        activateButtons(ToolBarActiveButton.START);
    }

    public void activateButtons(ToolBarActiveButton selection) {
        switch(selection) {
            case START: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(false);
                cecBtn.setEnabled(false);
                psBtn.setEnabled(false);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case GENERATE: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(false);
                psBtn.setEnabled(false);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case EVALUATE: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(false);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case SOLVED: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(false);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case UNSOLVED: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(true);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case PARENTS: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(true);
                coBtn.setEnabled(true);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case CROSSOVER: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(true);
                coBtn.setEnabled(true);
                mBtn.setEnabled(true);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
            case MUTATE: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(true);
                coBtn.setEnabled(true);
                mBtn.setEnabled(true);
                npBtn.setEnabled(true);
                niBtn.setEnabled(false);
                break;
            }
            case NEW: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(true);
                psBtn.setEnabled(true);
                coBtn.setEnabled(true);
                mBtn.setEnabled(true);
                npBtn.setEnabled(true);
                niBtn.setEnabled(true);
                break;
            }
            case NEXT: {
                ipBtn.setEnabled(true);
                epBtn.setEnabled(true);
                cecBtn.setEnabled(false);
                psBtn.setEnabled(false);
                coBtn.setEnabled(false);
                mBtn.setEnabled(false);
                npBtn.setEnabled(false);
                niBtn.setEnabled(false);
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "ip": {
                Variables.getWorkingPlace().setPanel(PanelType.INITIAL);
                ipBtn.setBorder(redBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "ep": {
                Variables.getWorkingPlace().setPanel(PanelType.EVALUATE);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(redBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "cec": {
                Variables.getWorkingPlace().setPanel(PanelType.CHECK);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(redBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "ps": {
                Variables.getWorkingPlace().setPanel(PanelType.SELECT);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(redBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "co": {
                Variables.getWorkingPlace().setPanel(PanelType.CROSSOVER);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(redBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "m": {
                Variables.getWorkingPlace().setPanel(PanelType.MUTATE);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(redBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "np": {
                Variables.getWorkingPlace().setPanel(PanelType.NEW);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(redBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                break;
            }
            case "ni": {
                Variables.getWorkingPlace().setPanel(PanelType.EVALUATE);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(redBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(oldBorder);
                activateButtons(ToolBarActiveButton.NEXT);
                Variables.setPopulation(Variables.getNewPopulation());
                Variables.getWorkingPlace().getEpPanel().setTablePane(null);
                break;
            }
            case "ex": {
                Variables.getWorkingPlace().setPanel(PanelType.EXAMPLES);
                ipBtn.setBorder(oldBorder);
                epBtn.setBorder(oldBorder);
                cecBtn.setBorder(oldBorder);
                psBtn.setBorder(oldBorder);
                coBtn.setBorder(oldBorder);
                mBtn.setBorder(oldBorder);
                npBtn.setBorder(oldBorder);
                niBtn.setBorder(oldBorder);
                exBtn.setBorder(redBorder);
                activateButtons(ToolBarActiveButton.START);
                Variables.getWorkingPlace().getIpPanel().generateTable(null);
                break;
            }
        }
    }
}
