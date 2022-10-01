package snake.game.helper;

import snake.game.components.GamePanel;
import java.util.Objects ;
import java.util.Random ;

public class Position {
    Random random = new Random() ;
    private final int DIMENSION = GamePanel.DIMENSIONS ;
    private final int UNIT_SIZE = GamePanel.UNIT_SIZE ;
    private int x ;
    private int y ;
    private final int[] pos = new int[2] ;

    public Position() {
        setPos( randomLocation() , randomLocation() ) ;
    }
    public Position(int x, int y) {
        setPos(x, y) ;
    }
    public Position(int[] pos) {
        setPos(pos) ;
    }

    public int randomLocation() {
        return random.nextInt(DIMENSION/UNIT_SIZE)*UNIT_SIZE ;
    }

    public void setPos(int[] pos) {
        setPos(pos[0], pos[1]);
    }
    public void setPos(int x, int y) {
        setX(x) ;
        setY(y) ;
    }

    public void setX(int x) {
        this.x = x ;
        pos[0] = x ;

    }

    public void setY(int y) {
        this.y = y ;
        pos[1] = y ;
    }

    public void horizontal(int movement) {
        setX(this.x+movement) ;
    }

    public void vertical(int movement) {
        setY(this.y-movement) ;
    }

    public int[] getPos() {
        return this.pos ;
    }

    public int getX() {
        return x ;
    }

    public int getY() {
        return y ;
    }

    public boolean isOutside() {
        return x > DIMENSION-UNIT_SIZE || y > DIMENSION-UNIT_SIZE || x < 0 || y < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (o == null || getClass() != o.getClass()) return false ;

        final Position position = (Position) o ;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{x=" + x + ", y=" + y + '}';
    }
}
