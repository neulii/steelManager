package myJava.neulii.Game;

import myJava.neulii.Lib.Utils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PausedMenu extends GameMenu {

    public PausedMenu(GameWindow gameWindow){
        super(gameWindow);
    }

    @Override
    public void clickedAt(Point p) {

        for(MenuButton button: menuButtons){
            if(button.isPointInside(p)){
                //System.out.println(button.getButtonText());

                switch (button.getButtonText()){

                    case "zurück zum Spiel":

                        gw.setGameState(GameState.MAINGAME);
                         break;

                    case "Spiel speichern":

                        System.out.println("speichern");

                        ArrayList<Serializable> savingObjects = new ArrayList<>();

                        savingObjects.add(gw.getMap().getMapString());
                        savingObjects.add(gw.getMaterialManager());


                        //Utils.printArrayToConsole(gw.getMap().getMapString());

                        String filename = "savegame.txt";
//
                        FileOutputStream fos = null;
                        ObjectOutputStream out = null;


                        try {
                            fos = new FileOutputStream(filename);
                            out = new ObjectOutputStream(fos);


                            out.writeObject(savingObjects);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        break;

                    case "Spiel laden":
                        System.out.println("laden");
                        gw.loadGame();
                        break;

                    case "Spiel beenden":

                        System.exit(0);
                        break;


                }
            }

        }
    }
}
