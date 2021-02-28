import acm.graphics.GRect;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * File: Square.java
 * ---------------------
 * Describes the square that appears on the window.
 */
public class Square extends GRect implements Runnable {

    /* The flag of simulation start. */
    private final AtomicBoolean isStarted;

    /* The ongoing location of the mouse. */
    private Point mouseLocation = new Point(0, 0);

    /* The total displacement of the square from the initial point. */
    private double dxTotal = 0, dyTotal = 0;

    /* The boolean value that describes interaction between square and cursor.
       If value is positive then square moves to the cursor and vise a versa. */
    private boolean isAttractionPositive;

    /**
     * The class constructor.
     *
     * @param x The x-coordinate of the square.
     * @param y The y-coordinate of the square.
     * @param size The size of the square.
     */
    public Square(double x, double y, double size) {
        super(x, y, size, size);
        super.setFilled(true);
        isStarted = new AtomicBoolean(false);
        isAttractionPositive = true;
    }

    /**
     * Sets an ongoing location of the mouse.
     *
     * @param mouseLocation The location of the cursor.
     */
    public void setMouseLocation(Point mouseLocation) {
        this.mouseLocation = mouseLocation;
    }

    /**
     * Inverses attraction to the opposite boolean value.
     */
    public void inverseAttraction() {
        isAttractionPositive = !isAttractionPositive;
    }

    /**
     * Runs the animation of the square's motion.
     */
    @Override
    public void run() {
        isStarted.set(true);

        while (isStarted.get()) {
            moveSquareToCursor();
            pause(Constants.PAUSE);
        }
    }

    /**
     * Moves square to the ongoing cursor location
     * or to the opposite direction if attraction is negative.
     */
    private void moveSquareToCursor() {
        int direction = (isAttractionPositive) ? 1 : - 1;
        double dx = (mouseLocation.getX() - this.getX()) * Constants.ATTRACTION_FORCE_COEFFICIENT * direction;
        double dy = (mouseLocation.getY() - this.getY()) * Constants.ATTRACTION_FORCE_COEFFICIENT * direction;

        if (Math.abs(dxTotal + dx) < Math.abs(Constants.MAXIMUM_DISPLACEMENT)) {
            this.setLocation(this.getX() + dx, this.getY());
            dxTotal += dx;
        }

        if (Math.abs(dyTotal + dy) < Math.abs(Constants.MAXIMUM_DISPLACEMENT)) {
            this.setLocation(this.getX(), this.getY() + dy);
            dyTotal += dy;
        }
    }

    /**
     * Stops the animation.
     */
    public void stop() {
        isStarted.set(false);
    }
}
