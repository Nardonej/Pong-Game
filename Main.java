
package application ;

import javafx.animation.AnimationTimer ;
import javafx.application.Application ;
import javafx.geometry.Insets ;
import javafx.geometry.Pos ;
import javafx.scene.Scene ;
import javafx.scene.control.Button ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.StackPane ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.scene.text.Font ;
import javafx.scene.text.Text ;
import javafx.stage.Stage ;

/**
 * @author Josh Nardone
 *
 * @version 1.0.0 2023-04-12 Initial implementation
 */
public class Main extends Application
    {

    private final int width = 1200 ;
    private final int height = 800 ;
    private final int wallThickness = 20 ;
    private final int ballSpeed = 4 ;

    private int x = this.ballSpeed ;
    private int y = this.ballSpeed ;

    @Override
    public void start( Stage primary )
        {
        try
            {

            // Create the buttons
            final Text pong = new Text( "PONG GAME" ) ;
            pong.setFont( new Font( 60 ) ) ;
            pong.setFill( Color.BLACK ) ;

            final BorderPane root = new BorderPane() ;
            root.setCenter( pong ) ;

            final Button btnStart = new Button( "Start Game" ) ;
            btnStart.setPrefSize( 350, 150 ) ;
            btnStart.setFont( new Font( 30 ) ) ;
            btnStart.setOnAction( new StartHandler() ) ;

            final Button btnQuit = new Button( "Quit Game" ) ;
            btnQuit.setPrefSize( 350, 150 ) ;
            btnQuit.setFont( new Font( 30 ) ) ;
            btnQuit.setOnAction( new QuitHandler() ) ;

            final HBox hBox = new HBox() ;
            hBox.setSpacing( 100 ) ;
            hBox.setAlignment( Pos.BOTTOM_CENTER ) ;
            hBox.getChildren().addAll( btnStart, btnQuit ) ;

            // Create the animation pane
            Rectangle ball = new Rectangle( 30, 30, Color.BLUE ) ;
            ball.relocate( 100, 100 ) ;

            // Create the walls
            Rectangle topWall = createTopWall() ;
            Rectangle bottomWall = createBottomWall() ;
            Rectangle leftWall = createLeftWall() ;
            Rectangle rightWall = createRightWall() ;

            BorderPane animationPane = new BorderPane( pong ) ;
            animationPane.getChildren().addAll( topWall, bottomWall, leftWall, rightWall, ball ) ;

            // Create the main pane and set the animation pane as the background
            final StackPane root1 = new StackPane() ;
            root1.getChildren().addAll( animationPane, hBox ) ;
            StackPane.setAlignment( hBox, Pos.BOTTOM_CENTER ) ;
            root1.setPadding( new Insets( 0, 0, 100, 0 ) ) ;
            Scene scene = new Scene( root1, this.width, this.height ) ;

            AnimationTimer timer = new AnimationTimer()
                {

                @Override
                public void handle( long now )
                    {
                    ball.setLayoutX( ball.getLayoutX() + Main.this.x ) ;
                    ball.setLayoutY( ball.getLayoutY() + Main.this.y ) ;

                    // Check collision with walls
                    if ( ball.getBoundsInParent().intersects( topWall.getBoundsInParent() ) )
                        {
                        Main.this.y = Main.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent()
                                  .intersects( bottomWall.getBoundsInParent() ) )
                        {
                        Main.this.y = -Main.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent().intersects( leftWall.getBoundsInParent() ) )
                        {
                        Main.this.x = Main.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent().intersects( rightWall.getBoundsInParent() ) )
                        {
                        Main.this.x = -Main.this.ballSpeed ;

                        }

                    }

                } ;
            timer.start() ;

            primary.setScene( scene ) ;
            primary.show() ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * @param args
     */

    public static void main( String args )
        {
        launch( args ) ;

        }


    // creates top wall
    private Rectangle createTopWall()
        {
        Rectangle topWall = new Rectangle( this.width, this.wallThickness, Color.BLACK ) ;
        topWall.setLayoutX( 0 ) ;
        topWall.setLayoutY( 0 ) ;
        return topWall ;

        }


    // creates bottom wall
    private Rectangle createBottomWall()
        {
        Rectangle bottomWall = new Rectangle( this.width, this.wallThickness, Color.BLACK ) ;
        bottomWall.setLayoutX( 0 ) ;
        bottomWall.setLayoutY( this.height - this.wallThickness ) ;
        return bottomWall ;

        }


    // creates left wall
    private Rectangle createLeftWall()
        {
        Rectangle leftWall = new Rectangle( this.wallThickness, this.height, Color.BLACK ) ;
        leftWall.setLayoutX( 0 ) ;
        leftWall.setLayoutY( 0 ) ;
        return leftWall ;

        }


    // creates right wall
    private Rectangle createRightWall()
        {
        Rectangle rightWall = new Rectangle( this.wallThickness, this.height, Color.BLACK ) ;
        rightWall.setLayoutX( this.width - this.wallThickness ) ;
        rightWall.setLayoutY( 0 ) ;
        return rightWall ;

        }

    }
