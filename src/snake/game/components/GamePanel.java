package snake.game.components;

import snake.game.helper.* ;
import snake.game.util.GameParameters;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GamePanel extends JPanel implements ActionListener {

    // IMPORTING PARAMETERS FROM GameParameters
    public static final int SPEED = GameParameters.SPEED ;
    public static final int DIMENSIONS = GameParameters.DIMENSIONS ;
    public static final int UNIT_SIZE = GameParameters.UNIT_SIZE ;
    public static final int INITIAL_BODY_PARTS = GameParameters.INITIAL_BODY_PARTS ;
    public static final Direction INITIAL_DIRECTION = GameParameters.INITIAL_DIRECTION ;

    // DECLARING VARIABLES
    static final int SCREEN_WIDTH = DIMENSIONS ;
    static final int SCREEN_HEIGHT = DIMENSIONS ;
    static final int GAME_UNITS = (SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE ;
    static final int DELAY = (int) Math.round(7500.0/SPEED) ;
    boolean paused ;
    Position[] snakePos ;
    Position apple ;
    int bodyParts ;
    Direction direction ;
    boolean running = false ;
    Timer timer = new Timer(DELAY, this) ;


    GamePanel(){
        this.setPreferredSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) ) ;

        this.setBackground(GameParameters.BACKGROUND_COLOR) ;
        this.setFocusable(true) ;
        startGame() ;
    }

    public void startGame() {
        snakePos = new Position[GAME_UNITS] ;

        bodyParts = INITIAL_BODY_PARTS ;
        for (int i = bodyParts-1; i >= 0; i--)
            snakePos[i] = new Position(0, 0) ;

        direction = INITIAL_DIRECTION ;
        newApple() ;
        running = true ;
        paused = false ;
        timer.restart() ;

    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g) ;
        draw(g) ;
    }

    public void draw( Graphics g ) {
        if (running) {
            g.setColor(GameParameters.APPLE_COLOR.getColor());
            g.fillOval(apple.getX(), apple.getY(), UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) { //Head -- Oval
                    g.setColor(GameParameters.SNAKE_HEAD_COLOR);
                    g.fillOval(snakePos[0].getX(), snakePos[0].getY(), UNIT_SIZE, UNIT_SIZE);
                } else { //Body -- Square
                    g.setColor(GameParameters.SNAKE_BODY_COLOR);
                    if (getScore() > 0 && getScore() % 5 == 0)
                        g.setColor(ColorUtil.randomColor(ColorUtil.Colour.GREEN));
                    g.fillRect(snakePos[i].getX(), snakePos[i].getY(), UNIT_SIZE, UNIT_SIZE);
                }
            }
            GameFrame.scoreLabel.setText("    Score: " + getScore());

            if (paused) {
                g.setFont(GameParameters.PAUSED_FONT) ;
                g.setColor(GameParameters.DETAILS_COLOR) ;
                FontMetrics metrics = g.getFontMetrics(g.getFont()) ;
                String text = "[  game is paused  ]" ;
                g.drawString(text, SCREEN_WIDTH/2 - metrics.stringWidth(text)/2, SCREEN_HEIGHT/2 - metrics.getHeight()/2) ;
                timer.stop() ;
            }

        } else {
            gameOver(g) ;
            g.setColor(GameParameters.TEXT_COLOR) ;
            g.setFont(GameParameters.SUBTITLE_FONT2) ;
            FontMetrics metrics = getFontMetrics(g.getFont()) ;
            String text = "SCORE: "+getScore() ;
            g.drawString( text, (SCREEN_WIDTH - metrics.stringWidth(text))/2, SCREEN_HEIGHT/2 + UNIT_SIZE/2 ) ;
        }
    }

    public void newApple() {
        apple = new Position() ;
        if (Arrays.asList(snakePos).contains(apple)) {
            newApple() ;
        }
    }

    public void move() {
        for (int i = bodyParts-1; i > 0; i--)
            snakePos[i].setPos( snakePos[i-1].getPos() ) ;

        switch (direction) {
            case UP -> snakePos[0].vertical(UNIT_SIZE) ;
            case DOWN -> snakePos[0].vertical(-1 * UNIT_SIZE) ;
            case LEFT -> snakePos[0].horizontal(-1 * UNIT_SIZE) ;
            case RIGHT -> snakePos[0].horizontal(UNIT_SIZE) ;
        }
    }

    public void checkApple() {
        if (apple.equals(snakePos[0])) {
            snakePos[bodyParts] = new Position(snakePos[bodyParts-1].getPos()) ;
            bodyParts++ ;
            newApple() ;
        }
    }

    public int getScore() {
        return bodyParts - INITIAL_BODY_PARTS ;
    }

    public void checkCollisions() {
        if ( snakePos[0].isOutside() ) {
            endGame() ;
            return;
        }
        for (int i = bodyParts; i > 0; i--) {
            if ( snakePos[0].equals(snakePos[i]) ) {
                endGame() ;
                return;
            }
        }
    }

    public void endGame() {
        running = false ;
        timer.stop() ;
    }

    public void pause() {
        if (this.paused) {
            this.paused = false ;
            timer.restart() ;
        } else {
            this.paused = true ;
        }
    }

    public void gameOver( Graphics g ) {
        g.setColor(GameParameters.TEXT_COLOR) ;
        g.setFont( GameParameters.TITLE_FONT ) ;
        FontMetrics metrics = getFontMetrics(g.getFont()) ;
        String text = "Game Over" ;
        g.drawString( text, (SCREEN_WIDTH - metrics.stringWidth(text))/2, SCREEN_HEIGHT/2 - (int)(UNIT_SIZE*1.5) ) ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move() ;
            checkApple() ;
            checkCollisions() ;
        }
        repaint() ;
    }
}