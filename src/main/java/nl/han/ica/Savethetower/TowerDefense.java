package nl.han.ica.Savethetower;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class TowerDefense extends JFrame {
//Launcher
	
	private JPanel contentPane;
	
	public static boolean Fullscreen = false;
	
	InfoFrame infoFrame = new InfoFrame();

	public static void main(String[] args) {
		
		if(args.length == 0) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TowerDefense frame = new TowerDefense();
						frame.setVisible(true);
						registerEscListener();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	
			
		} else {
			
			String argsStr = "";
			for(int i = 0; args.length > i; i++) {
				argsStr += args[i];
			}
			
			if(argsStr.contains("-fullscreen")) {
				Fullscreen = true;
			}
			
			if(argsStr.contains("-720p")) {
				Frame.BigRes = false;
				Frame.main();	
			} else if(argsStr.contains("-1440p")) {
				Frame.BigRes = true;
				Frame.main();
			} else {
				System.out.println();
				
				System.out.println("Argumente:");
				
				System.out.println("-720p");
				System.out.println("-1440p");
				System.out.println("-fullscreen");
				
				System.out.println();
			}
			
		}
		
	}

	public TowerDefense() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res" + File.separator + "ico.png"));
		setAlwaysOnTop(true);
		setTitle(Frame.Titel + " - Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 180);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowLostFocus(WindowEvent e) {}
			public void windowGainedFocus(WindowEvent e) {
				if(infoFrame.isVisible()) {
					infoFrame.requestFocus();
				}
				
			}
		});
		
		String[] resForComboBox = {Frame.Size1.width + " x " + Frame.Size1.height,
				Frame.Size2.width + " x " + Frame.Size2.height};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBoxRes = new JComboBox(resForComboBox);
		comboBoxRes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JCheckBox chckbxFullscreen = new JCheckBox("fullscreen");
		chckbxFullscreen.setSelected(true);
		chckbxFullscreen.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		
		JButton btnSpelen = new JButton("Spelen");
		btnSpelen.setForeground(Color.DARK_GRAY);
		btnSpelen.setBackground(Color.ORANGE);
		btnSpelen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(comboBoxRes.getSelectedItem().equals(resForComboBox[1])) {
					Frame.BigRes = true;
				}
				
				if(chckbxFullscreen.isSelected()) {
					Fullscreen = true;
				}
				
				Frame.main();
				
				setVisible(false);
				dispose();
				
			}
		});
		btnSpelen.setFont(UIManager.getFont("InternalFrame.titleFont"));
		btnSpelen.setBounds(234, 101, 180, 30);
		contentPane.add(btnSpelen);
		
		
		comboBoxRes.setBounds(254, 12, 160, 22);
		contentPane.add(comboBoxRes);
		
		JLabel lblResolutie = new JLabel("Resolutie:");
		lblResolutie.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResolutie.setBounds(10, 14, 90, 18);
		contentPane.add(lblResolutie);
		
		chckbxFullscreen.setBackground(Color.GREEN);
		chckbxFullscreen.setBounds(6, 112, 90, 23);
		contentPane.add(chckbxFullscreen);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(infoFrame.isVisible()) {
					infoFrame.requestFocus();
				} else {
					infoFrame.setVisible(true);
				}
				
			}
		});
		btnInfo.setBackground(Color.ORANGE);
		btnInfo.setBounds(234, 66, 180, 30);
		contentPane.add(btnInfo);
	}
	
	public static void registerEscListener() {
		
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(
		        new EventQueue() {
		            @Override
					protected void dispatchEvent(AWTEvent event) {
		                if (event instanceof KeyEvent) {
		                    KeyEvent keyEvent = (KeyEvent) event;
		                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED
		                            && (keyEvent).getKeyCode() == KeyEvent.VK_ESCAPE) {
		                    	
		                    	if(Screen.runGame) {
		                    		if(Screen.isGameEnding) {
				                    	if(Screen.saveFile.exists()) {
				                    		Screen.saveFile.delete();
				                    	}
		                    		} else {
				                    	TextFileWriter.writeLineinTextFile(Screen.saveFile.getAbsolutePath(), String.valueOf(Screen.startingLevel));	
		                    		}	
		                    	}
		                    	System.exit(0);
		                    }
		                }
		                super.dispatchEvent(event);
		            }
		        });
		
	}
}

