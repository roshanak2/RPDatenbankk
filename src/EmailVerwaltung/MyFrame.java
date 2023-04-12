package EmailVerwaltung;

import javax.swing.*;
import java.sql.SQLException;

public class MyFrame extends JFrame {
    MyFrame(){
        setVisible(true);
        JFrameEmailverwaltung hw = null;
        try {
            hw = new JFrameEmailverwaltung();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setContentPane(hw.getPanel());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}