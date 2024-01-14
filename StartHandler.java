
package application ;

import javafx.event.ActionEvent ;
import javafx.event.EventHandler ;
import javafx.stage.Stage ;

/**
 * @author Josh Nardone
 *
 * @version 1.0.0 2023-04-12 Initial implementation
 */
public class StartHandler implements EventHandler<ActionEvent>
    {

    @Override
    public void handle( ActionEvent event )
        {
        // TODO Auto-generated method stub
        try
            {
            System.out.println( "start" ) ;
            Game main = new Game() ;

            Stage primary = new Stage() ;
            main.start( primary ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }

    }
