import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * File: Main.java
 * ---------------------
 * Creates squares on the program's window and allows user to interact with them.
 */
public class Main extends SimpleProgram implements ActionListener {

    /* The canvas where program is visualized. */
    Canvas canvas;

    /**
     * Initialize the program.
     *  -- Adds a canvas where the animation will be.
     *  -- Adds an action listeners.
     *  -- Adds a restart button.
     */
    public void init() {
        canvas = new Canvas();
        add(canvas, CENTER);
        add(new JButton ("Restart"), SOUTH);
        addActionListeners();
    }

    /**
     * Restarts the program when "Restart" button is pressed.
     *
     * @param ae The action event object.
     *           Uses to identify the pressing of "Restart" button.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Restart")) {
            canvas.restart();
        }
    }
}
