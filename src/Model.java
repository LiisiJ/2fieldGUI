import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model extends JPanel{

    private JDialog dialog;
    private View view;
    private List<DatabaseData> databaseData;

    private String fileName = "info.txt";

    private DefaultTableModel dtm = new DefaultTableModel();
    private JTextField txtAge, txtName;

    public Model(){
        databaseData = new ArrayList<>();
        this.view = view;


    }

    public boolean readFromFile() throws IOException {
        File f = new File(fileName);
        databaseData.clear();
        if (f.exists()) {
            if (f.length() > 0 ) {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                    for (String line; (line = br.readLine()) != null;){
                        String name = line.split(";")[0];
                        int age = Integer.parseInt(line.split(";")[1]);
                        databaseData.add(new DatabaseData(name, age));
                    }
                    return true;

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                f.createNewFile();
            }
        }
        return false;
    }

    public void addToTable( JDialog dialog){
        String[] header = new String[]{"Nimi", "Vanus"};
        String[][] content = new String[databaseData.size()][2];
        for (int x =0; x < getDatabaseData().size(); x++){
            content[x][0] = getDatabaseData().get(x).getTxtName();
            content[x][1] = String.valueOf(getDatabaseData().get(x).getTxtAge());

        }

        JTable table = new JTable(content, header);
        dialog.add(new JScrollPane(table));
        dialog.setTitle("Info tabel");

    }






    //______________Setter_____________//


    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public void setFileData(List<DatabaseData> databaseData) {
        this.databaseData = databaseData;
    }

    public List<DatabaseData> getDatabaseData() {
        return databaseData;
    }

    public String getFileName() {
        return fileName;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }
    public JTextField getTxtName() {
        return txtName;
    }
    public JTextField getTxtAge() {
        return txtAge;
    }


}
