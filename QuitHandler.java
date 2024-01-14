/**
 *
 */

package application ;

import javafx.event.ActionEvent ;
import javafx.event.EventHandler ;

/**
 * @author Josh Nardone
 */
public class QuitHandler implements EventHandler<ActionEvent>
    {

    @Override
    public void handle( ActionEvent event )
        {
        // TODO Auto-generated method stub
        System.exit( 0 ) ;

        }

    }
