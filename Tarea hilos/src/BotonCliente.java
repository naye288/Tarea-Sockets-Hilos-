import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonCliente implements ActionListener{
    JFrame frame;
    JButton button;
    JPanel panel;

    boolean activo = false;

    public BotonCliente(){
        panel = new JPanel(new BorderLayout());
        frame = new JFrame("Tarea Programada POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Enceder");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(100, 100));
        button.setActionCommand("encender");
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}