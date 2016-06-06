/*
    Autor: Radim KUBIŠ, xkubis03 © 2014
*/

package cz.vutbr.fit.xkubis03.sfc.ga.gui;

import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.InitialPopulationPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.CheckEndConditionPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.NextPopulationPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.EvaluatePopulationPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.CrossoverPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.ExamplesPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.ParentSelectionPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.MutationPanel;
import cz.vutbr.fit.xkubis03.sfc.ga.gui.panel.PanelType;
import cz.vutbr.fit.xkubis03.sfc.ga.util.Variables;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class WorkingPlace extends JPanel {

    protected InitialPopulationPanel ipPanel;
    protected EvaluatePopulationPanel epPanel;
    protected CheckEndConditionPanel cecPanel;
    protected ParentSelectionPanel psPanel;
    protected CrossoverPanel coPanel;
    protected MutationPanel mPanel;
    protected NextPopulationPanel npPanel;
    protected ExamplesPanel exPanel;

    protected JPanel actual;

    protected ToolBar toolBar;

    public WorkingPlace(ToolBar tb) {
        super();

        this.toolBar = tb;

        setLayout(new BorderLayout());

        ipPanel = new InitialPopulationPanel();
        epPanel = new EvaluatePopulationPanel();
        cecPanel = new CheckEndConditionPanel();
        psPanel = new ParentSelectionPanel();
        coPanel = new CrossoverPanel();
        mPanel = new MutationPanel();
        npPanel = new NextPopulationPanel();
        exPanel = new ExamplesPanel();

        add(ipPanel);
        actual = ipPanel;
    }

    public void setPanel(PanelType pt) {
        remove(actual);

        switch(pt) {
            case INITIAL: {
                actual = ipPanel;
                break;
            }
            case EVALUATE: {
                actual = epPanel;
                break;
            }
            case CHECK: {
                actual = cecPanel;
                break;
            }
            case SELECT: {
                actual = psPanel;
                break;
            }
            case CROSSOVER: {
                actual = coPanel;
                break;
            }
            case MUTATE: {
                actual = mPanel;
                break;
            }
            case NEW: {
                actual = npPanel;
                break;
            }
            case EXAMPLES: {
                actual = exPanel;
                break;
            }
            default: {
                throw new AssertionError(pt.name());
            }
        }

        add(actual);
        Variables.setActualScreen(actual);
        revalidate();
        repaint();
    }

    public InitialPopulationPanel getIpPanel() {
        return ipPanel;
    }

    public EvaluatePopulationPanel getEpPanel() {
        return epPanel;
    }

    public CheckEndConditionPanel getCecPanel() {
        return cecPanel;
    }

    public ParentSelectionPanel getPsPanel() {
        return psPanel;
    }

    public CrossoverPanel getCoPanel() {
        return coPanel;
    }

    public MutationPanel getMPanel() {
        return mPanel;
    }

    public NextPopulationPanel getNpPanel() {
        return npPanel;
    }

    public ExamplesPanel getExPanel() {
        return exPanel;
    }
}
