package snake.game.components;

import snake.game.helper.Direction;
import snake.game.util.GameParameters;
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class GameFrame extends JFrame {
    public static MyButton bRestart ;
    public static GamePanel gPanel ;
    public static JLabel scoreLabel ;
    public static MyButton bExit ;

    public GameFrame() {
        this.setFocusable(true) ;
        this.addKeyListener( new MyKeyAdapter() ) ;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("/res/snake.ico"));
        this.setUndecorated(true) ;
        this.getRootPane().setBorder(GameParameters.THICK_EMPTY_BORDER) ;
        this.setTitle("Snake Game")  ;
        this.setBackground(GameParameters.BACKGROUND_COLOR) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setResizable(false) ;

        this.setLayout(new BorderLayout()) ;


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        northPanel.setBackground(GameParameters.BACKGROUND_COLOR) ;
        northPanel.setLayout(new BorderLayout()) ;
        northPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                GameParameters.THIN_EMPTY_BORDER,
                BorderFactory.createMatteBorder(0, 0, 1, 0, GameParameters.DETAILS_COLOR)),
                GameParameters.THIN_EMPTY_BORDER )) ;

        bExit = new MyButton("X", GameParameters.EXIT_BUTTON_COLOR) ;
        bExit.setPreferredSize(new Dimension(bExit.getFont().getSize()*3,bExit.getFont().getSize()*2)) ;
        bExit.setVerticalAlignment(SwingConstants.CENTER) ;
        bExit.setBorder(GameParameters.THIN_EMPTY_BORDER) ;
        bExit.addActionListener(e -> closeApp() );

        scoreLabel = new JLabel("    Score: "+0) ;
        scoreLabel.setForeground(GameParameters.TEXT_COLOR) ;
        scoreLabel.setFont(GameParameters.SUBTITLE_FONT1) ;

        northPanel.add( scoreLabel , BorderLayout.CENTER ) ;
        northPanel.add( bExit, BorderLayout.EAST) ;
        this.add(northPanel, BorderLayout.NORTH) ;

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout()) ;
        southPanel.setBackground(GameParameters.BACKGROUND_COLOR) ;

        gPanel = new GamePanel() ;
        bRestart = new MyButton("RESTART GAME", GameParameters.TEXT_COLOR) ;
        bRestart.addActionListener(e -> gPanel.startGame() );
        bRestart.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        GameParameters.THIN_EMPTY_BORDER,
                        BorderFactory.createMatteBorder(1, 0, 0, 0, GameParameters.DETAILS_COLOR)),
                BorderFactory.createEmptyBorder(GameParameters.THIN_PADDING*2,0,0,0) )) ;

        southPanel.add( gPanel , BorderLayout.NORTH ) ;
        southPanel.add( bRestart, BorderLayout.SOUTH) ;
        this.add(southPanel, BorderLayout.SOUTH) ;


        this.pack() ;
        this.setLocationRelativeTo(null) ;
        this.setVisible(true) ;
    }

    public void closeApp() {
        this.setVisible(false) ;
        this.dispose() ;
    }




    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed( KeyEvent e ) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:  if (gPanel.direction !=Direction.LEFT && gPanel.direction !=Direction.RIGHT && !gPanel.paused)
                                           gPanel.direction = Direction.LEFT ;
                                        break ;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT: if (gPanel.direction !=Direction.LEFT && gPanel.direction !=Direction.RIGHT && !gPanel.paused)
                                           gPanel.direction = Direction.RIGHT ;
                                        break ;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:    if (gPanel.direction !=Direction.UP && gPanel.direction !=Direction.DOWN && !gPanel.paused)
                                            gPanel.direction = Direction.UP ;
                                        break ;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:  if (gPanel.direction !=Direction.UP && gPanel.direction !=Direction.DOWN && !gPanel.paused)
                                            gPanel.direction = Direction.DOWN ;
                                        break ;
                case KeyEvent.VK_ESCAPE:
                case KeyEvent.VK_END:   closeApp() ;
                                        break ;
                case KeyEvent.VK_SPACE: if (gPanel.running){
                                            gPanel.pause() ;
                                            break ;
                                        }
                case KeyEvent.VK_DELETE:
                case KeyEvent.VK_BACK_SPACE:
                case KeyEvent.VK_ENTER: gPanel.startGame() ;
            }
        }
    }

}
