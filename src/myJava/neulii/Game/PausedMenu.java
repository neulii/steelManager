package myJava.neulii.Game;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PausedMenu extends GameMenu {

    public PausedMenu(GameWindow gameWindow){
        super(gameWindow);
    }

    @Override
    public void clickedAt(Point p) {

        for(MenuButton button: menuButtons){
            if(button.isPointInside(p)){

                switch (button.getButtonText()){

                    case "zurück zum Spiel":
                        gw.setGameState(GameState.MAINGAME);
                        break;
                    case "Spiel speichern":

                        String filename = "savegame.txt";

                        FileOutputStream fos = null;
                        ObjectOutputStream out = null;


                        break;

                    case "Spiel beenden":
                        System.exit(0);
                        break;


                }
            }

        }
    }
}
