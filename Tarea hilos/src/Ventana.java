import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            //System.out.println(activo);
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

    private void hiloSecundario(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                blink();
            }
        }).start();
    }
    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(9999);
            System.out.println("Servidor en ejecucion....");
            while(true){
                client = serverSocket.accept();
                System.out.println("\nCliente conectado: "+ client.getInetAddress().getHostName());
                DataInputStream datoStream = new DataInputStream(client.getInputStream());
                activo  = datoStream.readBoolean();
                System.out.println("El servidor recibio del cliente "+ activo);
                hiloSecundario();
                DataOutputStream enviStream = new DataOutputStream(client.getOutputStream());
                enviStream.writeBoolean(activo);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}