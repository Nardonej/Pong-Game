
package application ;

import javafx.animation.AnimationTimer ;
import javafx.application.Application ;
import javafx.scene.Scene ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.Pane ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.scene.text.Font ;
import javafx.scene.text.Text ;
import javafx.stage.Stage ;

/**
 * @author Josh Nardone
 */

public class Game extends Application
    {

    private final int width = 1200 ;
    private final int height = 800 ;
    private final int wallThickness = 20 ;
    private int ballSpeed = 2 ;

    private int score ;

    @Override
    public void start( Stage primary )
        {
        try
            {

            Pane root = new Pane() ;
            Scene scene = new Scene( root, this.width, this.height ) ;
            if ( primary == null )
                {
                throw new IllegalArgumentException( "primary cannot be null" ) ;

                }

            primary.setTitle( "Pong Goal" ) ;

            // Create the ball
            Rectangle ball = new Rectangle( 30, 30, Color.BLUE ) ;
            ball.relocate( 100, 100 ) ;

            // Create the paddle
            Paddle paddle = new Paddle( 100, 350, 20, 100, Color.BLUE, 5 ) ;
            root.getChildren().add( paddle ) ;

            // Create the paddle handler
            double paddleSpeed = 15 ;
            PaddleHandler paddleHandler = new PaddleHandler( paddle, paddleSpeed ) ;

            // Add a key event handler to the scene
            scene.setOnKeyPressed( event ->
                {
                paddleHandler.handleInput( event.getCode() ) ;

                } ) ;

            // Create the walls
            Rectangle topWall = createTopWall() ;
            Rectangle bottomWall = createBottomWall() ;
            Rectangle leftWall = createLeftWall() ;
            Rectangle rightWall = createRightWall() ;

            root.getChildren().addAll( ball, topWall, bottomWall, leftWall, rightWall ) ;

            // Create the animation timer to update the ball's position
            AnimationTimer timer = new AnimationTimer()
                {

                double x = 1.5 ; // Set the ball's x velocity
                double y = 1.5 ; // Set the ball's y velocity

                @Override
                public void handle( long now )
                    {
                    ball.setLayoutX( ball.getLayoutX() + this.x ) ;
                    ball.setLayoutY( ball.getLayoutY() + this.y ) ;

                    // Check collision with walls
                    if ( ball.getBoundsInParent().intersects( topWall.getBoundsInParent() ) )
                        {
                        this.y = Game.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent()
                                  .intersects( bottomWall.getBoundsInParent() ) )
                        {
                        this.y = -Game.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent().intersects( rightWall.getBoundsInParent() ) )
                        {
                        this.x = -Game.this.ballSpeed ;

                        }
                    else if ( ball.getBoundsInParent().intersects( leftWall.getBoundsInParent() ) )
                        {

                        // stops balls velocity
                        this.x = Game.this.ballSpeed = 0 ;
                        this.y = Game.this.ballSpeed = 0 ;

                        // changes background and tells user the game is over
                        root.setStyle( "-fx-background-color: black;" ) ;
                        final Text endGame = new Text( "GAME OVER!" ) ;
                        endGame.setFont( new Font( 60 ) ) ;
                        endGame.setFill( Color.RED ) ;

                        // sets ball and paddle to be red
                        ball.setFill( Color.RED ) ;
                        paddle.setFill( Color.RED ) ;

                        // displays game over
                        final BorderPane gameOverRoot = new BorderPane() ;
                        gameOverRoot.setCenter( endGame ) ;
                        root.getChildren().add( gameOverRoot ) ;
                        gameOverRoot.setLayoutX( 400 ) ;
                        gameOverRoot.setLayoutY( 350 ) ;

                        // displays the users score-- how many times they hit the
                        // ball
                        final Text score1 = new Text( "SCORE: " + Game.this.score ) ;
                        score1.setFont( new Font( 60 ) ) ;
                        score1.setFill( Color.RED ) ;

                        // displays score
                        final BorderPane scoreRoot = new BorderPane() ;
                        scoreRoot.setCenter( score1 ) ;
                        root.getChildren().add( scoreRoot ) ;
                        scoreRoot.setLayoutX( 100 ) ;
                        scoreRoot.setLayoutY( 150 ) ;

                        }

                    // Check if the ball collides with the paddle
                    if ( paddle.getBoundsInParent().intersects( ball.getBoundsInParent() ) )
                        {
                        this.x *= -1 ;
                        Game.this.score = Game.this.score + 1 ;

                        }

                    }

                } ;

            timer.start() ;

            // Set the score to 0
            scene.getRoot().setUserData( 0 ) ;

            primary.setScene( scene ) ;
            primary.show() ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

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
