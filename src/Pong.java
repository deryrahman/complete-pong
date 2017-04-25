import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import views.GameView;

/**
 * Pong class as main Class for all CompletePong project.
 * Loads all controllers and views class
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Pong {
    /**
     * the main program from CompletePong project
     * @param args
     */
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");

        // initialize controllers and views
        GameView gameView = new GameView(1200,400);
        GameController gameController = new GameController(gameView);
        CenterAreaController brickAreaController = new CenterAreaController(gameView);
        PlayerController player1 = new PlayerController(gameView,0);
        PlayerController player2 = new PlayerController(gameView,1);

        // starting thread
        brickAreaController.start();
        player1.start();
        player2.start();
        gameController.start();
    }
}
