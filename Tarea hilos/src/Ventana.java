import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ventana implements Runnable{
    JFrame frame;
    JLabel blinker;
    JPanel panel;
    ServerSocket serverSocket;
    Socket client;
    boolean activo; 

    public Ventana(){
        Thread hilo = new Thread(this);
        frame = new JFrame("Tarea Programada POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        blinker = new JLabel();
        blinker.setPreferredSize(new Dimension(200, 200));
        blinker.setOpaque(true);
        blinker.setBackground(Color.WHITE);

        panel = new JPanel(new BorderLayout());
        panel.add(blinker);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        hilo.start();
    }

    private void blink(){
        while(true){
            //OJO, se necesita esta pausa
            System.out.println(activo);
            if (activo){
                try {
                    blinker.setBackground(Color.YELLOW);
                    Thread.sleep(500);
                    blinker.setBackground(Color.WHITE);
                    Thread.sleep(500);
                } catch (InterruptedException e) { 
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(1234);
            System.out.println("Servidor en ejecucion....");
            while(true){
                System.out.println("Conectando cliente....");
                client = serverSocket.accept();
                System.out.println("Cliente conectado: "+ client.getInetAddress().getHostName());
                DataInputStream datoStream = new DataInputStream(client.getInputStream());
                activo  = datoStream.readBoolean();
                System.out.println("En el servidor "+ activo);
                blink();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}