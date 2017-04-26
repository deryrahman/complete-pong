import controllers.CenterAreaController;
import controllers.GameController;
import controllers.PlayerController;
import views.GameView;
import views.MenuView;

public class PlayPong {

    public static void main(String[] args){

        // activate openGL on linux
        System.setProperty("sun.java2d.opengl", "true");
        MenuView mainFrame = new MenuView(1200,400);
        mainFrame.view();

        while(true) {
            if (!mainFrame.isVisible()) {
                String namaPlayer1 = mainFrame.getPlayer1();
                String namaPlayer2 = mainFrame.getPlayer2();
                String namaPlayerBot = mainFrame.getPlayerBot();

                if (namaPlayerBot.length() != 0){
                    namaPlayer1 = "Bot";
                    namaPlayer2 = namaPlayerBot;
                    System.out.println(namaPlayer1);
                    System.out.println(namaPlayer2);
                    System.out.println(namaPlayerBot);
                }

                GameView gameView = new GameView(1200, 400, namaPlayer1, namaPlayer2);
                GameController gameController = new GameController(gameView);
                CenterAreaController brickAreaController = new CenterAreaController(gameView);
                PlayerController player1 = new PlayerController(gameView, 0);
                PlayerController player2 = new PlayerController(gameView, 1);
                brickAreaController.start();
                player1.start();
                player2.start();
                gameController.start();
                gameView.play();
                System.out.println("2");
                break;
            }
            System.out.println("1");
        }
    }
}
