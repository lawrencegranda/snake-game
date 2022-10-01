package snake.game.helper;

import snake.game.util.*;
import java.awt.*;
import java.util.Random;

public class ColorUtil {
    public static final ColorInterface APPLE_COLOR = () -> {    //SETTING THE COLOUR DEPENDING ON CONSTANT_APPLE_COLOR
        if (GameParameters.CONSTANT_APPLE_COLOR)
            return GameParameters.DEFAULT_APPLE_COLOR ;
        return randomColor(Colour.RED) ;
    } ;

    public enum Colour {
        RED,
        GREEN,
        BLUE,
        ANY
    }

    public static Color randomColor (Colour mainColor) {
        Random random = new Random() ;
        int r = random.nextInt(100) ;
        int g = random.nextInt(100) ;
        int b = random.nextInt(100) ;
        switch (mainColor) {
            case RED ->     r+=155 ;
            case GREEN ->   g+=155 ;
            case BLUE ->    b+=155 ;
            case ANY -> {   r+= random.nextInt(155) ;
                g+= random.nextInt(155) ;
                b+= random.nextInt(155) ;
            }
        }
        return new Color(r, g, b) ;
    }

    @FunctionalInterface
    public interface ColorInterface {
        Color getColor() ;
    }
}
