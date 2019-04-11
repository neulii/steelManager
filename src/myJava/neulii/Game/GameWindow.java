package myJava.neulii.Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameWindow extends Canvas implements GameObject{

	private GameMenu gameMenu;
	private GameState gameState;

	private static final long serialVersionUID = 1L;
	
	private final int MAPWIDTH = 300;		//mapwidth in tiles
	private final int MAPHEIGHT = 300;		//mapheight in tiles
	
	private final int TILE_WIDTH = 50;
	private final int TILE_HEIGHT = 50;

	private int windowHeight;
	private int windowWidth;
	
	private boolean gameIsRunning;
	
	private boolean isMouseOverMenu = false;
	
	private int actualFrames = 0;
	
	private Dimension windowSize;
	private JFrame gameWindow;
	private BufferStrategy bs;
	private Graphics graphics;
	
	private MaterialManager mm;
	
	private Point mousePosition;
	
	private InputListener inputListener;
	
	private int[] mapString;
	
	private Map gameMap;
	private GUI gui;

	public GameWindow(int width, int height) {


		windowWidth = 20 * TILE_WIDTH;
		windowHeight =15 * TILE_HEIGHT;

		windowSize = new Dimension(width, height);

		initializeObjects();
		initializeWindow();

		gameMenu = new GameMenu(this);
		gameMenu.addMenuButton("Neues Spiel");
		gameMenu.addMenuButton("Spiel Laden");
		gameMenu.addMenuButton("Spiel beenden");


//		gameState = GameState.MAINGAME;
		gameState = GameState.TITLE_MENU;


		gameWindow.setVisible(true);
		gameWindow.setResizable(true);
		gameIsRunning = true;
		
		gameWindow.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				getGameWindow().setSize(gameWindow.getSize());				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {				
			}
		});
	
		//Game Loop
		final int FPS = 60;
		
		long startTime = System.nanoTime();
		long deltaTime = 0;
		long frameCounterTime;
		double timePerFrame = 1000000000.0/FPS;
		
		int frames = 0;
		frameCounterTime = System.currentTimeMillis();
		
		while(gameIsRunning) {
			deltaTime = System.nanoTime()-startTime;
						
			if(deltaTime>=timePerFrame) {
				
				//update Methode
				update(deltaTime);
				
				frames++;
				deltaTime = 0;
				startTime = System.nanoTime();	
			}
		
			//Rendermethode
			render(graphics);
			
			//Frame Counter
			if(System.currentTimeMillis()-frameCounterTime>=1000) {
//				System.out.println(frames);
				actualFrames = frames;
				frames = 0;
				frameCounterTime = System.currentTimeMillis();
			}
		}
	}

	// Render Method
	public void render(Graphics g) {
		
		bs = getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();	       
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		//==================    Add Drawing Stuff here    =====================


		//painting je nach gamestate

		switch (gameState){
			case TITLE_MENU:

				gameMenu.render(g);
				break;

			case MAINGAME:

				gameMap.render(g);
				gui.render(g);

				break;

			case PAUSED:
				break;
		}


	
		//==================     End of drawing zone      ===================== 
		
		g.dispose();
		bs.show();
	}
	
	//Update Method
	public void update(long dT) {

		switch (gameState){
			case TITLE_MENU:

				gameMenu.update(dT);


				break;

			case MAINGAME:

				gui.update(dT);
				gameMap.update(dT);

				break;

			case PAUSED:
				break;
		}
	}
	
	//initialize Window
	public void initializeWindow() {
		gameWindow = new JFrame("Game");
		gameWindow.setSize(windowWidth, windowHeight);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
		this.setFocusable(true);

		
		gameWindow.add(this);		
		gameWindow.pack();
		inputListener = new InputListener(this,gui);
		this.addMouseMotionListener(inputListener);
		this.addMouseListener(inputListener);
		this.addKeyListener(inputListener);
		
		gameWindow.setLocationRelativeTo(null);
	}
	
	public void initializeObjects() {
		mm = new MaterialManager();

		long startTime = System.currentTimeMillis();
		long timeElapsed = 0;

		MapStringGenerator mapStringGen = new MapStringGenerator(MAPWIDTH, MAPHEIGHT);
		mapStringGen.setMapStructure(1,1);
		mapString = mapStringGen.getMapString();

		gameMap = new Map(MAPWIDTH,MAPHEIGHT,TILE_WIDTH,TILE_HEIGHT,mapString);
		gameMap.setGameWindow(this);


		timeElapsed = System.currentTimeMillis()-startTime;
		System.out.println("Map generated in : " + timeElapsed + " ms");



		gui = new GUI(this, mm);
		mousePosition = new Point(0,0);
	}
	
	public int getActualFrames() {
		return actualFrames;
	}
	
	public Map getMap() {
		return gameMap;
	}
	
	public Point getMousePos() {
		return mousePosition;
	}
	
	public void setMousePos(Point p) {
		mousePosition = p;
	}
	
	public void setMouseOverMenu(boolean overMenu) {
		isMouseOverMenu = overMenu;
	}
	
	public boolean getMouseOverMenu() {
		return isMouseOverMenu;
	}
	
	public MaterialManager getMaterialManager() {
		return mm;
	}
	
	public GameWindow getGameWindow() {
		return this;
	}
	
	public JFrame getFrame() {
		return gameWindow;
	}

	public GameState getGameState() {
		 return gameState;
	}

	public void setGameState(GameState state){
		gameState = state;

	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}
}
