package myJava.neulii.Game;

import javafx.scene.paint.Material;

import java.awt.*;

public class TitleMenu extends GameMenu{

    public TitleMenu(GameWindow gameWindow){
        super(gameWindow);
    }

    @Override
    public void clickedAt(Point p) {
        for(MenuButton button: menuButtons){
            if(button.isPointInside(p)){

                switch (button.getButtonText()){

                    case "Neues Spiel":

                        gw.startNewGame();
                        gw.setGameState(GameState.MAINGAME);

                        break;

                    case "Spiel Laden":

                        MapStringGenerator generator = new MapStringGenerator(20,20);

                        gw.loadGame(new MaterialManager(),generator.getMapString());



                        break;

                    case "Spiel beenden":

                        System.exit(0);

                        break;
                }
            }

        }
    }
}
