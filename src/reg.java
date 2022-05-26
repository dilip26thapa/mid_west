import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class reg {
    private JPanel main;
    private JButton ADDButton;
    private JButton EDITButton;
    private JTextField txtname;
    private JTextField txtmobile;
    private JTextField txtcourse;
    private JButton DELETEButton;
    private JTable table1;

    public reg() {

        ADDButton.addActionListener(new ActionListener() {
                Connection con1;
            PreparedStatement insert;
            public void actionPerformed(ActionEvent e) {
                String name = txtname.getText();
                String mobile = txtmobile.getText();
                String course = txtcourse.getText();


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/mid_west","root","");
                    insert = con1.prepareStatement("insert into record(name, mobile,course) values(?,?,?)");
                    insert.setString(1,name);
                    insert.setString(2,mobile);
                    insert.setString(3,course);
                    insert.executeUpdate();

//                    JOptionPane.showMessageDialog(this,"Record Added");
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                catch (SQLException ex){
                    Logger.getLogger(reg.class.getName()).log(Level.SEVERE,null,ex);
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("reg");
        frame.setContentPane(new reg().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
