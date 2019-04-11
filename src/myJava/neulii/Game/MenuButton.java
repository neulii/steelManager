package myJava.neulii.Game;

import java.awt.*;

public class MenuButton implements GameObject {

    private int x;
    private int y;
    private int width;
    private int height;

    private Point mousePos;

    private boolean isHoovered;

    private Color normalTextColor;
    private Color normalBackgroundColor;

    private Color textHooverColor;
    private Color backgroundHooverColor;

    private Color drawingBackgroundColor;
    private Color drawingTextColor;

    private String buttonText;


    public MenuButton(int x, int y, int width, int height,String buttonText){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonText = buttonText;

        this.normalBackgroundColor = Color.gray;
        this.normalTextColor = Color.black;

        this.backgroundHooverColor = Color.ORANGE;
        this.textHooverColor = Color.white;


        this.mousePos = new Point(0,0);

        this.drawingBackgroundColor = normalBackgroundColor;
        this.drawingTextColor = normalTextColor;

    }


    @Override
    public void render(Graphics g) {
        g.setColor(drawingBackgroundColor);
        g.fillRect(x,y,width,height);

        FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int textWidth = fontMetrics.stringWidth(buttonText);
        int textHeight = fontMetrics.getHeight();

        int xString = x+ (width-textWidth)/2;
        int yString = y +(height - textHeight)/2 + fontMetrics.getAscent();

        g.setColor(drawingTextColor);
        g.drawString(buttonText,xString,yString);

    }

    @Override
    public void update(long dT) {

        //System.out.println(mousePos);
        if (isPointInside(mousePos)){
            if(!isHoovered){
                mouseEnter();
            }
        }

        if (!isPointInside(mousePos)){
            if(isHoovered) {
                mouseLeave();
            }
        }
    }

    public void setBackgroundColor(Color color){
        normalBackgroundColor = color;
    }

    public void setTextColor(Color color){
        normalTextColor = color;
    }

    public void mouseEnter(){
        isHoovered = true;
        drawingTextColor = textHooverColor;
        drawingBackgroundColor = backgroundHooverColor;
    }

    public void mouseLeave(){
        isHoovered=false;

        drawingTextColor = normalTextColor;
        drawingBackgroundColor = normalBackgroundColor;

    }

    public boolean isPointInside(Point p){
        Rectangle rec = new Rectangle(x,y,width,height);
        return rec.intersects(p.getX(),p.getY(),1,1);
    }

    public void setMousePos(Point p){

        if(p!=null) {
            this.mousePos = p;
        }
    }

    public String getButtonText(){
        return buttonText;
    }

    public void setX(int x){
        this.x = x;
    }
}
