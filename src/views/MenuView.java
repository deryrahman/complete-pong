package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuView class as view class provided all graphics for main menu
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class MenuView extends JFrame{
    /**
     * horizontal size for main menu
     */
    private int sizeX;

    /**
     * vertical size for main menu
     */
    private int sizeY;

    /**
     * half from horizontal size
     */
    private int halfSizeX;

    /**
     * splitted panel
     */
    private JSplitPane splitPaneH;

    /**
     * draw panel 1
     */
    private DrawCanvas panel1;

    /**
     * draw panel 2
     */
    private DrawCanvas panel2;

    /**
     * player 1 name
     */
    private String namaPlayer1;

    /**
     * player 2 name
     */
    private String namaPlayer2;

    /**
     * bot name
     */
    private String namaPlayerBot;

    //panel2
    /**
     * label header for panel 2 (play with friend)
     */
    private JLabel headerLabelMulti;

    /**
     * text field for player 1 name
     */
    private JTextField playerName1;

    /**
     * text field for player 2 name
     */
    private JTextField playerName2;

    /**
     * label for player 1
     */
    private JLabel playerLabel1;

    /**
     * label for player 2
     */
    private JLabel playerLabel2;

    /**
     * play button
     */
    private JButton playMultiButton;

    /**
     * define label status
     */
    private JLabel statusLabelMulti;

    //panel1
    /**
     * label header for panel 1 (play with bot)
     */
    private JLabel headerLabelBot;

    /**
     * player name
     */
    private JTextField playerName;

    /**
     * player label
     */
    private JLabel playerLabel;

    /**
     * play button
     */
    private JButton playBotButton;

    /**
     * define label status
     */
    private JLabel statusLabelBot;

    /**
     * define windows dimension
     */
    private Dimension windowSize;

    /**
     * MenuView Constructor, initialize all members
     * @param x = horizontal size
     * @param y = vertical size
     */
    public MenuView(int x, int y){
        sizeX = x;
        sizeY = y;
        halfSizeX = sizeX / 2;

        setTitle( "Pong" );
        setBackground( Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeX, sizeY);
        setMinimumSize(new Dimension(sizeX, sizeY));

        windowSize = new Dimension(sizeX, sizeY);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        namaPlayer1 = "";
        namaPlayer2 = "";
        namaPlayerBot = "";

        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );

        // Create the panels
        createPanel1();
        createPanel2();

        panel1.setMinimumSize(new Dimension(halfSizeX, sizeY));
        panel2.setMinimumSize(new Dimension(halfSizeX-12,sizeY));

        splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        topPanel.add(splitPaneH);
        splitPaneH.setLeftComponent( panel1 );
        splitPaneH.setRightComponent( panel2 );

        // Display in the center of the screen
        int xWin = screensize.width/2 - windowSize.width/2;
        int yWin = screensize.height/2 - windowSize.height/2;

        this.setBounds(xWin, yWin, windowSize.width, windowSize.height);
        this.setSize(sizeX,sizeY);
        this.requestFocusInWindow(true);
        this.pack();
    }

    /**
     * set all graphics visible
     */
    public void view() {
        this.setVisible( true );
    }

    /**
     * method for make panel 1
     */
    public void createPanel1(){
        panel1 = new DrawCanvas();
        panel1.setLayout( new GridLayout(4,1) );

        DrawCanvas nameBot = new DrawCanvas();

        DrawCanvas dummy = new DrawCanvas();
        DrawCanvas headerBot = new DrawCanvas();
        headerBot.setLayout(new FlowLayout());
        DrawCanvas statusBot = new DrawCanvas();
        statusBot.setLayout(new FlowLayout());

        headerLabelBot = new JLabel("Play with bot!");
        headerLabelBot.setForeground(Color.WHITE);
        playerName = new JTextField(20);
        playerLabel = new JLabel("Player Name : ");
        playerLabel.setForeground(Color.WHITE);
        playBotButton = new JButton("Play with bot");
        playBotButton.setBackground(Color.BLACK);
        playBotButton.setForeground(Color.WHITE);
        playBotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                namaPlayerBot = playerName.getText();

                if (namaPlayerBot.length() != 0) {
                    String data = "Username " + playerName.getText();
                    statusLabelBot.setText(data);
                    setVisible(false);
                    //System.out.println(data);
                }else {
                    String data = "Please insert player name";
                    statusLabelBot.setText(data);
                }
            }
        });
        statusLabelBot = new JLabel("");
        statusLabelBot.setForeground(Color.WHITE);

        headerBot.add(headerLabelBot);
        nameBot.add(playerLabel);
        nameBot.add(playerName);
        nameBot.add(playBotButton);
        statusBot.add(statusLabelBot);

        panel1.add(dummy);
        panel1.add(headerBot, BorderLayout.NORTH);
        panel1.add(nameBot, BorderLayout.CENTER);
        panel1.add(statusBot, BorderLayout.SOUTH);
    }

    /**
     * method for make panel 2
     */
    public void createPanel2(){
        panel2 = new DrawCanvas();
        panel2.setLayout( new GridLayout(6,1) );

        DrawCanvas namePlayer1 = new DrawCanvas();
        DrawCanvas namePlayer2 = new DrawCanvas();

        DrawCanvas dummy = new DrawCanvas();
        DrawCanvas pnlButton = new DrawCanvas();
        pnlButton.setLayout(new FlowLayout());
        DrawCanvas headerMulti = new DrawCanvas();
        headerMulti.setLayout(new FlowLayout());
        DrawCanvas statusMulti = new DrawCanvas();
        statusMulti.setLayout(new FlowLayout());

        headerLabelMulti = new JLabel("Play with friend!");
        headerLabelMulti.setForeground(Color.WHITE);

        playerName1 = new JTextField(20);
        playerLabel1 = new JLabel("Player 1 : ");
        playerLabel1.setForeground(Color.WHITE);

        playerName2 = new JTextField(20);
        playerLabel2 = new JLabel("Player 2 : ");
        playerLabel2.setForeground(Color.WHITE);

        playMultiButton = new JButton("Play with friend");
        playMultiButton.setBackground(Color.BLACK);
        playMultiButton.setForeground(Color.WHITE);
        playMultiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                namaPlayer1 = playerName1.getText();
                namaPlayer2 = playerName2.getText();

                if (namaPlayer1.length() != 0 && namaPlayer2.length() != 0) {
                    String data = playerName1.getText();
                    data += " Vs " + playerName2.getText();
                    statusLabelMulti.setText(data);
                    setVisible(false);
                }else {
                    String data = "Please insert player name";
                    statusLabelMulti.setText(data);
                }
            }
        });
        statusLabelMulti = new JLabel("");
        statusLabelMulti.setForeground(Color.WHITE);

        headerMulti.add(headerLabelMulti);
        namePlayer1.add(playerLabel1);
        namePlayer1.add(playerName1);
        namePlayer2.add(playerLabel2);
        namePlayer2.add(playerName2);
        pnlButton.add(playMultiButton);
        statusMulti.add(statusLabelMulti);

        panel2.add(dummy);
        panel2.add(headerMulti, BorderLayout.NORTH);
        panel2.add(namePlayer1, BorderLayout.CENTER);
        panel2.add(namePlayer2, BorderLayout.CENTER);
        panel2.add(pnlButton, BorderLayout.CENTER);
        panel2.add(statusMulti, BorderLayout.SOUTH);
    }

    /**
     * DrawCanvas as special class to extends JPanel
     * use to paint component on Graphics
     */
    class DrawCanvas extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }
    }

    /**
     * draw all aspects of Main Menu
     * @param g = graphic source
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,sizeX, sizeY);
        g.drawRect(0,0,sizeX, sizeY);
    }

    /**
     * get player 1 name
     * @return player 1 name
     */
    public String getPlayer1() {
        return namaPlayer1;
    }

    /**
     * get player 2 name
     * @return  player 2 name
     */
    public String getPlayer2() {
        return namaPlayer2;
    }

    /**
     * get bot name
     * @return bot name
     */
    public String getPlayerBot() {
        return namaPlayerBot;
    }

    /**
     * Start main menu. First appear on program
     * @param args = arguments
     */
    public static void main( String args[] ){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception evt) {}
        // Create an instance of the test application
        //MenuView mainFrame = new MenuView(1200,400);
        //mainFrame.view();
        //System.out.println(mainFrame.getPlayer1());
        //System.out.println("selesai");
    }

}
