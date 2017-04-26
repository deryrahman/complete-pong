import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import views.GameView;
import views.MenuView;

public class Pong {
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");

        MenuView mainFrame = new MenuView(1200,400);
        mainFrame.view();

        GameView gameView = new GameView(1200,400);
        GameController gameController = new GameController(gameView);
        CenterAreaController brickAreaController = new CenterAreaController(gameView);
        PlayerController player1 = new PlayerController(gameView,0);
        PlayerController player2 = new PlayerController(gameView,1);
        brickAreaController.start();
        player1.start();
        player2.start();
        gameController.start();
        gameView.play();
    }
}
