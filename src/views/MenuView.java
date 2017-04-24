package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by irfan on 24/04/17.
 */
public class MenuView extends JFrame{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public MenuView(){
        prepareGUI();
    }
    public static void main(String[] args){
        MenuView  swingControlDemo = new MenuView();
        swingControlDemo.showTextFieldDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(800,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        //mainFrame.setForeground(Color.BLACK);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        //controlPanel.setLayout(new FlowLayout());
        
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private void showTextFieldDemo(){
        headerLabel.setText("Pong!");

        JLabel  player1Label = new JLabel("Player 1 : ");
        JLabel  player2Label = new JLabel("Player 2 : ");

        JTextField player1Name = new JTextField(6);
        JTextField player2Name = new JTextField(6);

        JButton loginButton = new JButton("Play");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty()) {
                    String data = "Player 1 : " + player1Name.getText();
                    data += " Vs Player 2 : " + player2Name.getText();
                    statusLabel.setText(data);
                }else{
                    String data = "Please enter player name";
                    statusLabel.setText(data);
                }
            }
        });

        controlPanel.add(player1Label);
        controlPanel.add(player1Name);

        controlPanel.add(loginButton);

        controlPanel.add(player2Label);
        controlPanel.add(player2Name);

        mainFrame.setVisible(true);
    }

}
