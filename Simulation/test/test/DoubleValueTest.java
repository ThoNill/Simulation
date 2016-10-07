package test;

import javax.swing.JFrame;

import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValue;
import simulation.doubles.DoubleValueDescription;
import simulation.doubles.DoubleViewFactory;

public class DoubleValueTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        DoubleValueDescription description = new DoubleValueDescription("test",
                new DoubleBound(-1.0), new DoubleBound(1.0));

        DoubleValue value = new DoubleValue("TestValue", description);

        DoubleViewFactory guiFactory = new DoubleViewFactory();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(guiFactory.getGUI(value, description));
        value.setValue(0.0);
        frame.pack();
        frame.setVisible(true);

    }
}
