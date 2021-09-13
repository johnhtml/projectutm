package views;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.text.NumberFormatter;

import controller.Controller;
import models.User;

public class LoginView extends JFrame implements ActionListener {

    private Container panelContenido;

    private JLabel jLabelUser;
    private JLabel jLabelPin;

    private JFormattedTextField jNumberID;
    private JPasswordField jNumberPin;
    private JButton jButtonLogin;

    private NumberFormat format;
    private NumberFormatter formatterID;

    public static void main(String[] args) {
        // start the login GUI
        LoginView objMain = new LoginView();

        // Intanciate the controller


        // Inicializar componentes
        objMain.inicializarFrame("Este es el título");

        // Añadir Componentes
        Controller controller = new Controller();

        // Show the window
        objMain.setLocationRelativeTo(null);
        objMain.setVisible(true);
    }

    /**
     *
     * @param title The title of the window
     */
    public void inicializarFrame(String title) {
        this.setTitle(title);

        format = NumberFormat.getInstance();
        formatterID = new NumberFormatter(format);
        formatterID.setValueClass(Integer.class);
        formatterID.setMinimum(0);
        formatterID.setMaximum(999999);
        formatterID.setAllowsInvalid(false);

        // If the value is needed to be committed on each keystroke instead of focus lost
        // formatter.setCommitsOnValidEdit(true);
        jNumberID = new JFormattedTextField(formatterID);
        jNumberPin = new JPasswordField();
        jButtonLogin = new JButton("Login");

        // add the action
        jButtonLogin.addActionListener(this);

        jLabelUser = new JLabel("Enter the user ID: ");
        jLabelPin = new JLabel("Enter the pin associated: ");

        panelContenido = this.getContentPane();
        panelContenido.setLayout(new GridLayout(5, 1, 10, 10));
        panelContenido.add(jLabelUser);
        panelContenido.add(jNumberID);
        panelContenido.add(jLabelPin);
        panelContenido.add(jNumberPin);
        panelContenido.add(jButtonLogin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Login attemp");

        // try to get the user object corresponding to the ID and pin combo
        String userId = jNumberID.getText();
        String pin = String.valueOf(jNumberPin.getPassword());

        User authuser = Controller.login(userId, pin);
    }
}
