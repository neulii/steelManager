package myJava.neulii.Game;

import java.awt.*;

public class MenuButton implements GameObject {

    private Rectangle buttonRect;
    private int x;
    private int y;
    private int width;
    private int height;

    private Color textColor;
    private Color backgroundColor;

    private String buttonText;


    public MenuButton(int x, int y, int width, int height,String buttonText){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonText = buttonText;
        this.backgroundColor = Color.gray;
        this.textColor = Color.black;
    }


    @Override
    public void render(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(x,y,width,height);

        FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int textWidth = fontMetrics.stringWidth(buttonText);
        int textHeight = fontMetrics.getHeight();

        int xString = x+ (width-textWidth)/2;
        int yString = y +(height - textHeight)/2 + fontMetrics.getAscent();

        g.setColor(textColor);
        g.drawString(buttonText,xString,yString);

    }

    @Override
    public void update(long dT) {

    }

    public void setBackgroundColor(Color color){
        backgroundColor = color;
    }

    public void setTextColor(Color color){
        textColor = color;
    }
}
