package snake.game.components;

import snake.game.util.GameParameters;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton implements MouseListener {
    private final Color color ;

    MyButton(String title, Color color){
        this.color = color ;
        this.setText(title) ;
        this.setBackground(GameParameters.BACKGROUND_COLOR) ;
        this.setFont(GameParameters.BUTTON_FONT) ;
        this.setForeground(this.color) ;
        this.setBorder(GameParameters.THIN_EMPTY_BORDER) ;

        this.setFocusable(false) ;

        addMouseListener(this) ;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.color == GameParameters.EXIT_BUTTON_COLOR) {
            this.setForeground(GameParameters.TEXT_COLOR) ;
            this.setBackground(this.color) ;
        } else
            this.setForeground(GameParameters.EXIT_BUTTON_COLOR) ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(this.color) ;
        this.setBackground(GameParameters.BACKGROUND_COLOR) ;
    }
}