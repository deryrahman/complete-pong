import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import views.GameView;

public class PlayPong {
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");

        GameView gameView = new GameView(1200,400);
        GameController gameController = new GameController(gameView);
        CenterAreaController brickAreaController = new CenterAreaController(gameView);
        PlayerController player1 = new PlayerController(gameView,0);
        PlayerController player2 = new PlayerController(gameView,1);
        brickAreaController.start();
        player1.start();
        player2.start();
        gameController.start();
    }
}
