package views;

import java.awt.Container;
import javax.swing.*;
import java.awt.GridLayout;

public class ATMGUI extends JFrame {
    Container panelContenido;
    public static void main (String[] args) {
        //Instanciar la GUI
        ATMGUI objMain = new ATMGUI();

        //Inicializar componentes
        objMain.inicializarFrame("UTM services");
        //Añadir Componentes

        //Mostrar ventana
        objMain.setLocationRelativeTo(null);
        objMain.setVisible(true);
    }

    /**
     *
     * @param title The title of the window
     */
    public void inicializarFrame(String title) {
        this.setTitle(title);
        panelContenido = this.getContentPane();
        panelContenido.setLayout(new GridLayout(4, 1, 10, 10));

        JButton jButtonWithdraw = new JButton("Withdraw");
        JButton jButtonDeposit = new JButton("Deposit");
        JButton jButtonTransfer = new JButton("Transfer");
        JButton jButtonQuit = new JButton("Quit");

        panelContenido.add(jButtonWithdraw);
        panelContenido.add(jButtonDeposit);
        panelContenido.add(jButtonTransfer);
        panelContenido.add(jButtonQuit);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
    }
}
