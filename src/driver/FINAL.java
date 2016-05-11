package driver;

import gui.PanelContainer;
import javax.swing.*;

/**
 * Created by Brian on 4/20/2016.
 */
public class FINAL
{
    public static void main(String[] args)
    {
        JFrame frmApp = new JFrame();
        frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelContainer pnlApp = new PanelContainer();
        frmApp.add(pnlApp);

        frmApp.setSize(500, 500);
        frmApp.setVisible(true);
    }
}
