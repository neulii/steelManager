package myJava.neulii.Game;

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
                        gw.setGameState(GameState.MAINGAME);

                        break;

                    case "Spiel Laden":

                        break;

                    case "Spiel beenden":

                        System.exit(0);

                        break;
                }
            }

        }
    }
}
