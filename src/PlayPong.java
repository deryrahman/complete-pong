import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import models.Player;
import views.GameView;
import views.MenuView;

import java.awt.*;

public class PlayPong {
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");

//        MenuView mainFrame = new MenuView(1200,400);
//        mainFrame.view();

        GameView gameView = new GameView(1200,400,"PlayerOne","PlayerTwo");
        GameController gameController = new GameController(gameView);
        CenterAreaController brickAreaController = new CenterAreaController(gameView);
        PlayerController player1 = new PlayerController(gameView,0);
        PlayerController player2 = new PlayerController(gameView,1);
        brickAreaController.start();
        player1.start();
        player2.start();
        gameController.start();
        gameView.play();

        // Assertion check
        assertionCheck(gameView);
    }

    private static void assertionCheck(GameView gameView) {
        assert (gameView.getBall().getSpeed()<10);
        assert (gameView.getBall().getRadius()<7);

        for(Player player:gameView.getPlayers()) {
            assert (player.getPlayerName() != null);
            assert (player.getScores() >= 0 );
            assert (player.getPaddle().getLength() < gameView.getCanvasHeight());
        }

        assert (gameView.getCanvasWidth()<Toolkit.getDefaultToolkit().getScreenSize().width);
        assert (gameView.getCanvasHeight()<Toolkit.getDefaultToolkit().getScreenSize().height);
        assert (gameView.getCenterArea().getWidth()<gameView.getWidth());
        assert (gameView.getCenterArea().getHeight()<gameView.getHeight());
    }
}
