import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import models.Player;
import views.GameView;
import views.MenuView;

import java.awt.*;

/**
 * PlayPong Class as main Class for this project
 * Use all controllers and view to run the game with multithreading
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class PlayPong {

    /**
     * provide view when playing the game
     */
    private static GameView gameView;

    /**
     * provide thread control for game
     */
    private static GameController gameController;

    /**
     * provide thread control on spawning objects on centerarea
     */
    private static CenterAreaController centerAreaController;

    /**
     * player1 name
     */
    private static String namaPlayer1;

    /**
     * player2 name
     */
    private static String namaPlayer2;

    /**
     * bot name
     */
    private static String  namaPlayerBot;

    /**
     * main function, run the game with multithreading
     * @param args = arguments
     */
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");
        MenuView mainFrame = new MenuView(1200,400);
        mainFrame.view();

        // Wait until mainFrame not visible
        while(mainFrame.isVisible()) {
            System.out.println("Waiting player name");
        }

        // get names for all players
        namaPlayer1 = mainFrame.getPlayer1();
        namaPlayer2 = mainFrame.getPlayer2();
        namaPlayerBot = mainFrame.getPlayerBot();

        // handle bot name
        if (namaPlayerBot.length() != 0){
            namaPlayer1 = "Bot";
            namaPlayer2 = namaPlayerBot;
        }

        // initialize all controllers and views then start game
        System.out.println("Game Started!");
        gameView = new GameView(1200, 400, namaPlayer1, namaPlayer2);
        gameController = new GameController(gameView);
        centerAreaController = new CenterAreaController(gameView);
        PlayerController player1 = new PlayerController(gameView, 0);
        PlayerController player2 = new PlayerController(gameView, 1);

        // play view and start all thread
        centerAreaController.start();
        player1.start();
        player2.start();
        gameController.start();
        gameView.play();

        // Assertion check
        assertionCheck(gameView);
    }

    /**
     * method for asserting initial state and final state. Keeping
     * game on the right way
     * @param gameView = source for asserting
     */
    private static void assertionCheck(GameView gameView) {
        assert (gameView.getBall().getSpeed()<10);
        assert (gameView.getBall().getRadius()<7);

        for(Player player:gameView.getPlayers()) {
            assert (player.getPlayerName() != null);
            assert (player.getScores() >= 0 );
            assert (player.getPaddle().getLength() < gameView.getCanvasHeight());
        }

        assert (gameView.getCanvasWidth()< Toolkit.getDefaultToolkit().getScreenSize().width);
        assert (gameView.getCanvasHeight()<Toolkit.getDefaultToolkit().getScreenSize().height);
        assert (gameView.getCenterArea().getWidth()<gameView.getWidth());
        assert (gameView.getCenterArea().getHeight()<gameView.getHeight());
    }
}
