import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PanelTop extends JPanel {
    private Model model;

    // Muutujad
    private JPanel pnlComponents = new JPanel(new GridBagLayout()); // See paneel sisaldab JButton,JLabel,JTextfield
    private GridBagConstraints gbc = new GridBagConstraints();

    // Kompondendid mida mujalt vaja kätte saada
    private JLabel lblName, lblAge;
    private JTextField txtName, txtAge;
    private JButton btnAdd, btnShow;

    // TopPanel konstrukor
    public PanelTop(Model model) {
        this.model = model;
        setupPanel();
        setupConstraint();
        setupLineFirst();
        setupLineTwo();
        setupLineThree();

        this.add(pnlComponents);
    }

    private void setupPanel() {
        setBackground(Color.YELLOW);
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    private void setupConstraint() {
        gbc.anchor = GridBagConstraints.WEST; // Lahtri sisu joondatakse vasakule
        gbc.insets = new Insets(2, 2, 2, 2); // Jätab 2px vahet
        gbc.fill = GridBagConstraints.BOTH; // Venitab lahtris oleva komponendi laiaks
    }

    private void setupLineFirst() {
        JLabel lblName = new JLabel("Nimi:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlComponents.add(lblName, gbc);

        txtName = new JTextField("", 14);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlComponents.add(txtName, gbc);

    }

    private void setupLineTwo() {
        JLabel lblAge = new JLabel("Vanus:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlComponents.add(lblAge, gbc);

        txtAge = new JTextField("", 14);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlComponents.add(txtAge, gbc);
    }

    private void setupLineThree() {
        JButton btnAdd = new JButton("Lisa andmed");
        gbc.gridx = 0;
        gbc.gridy = 2;
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Klikiti andmete nupul");
                String txtName = getTxtName().getText();
                int txtAge = Integer.parseInt(getTxtAge().getText());
                //System.out.println("Nimi: " + txtName);
                //System.out.println("Vanus: " + txtAge);
                if (!txtName.isEmpty() && txtName.length() > 1 && txtName.length() < 30 && txtAge > 0 && txtAge < 110) {
                    writeFileToFile();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Valesti sisestatud info!" + "\nMin. 2 tähte nimes!" + "\nMax 110 vanus!");
                }
            }
        });
        pnlComponents.add(btnAdd, gbc);


        JButton btnShow = new JButton("Tabel");
        gbc.gridx = 1;
        gbc.gridy = 2;
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Klikiti tabeli nupul");
                try {
                    if (model.readFromFile()) {
                        JDialog dialog = new JDialog();
                        model.addToTable(dialog);
                        dialog.setModal(true);
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sisu puudub");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pnlComponents.add(btnShow, gbc);
    }

    private void clearForm() {
        getTxtName().setText(null);
        getTxtAge().setText(null);
    }

    public void writeFileToFile() {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(model.getFileName(), true))) {
            String line = getTxtName().getText() + ";" + getTxtAge().getText();
            fw.write(line);
            fw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JLabel getLblName() {
        return lblName;
    }

    public JLabel getLblAge() {
        return lblAge;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtAge() {
        return txtAge;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnShow() {
        return btnShow;
    }
}