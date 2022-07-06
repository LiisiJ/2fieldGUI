public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View(model);


        view.pack();
        view.setLocationRelativeTo(null); // JFrame keset ekraani
        view.setVisible(true);
    }

}
