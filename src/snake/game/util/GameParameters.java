package snake.game.util;

import snake.game.helper.* ;

import javax.swing.border.EmptyBorder;
import java.awt.* ;

public final class GameParameters {

    //GAME PARAMETERS
    public static final int SPEED = 120 ;
    public static final int DIMENSIONS = 600 ;
    public static final int INITIAL_BODY_PARTS = 6 ;
    public static final Direction INITIAL_DIRECTION = Direction.RIGHT ;

    public static final boolean RESPONSIVE_UNIT_SIZE = true ; //IF TRUE, THE GAME WILL BE RESPONSIVE
    public static final int UNIT_SIZE = getSize() ; //SETTING THE UNIT_SIZE RELATIVE TO DIMENSIONS, OR NOT

    public static int getSize() {
            if (RESPONSIVE_UNIT_SIZE)
                return DIMENSIONS/24 ;
            return 25 ;
    }
    //PADDING and BORDERS
    public static final int THIN_PADDING = DIMENSIONS/100 ;
    public static final EmptyBorder THIN_EMPTY_BORDER = new EmptyBorder(THIN_PADDING,THIN_PADDING,THIN_PADDING,THIN_PADDING) ;
    public static final int THICK_PADDING = DIMENSIONS/60 ;
    public static final EmptyBorder THICK_EMPTY_BORDER = new EmptyBorder(THICK_PADDING,THICK_PADDING,THICK_PADDING,THICK_PADDING) ;

    //FONTS
    public static final Font TITLE_FONT = new Font("Ink Free", Font.BOLD, DIMENSIONS/10 ) ;
    public static final Font SUBTITLE_FONT1 = new Font("Ink Free", Font.BOLD, DIMENSIONS/30 ) ;
    public static final Font SUBTITLE_FONT2 = new Font("Verdana", Font.BOLD + Font.ITALIC, DIMENSIONS/30 ) ;
    public static final Font BUTTON_FONT = new Font("Verdana", Font.BOLD, DIMENSIONS/30 ) ;
    public static final Font PAUSED_FONT = new Font("Verdana", Font.BOLD + Font.ITALIC, DIMENSIONS/30 ) ;

    //COLORS
    public static final Color BACKGROUND_COLOR = new Color(0, 0, 0) ;
    public static final Color TEXT_COLOR = new Color(255, 255, 255) ;
    public static final Color EXIT_BUTTON_COLOR = new Color(133, 0, 0) ;
    public static final Color DETAILS_COLOR = new Color(75, 75, 75) ;
    public static final Color SNAKE_HEAD_COLOR = new Color(0, 255, 111) ;
    public static final Color SNAKE_BODY_COLOR = new Color(15, 150, 5) ;

    public static final boolean CONSTANT_APPLE_COLOR = false ;  //IF FALSE, RANDOM RED COLOUR GENERATED
    public static final Color DEFAULT_APPLE_COLOR = new Color(255, 0, 0) ;  //IF ABOVE IS FALSE, THE COLOR IS RED
    public static final ColorUtil.ColorInterface APPLE_COLOR = ColorUtil.APPLE_COLOR ;

    private GameParameters() {}
}
