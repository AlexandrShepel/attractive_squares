import acm.graphics.GCanvas;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
 * File: Canvas.java
 * ---------------------
 * Creates balls on the program window and interacts with them.
 */
public class Canvas extends GCanvas implements MouseListener, MouseMotionListener {

    /* The squares that feel mouse attraction. */
    private ArrayList<Square> squares;

    /**
     * The class constructor.
     *  -- Adds listeners interfaces.
     *  -- Creates squares on the app's window.
     */
    public Canvas() {
        setSize(new Dimension(600, 400));
        addMouseListener(this);
        addMouseMotionListener(this);
        createSquares();
    }

    /**
     * Creates squares on the app's window.
     */
    private void createSquares() {
        squares = new ArrayList<>();
        RandomGenerator randomGenerator = new RandomGenerator();

        for (int i = 0; i < Constants.SQUARES_COUNT; i++) {
            int xCoordinate = randomGenerator.nextInt(
                    Constants.MINIMAL_DISTANCE_TO_BORDERS,
                    this.getWidth() - Constants.MINIMAL_DISTANCE_TO_BORDERS);
            int yCoordinate = randomGenerator.nextInt(
                    Constants.MINIMAL_DISTANCE_TO_BORDERS,
                    this.getHeight() - Constants.MINIMAL_DISTANCE_TO_BORDERS);
            Square square = new Square(xCoordinate, yCoordinate, Constants.SQUARES_SIZE);
            this.add(square);
            squares.add(square);
        }
    }

    /**
     * -- Removes all objects from canvas.
     * -- Stops all threads.
     */
    public void restart() {
        for (Square square : squares) {
            square.stop();
        }
        squares.clear();
        this.removeAll();
        createSquares();
    }

    /* The implementation of the MouseListener and MouseMotionListener interfaces. */
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
        inverseAttraction();
    }
    public void mouseReleased (MouseEvent e) {
        inverseAttraction();
    }
    public void mouseEntered(MouseEvent e) {
        startMotion();
    }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        for (Square square: squares)
            square.setMouseLocation(new Point(e.getX(), e.getY()));
    }

    /**
     * Starts a motion of squares.
     */
    private void startMotion() {
        for (Square square : squares) {
            Thread thread = new Thread(square);
            thread.start();
        }
    }

    /**
     * Inverses attraction of the squares.
     */
    private void inverseAttraction() {
        for (Square square : squares) {
            square.inverseAttraction();
        }
    }
}
