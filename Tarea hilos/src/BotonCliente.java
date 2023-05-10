import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BotonCliente implements ActionListener{
    JFrame frame;
    JButton button;
    JPanel panel;
    Socket client;

    boolean activo = false;

    public BotonCliente(){
        panel = new JPanel(new BorderLayout());
        frame = new JFrame("Tarea Programada POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Enceder");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(200, 200));
        button.setActionCommand("encender");
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("encender")){
            button.setText("apagar");
            button.setActionCommand("apagar");
            activo = true;
        }
        else{
            button.setText("encender");
            button.setActionCommand("encender");
            activo = false;
        }
        try{
            client = new Socket("localhost", 9999);
            DataOutputStream enviStream = new DataOutputStream(client.getOutputStream());
            enviStream.writeBoolean(activo);
            DataInputStream datoStream = new DataInputStream(client.getInputStream());
            activo  = datoStream.readBoolean();
            System.out.println("El cliente recibio del servidor "+ activo);
            enviStream.close();
            client.close();

        }catch(IOException A){
            A.printStackTrace();
        }
    }

}