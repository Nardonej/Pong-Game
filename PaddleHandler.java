/**
 *
 */

package application ;

import javafx.scene.input.KeyCode ;
import javafx.scene.shape.Rectangle ;

/*
 * @author Josh Nardone
 */
/**
 * @author Josh Nardone
 *
 * @version 1.0.0 2023-04-12 Initial implementation
 */
public class PaddleHandler
    {

    private final Rectangle paddle ;
    private final double speed ;

    /**
     * @param paddle
     * @param speed
     */
    public PaddleHandler( Rectangle paddle, double speed )
        {
        this.paddle = paddle ;
        this.speed = speed ;

        }


    /**
     * @param keyCode
     */
    public void handleInput( KeyCode keyCode )
        {
        if ( keyCode == KeyCode.W )
            {
            moveUp() ;

            }
        else if ( keyCode == KeyCode.S )
            {
            moveDown() ;

            }

        if ( keyCode == KeyCode.UP )
            {
            moveUp() ;

            }
        else if ( keyCode == KeyCode.DOWN )
            {
            moveDown() ;

            }

        }


    private void moveUp()
        {
        double y = this.paddle.getY() - this.speed ;
        if ( y >= 0 )
            {
            this.paddle.setY( y ) ;

            }

        }


    private void moveDown()
        {
        double y = this.paddle.getY() + this.speed ;
        if ( ( y + this.paddle.getHeight() ) <=
             this.paddle.getParent().getBoundsInLocal().getHeight() )
            {
            this.paddle.setY( y ) ;

            }

        }

    }
