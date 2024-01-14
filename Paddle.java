/**
 *
 */

package application ;

import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;

/**
 * @author Josh Nardone
 */
public class Paddle extends Rectangle
    {

    private double speed ;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param speed
     */
    @SuppressWarnings( "javadoc" )
    public Paddle( double x, double y, double width, double height, Color color, double speed )
        {
        super( x, y, width, height ) ;
        setFill( color ) ;
        this.speed = speed ;

        }


    /**
     *
     *
     */
    public void moveUp()
        {
        setY( getY() - this.speed ) ;

        }


    /**
     *
     *
     */
    public void moveDown()
        {
        setY( getY() + this.speed ) ;

        }

    }
