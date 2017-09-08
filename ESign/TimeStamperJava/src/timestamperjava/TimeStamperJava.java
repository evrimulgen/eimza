/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamperjava;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;

/**
 *
 * @author cihan
 */
public class TimeStamperJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ESYAException {
        TimeStamperForm form = new TimeStamperForm(null, true);
        form.setVisible(true);
        form.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == WindowEvent.WINDOW_CLOSED) {
                    System.exit(0);
                }
            }
        });
    }

}