//package nl.han.ica.Savethetower;
//
//import java.awt.AWTEvent;
//import java.awt.EventQueue;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//
//import javax.swing.JPanel;
//
////import com.sun.prism.image.ViewPort;
//import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
//import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
//import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
//import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
//import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
//import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
//import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
//import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
//import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
//import nl.han.ica.OOPDProcessingEngineHAN.View.View;
//import nl.han.ica.Savethetower.tiles.BoardsTile;
//import processing.core.PApplet;
//
///**
// * @author Ralph Niels
// */
//@SuppressWarnings("serial")
//public class TowerDefense extends GameEngine {
//	private JPanel contentPane;
//	
//	public static boolean Fullscreen = false;
//	
//	InfoFrame infoFrame = new InfoFrame();
//	
////    private Sound backgroundSound;
////    private Sound bubblePopSound;
//    private TextObject dashboardText;
////    private BubbleSpawner bubbleSpawner;
//    private int bubblesPopped;
//    private IPersistence persistence;
//    private Player player;
//
//
//    public static void main(String[] args) {
//        PApplet.main(new String[]{"nl.han.ica.Savethetower.a"});
//        
//        if(args.length == 0) {
//			
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						a frame = new a();
//						frame.setVisible(true);
//						registerEscListener();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});	
//			
//		} else {
//			
//			String argsStr = "";
//			for(int i = 0; args.length > i; i++) {
//				argsStr += args[i];
//			}
//			
//			if(argsStr.contains("-fullscreen")) {
//				Fullscreen = true;
//			}
//			
//			if(argsStr.contains("-720p")) {
//				Frame.BigRes = false;
//				Frame.main();	
//			} else if(argsStr.contains("-1440p")) {
//				Frame.BigRes = true;
//				Frame.main();
//			} else {
//				System.out.println();
//				
//				System.out.println("Argument:");
//				
//				System.out.println("-720p");
//				System.out.println("-1440p");
//				System.out.println("-fullscreen");
//				
//				System.out.println();
//			}
//			
//		}
//		
//	}
//    
//
//    /**
//     * In deze methode worden de voor het spel
//     * noodzakelijke zaken geïnitialiseerd
//     */
//    @Override
//    public void setupGame() {
//
//        int worldWidth=1204;
//        int worldHeight=903;
//
////        initializeSound();
//        createDashboard(worldWidth, 100);
//        initializeTileMap();
//        initializePersistence();
//
//        createObjects();
////        createBubbleSpawner();
//
//        createViewWithoutViewport(worldWidth, worldHeight);
//        //createViewWithViewport(worldWidth, worldHeight, 800, 800, 1.1f);
//
//    }
//
//    /**
//     * Creeërt de view zonder viewport
//     * @param screenWidth Breedte van het scherm
//     * @param screenHeight Hoogte van het scherm
//     */
//    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
//        View view = new View(screenWidth,screenHeight);
//        view.setBackground(loadImage("src/main/java/nl/han/ica/Savethetower/media/background.jpg"));
//
//        setView(view);
//        size(screenWidth, screenHeight);
//    }
//
//    /**
//     * Creeërt de view met viewport
//     * @param worldWidth Totale breedte van de wereld
//     * @param worldHeight Totale hoogte van de wereld
//     * @param screenWidth Breedte van het scherm
//     * @param screenHeight Hoogte van het scherm
//     * @param zoomFactor Factor waarmee wordt ingezoomd
//     */
//    private void createViewWithViewport(int worldWidth,int worldHeight,int screenWidth,int screenHeight,float zoomFactor) {
//        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int)Math.ceil(screenWidth/zoomFactor),(int)Math.ceil(screenHeight/zoomFactor),0,0);
//        viewPort.setTolerance(50, 50, 50, 50);
//        View view = new View(viewPort, worldWidth,worldHeight);
//        setView(view);
//        size(screenWidth, screenHeight);
//        view.setBackground(loadImage("src/main/java/nl/han/ica/Savethetower/media/background.jpg"));
//    }
//
//    /**
//     * Initialiseert geluid
//     */
////    private void initializeSound() {
////        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/Savethetower/media/TowerDefense.mp3");
////        backgroundSound.loop(-1);
////        bubblePopSound = new Sound(this, "src/main/java/nl/han/ica/Savethetower/media/pop.mp3");
////    }
//
//
//    /**
//     * Maakt de spelobjecten aan
//     */
//    private void createObjects() {
//        player = new Player(this);
//        addGameObject(player, 100, 100);
////        Swordfish sf=new Swordfish(this);
//        //addGameObject(sf,200,200);
//        
////         	Mob mb =new Mob(this);
////        addGameObject(mb,10,25,15);
////        addGameObject(mb,150,55);
//    }
//
//    /**
//     * Maakt de spawner voor de bellen aan
//     */
////    public void createBubbleSpawner() {
////        bubbleSpawner=new BubbleSpawner(this,bubblePopSound,2);
////    }
//
//    /**
//     * Maakt het dashboard aan
//     * @param dashboardWidth Gewenste breedte van dashboard
//     * @param dashboardHeight Gewenste hoogte van dashboard
//     */
//    private void createDashboard(int dashboardWidth,int dashboardHeight) {
//        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
//        dashboardText=new TextObject("");
//        dashboard.addGameObject(dashboardText);
//        addDashboard(dashboard);
//    }
//
//    /**
//     * Initialiseert de opslag van de bellenteller
//     * en laadt indien mogelijk de eerder opgeslagen
//     * waarde
//     */
//    private void initializePersistence() {
//        persistence = new FilePersistence("main/java/nl/han/ica/Savethetower/media/bubblesPopped.txt");
//        if (persistence.fileExists()) {
//            bubblesPopped = Integer.parseInt(persistence.loadDataString());
//            refreshDasboardText();
//        }
//    }
//
//    /** 
//     * Initialiseert de tilemap
//     */
//    private void initializeTileMap() {
//        /* TILES */
//        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/Savethetower/media/cell.png");
//        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);
//        
//        Sprite tower = new Sprite("src/main/java/nl/han/ica/Savethetower/media/tower.png");
//        TileType<BoardsTile> tower1 = new TileType<>(BoardsTile.class, tower);
//
//        TileType[] tileTypes = { boardTileType ,tower1};
//        int tileSize=50;
//        int tilesMap[][]={
//                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//                {0,0,-1,-1,-1,-1,-1,-1,-1,-1},
//                {-1,0,0,0,-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,0,0,0,0,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1,0,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1,0,-1,-1,-1},
//                {-1,-1,-1,0,0,0,0,-1,-1,-1},
//                {-1,-1,-1,0,-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,0,0,-1,-1,-1,-1,-1},
//                {-1,-1,-1, 0, 0, 0, 0,0,0 , 1},
//                {-1,-1,-1,-1,-1,-1,-1,-1,-1,0},
//                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
//        };
//        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
//    }
//    
//    
//
//    @Override
//    public void update() {
//    }
//
//    /**
//     * Vernieuwt het dashboard
//     */
//    private void refreshDasboardText() {
//        dashboardText.setText("towers owned: "+bubblesPopped);
//        }
//
//    /**
//     * Verhoogt de teller voor het aantal
//     * geknapte bellen met 1
//     */
//    
//	public static void registerEscListener() {
//		
//        Toolkit.getDefaultToolkit().getSystemEventQueue().push(
//		        new EventQueue() {
//		            @Override
//					protected void dispatchEvent(AWTEvent event) {
//		                if (event instanceof KeyEvent) {
//		                    KeyEvent keyEvent = (KeyEvent) event;
//		                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED
//		                            && (keyEvent).getKeyCode() == KeyEvent.VK_ESCAPE) {
//		                    	
//		                    	if(Screen.runGame) {
//		                    		if(Screen.isGameEnding) {
//				                    	if(Screen.saveFile.exists()) {
//				                    		Screen.saveFile.delete();
//				                    	}
//		                    		} else {
//				                    	TextFileWriter.writeLineinTextFile(Screen.saveFile.getAbsolutePath(), String.valueOf(Screen.startingLevel));	
//		                    		}	
//		                    	}
//		                    	System.exit(0);
//		                    }
//		                }
//		                super.dispatchEvent(event);
//		            }
//		        });
//		
//	}
//}
