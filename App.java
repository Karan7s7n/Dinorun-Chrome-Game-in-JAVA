import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int BW=750;
        int BH=250;

        JFrame frame= new JFrame("DINORUN");
        frame.setVisible(true);
        frame.setSize(BW,BH);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dinorun dinorun = new Dinorun();
        frame.add(dinorun);
        frame.pack();
        dinorun.requestFocus();
        frame.setVisible(true);



    }
}
