import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Model model;
    private PanelTop panelTop;


    public View(Model model) {
        // Konstruktor
        this.model = model;
        setupFrame();
        setupPanels();
        addPanelsToFrame();
    }
    // Põhiaken
    private void setupFrame() {
        this.setTitle("2 field GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400,400));
    }
    // Kaks paneeli
    private void setupPanels() {
        panelTop = new PanelTop(model);

    }
    // Kaks paneeli JFrame peale
    private void addPanelsToFrame() {
        this.add(panelTop, BorderLayout.NORTH);

    }

    // GETTERID
    public JLabel getLblName() {
        return panelTop.getLblName();
    }

    public JLabel getLblAge() {
        return panelTop.getLblAge();
    }

    public JTextField getTxtName() {
        return panelTop.getTxtName();
    }

    public JTextField getTxtAge() {
        return panelTop.getTxtAge();
    }

    public JButton getBtnAdd() {
        return panelTop.getBtnAdd();
    }

    public JButton getBtnShow() {
        return panelTop.getBtnShow();
    }


}
