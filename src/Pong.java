import controllers.GameController;
import views.GameView;

public class Pong {
    public static void main(String[] args){
        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");
//        GameView gameView = new GameView(800,400);
        GameController gameController = new GameController(800,400);
        gameController.run();
    }
}
