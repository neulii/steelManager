package myJava.neulii.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameMenu implements GameObject{

    private BufferedImage backgroundImage;
    private GameWindow gw;

    private int buttonWidth = 180;
    private int buttonHeight = 60;
    private int buttonDistance = 30;

    private ArrayList<MenuButton> menuButtons;

    private int leftMenu;
    private int topMenu = 150;

    public GameMenu(GameWindow gameWindow){

        backgroundImage = ImageLoader.loadImage("/steel_background.jpg");

        this.gw = gameWindow;
        leftMenu = (gw.getWidth()-buttonWidth) /2;
        menuButtons = new ArrayList<>();
    }

    @Override
    public void update(long dT) {

        leftMenu = (gw.getWidth()-buttonWidth) /2;

        for (MenuButton button: menuButtons) {
            button.setX(leftMenu);
            button.setMousePos(gw.getMousePos());
            button.update(dT);
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(backgroundImage,0,0,gw.getWidth(),gw.getHeight(),null);


        for (MenuButton button : menuButtons) {
            button.render(g);
        }
    }

    public void addMenuButton(String buttonText){
        MenuButton tempButton = new MenuButton(leftMenu,topMenu+(menuButtons.size()*(buttonHeight + buttonDistance)),buttonWidth,buttonHeight,buttonText);

        menuButtons.add(tempButton);
    }

    public void clickedAt(Point p){

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