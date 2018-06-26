package container;

import javax.swing.*;
import java.awt.*;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
 
import java.util.Timer;
import java.util.TimerTask;


/*
 * Para que el programa funcione es necesario contar con un array que almacene todos los sitios
 * o direcciones ip que se desean comprobar
 * */


public class Principal extends JFrame{
	static int tbusqueda =15000; //900000 ; /// minutos de delay para busqueda 	
	
	/////constructor del programa, crea la ventana principal
	
	public Principal(){
		
		super("Comprobacion Status Red/servidores");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		Container cp = getContentPane(); 
		cp.setLayout(new FlowLayout());
		
		JLabel etiqueta = new JLabel("IP/Dominio: ");
		JTextField texto = new JTextField(10);			
		JButton boton = new JButton("Checar");
		JButton clear = new JButton("Vaciar");
		
		JTextArea logs = new JTextArea(10,28);
		JScrollPane scrollPane = new JScrollPane(logs);
		logs.setEditable(false);
				
		cp.add(etiqueta);
		cp.add(texto);
		cp.add(boton);		
		cp.add(scrollPane);
		cp.add(clear);
		
		
		logs.setText(logs.getText()+"Bienvenido a la interfaz de pruebas de red de EHWEB");
		logs.setText(logs.getText()+"\n Se verificaran todos los servidores ");
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		//		

		Cheker chk = new Cheker();
		
		/*
		Principal prm= new Principal();
		prm.setVisible(true);
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				////verificamos los dominios 
				System.out.println("Exec");
				prm.task();
				
		    }
		}, 1000,tbusqueda);
		*/
		//prm.capturaDominio();	
		
	}////end of main
	
	
		
	
	/*
	 * Metodos graficos
	 * */
	
	
	public void home(){
		JFrame home = new JFrame("Herramientas de Red");
		home.setSize(400,300);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setVisible(true);		
	}
	
	
	
	
	/*
	 * Establecemos los metodos de verificacion de dominios  
	 * */
	
	public void task() {
		String[] directions = new String[20];
		int rest;
		
		
		/* dominios almacenados en un arreglo
		 * cada vuelta de verificacion se checa dominio a dominio, si alguno esta fuera de linea 
		 * se manda una alerta y se agrega en el panel principal como un log del sistema con error
		 * opcionalmente se puede almacenar el log en una base de datos
		 * */
		directions[0] = "65.99.205.139";
		directions[1] = "especialistasweb.mx";		
		directions[2] = "especialistasweb.mx";
		directions[3] = "especialistasweb.mx";
		directions[4] = "especialistasweb.mx";
		directions[5] = "especialistasweb.mx";
		directions[6] = "especialistasweb.mx";
				
		rest = verf2(directions[0]);
		if(rest>0){ ///si el sitio esta fuera de linea lo monitoreamos por 2 minutos
			JOptionPane.showMessageDialog(null, directions[0]+" En linea");			
		}else {
			JOptionPane.showMessageDialog(null, directions[0]+" FUERA DE LINEA","Warning",JOptionPane.ERROR_MESSAGE);						
		}
	}
	
	
	public Integer verf2(String domain){
		int verfs=0;
		try{
			InetAddress[] addresses = InetAddress.getAllByName(domain);
			for (InetAddress address : addresses) 
			{
				if (address.isReachable(10000)){   
					verfs=1;
			    }else{
			    	verfs=0;
			    }
			}
			
		}catch(IOException ex){
			verfs = 0; ///sitio fuera de linea
		}				
		return verfs;		
	}
	
	
	
	public void capturaDominio() throws IOException {
		String nombre;		
		nombre = JOptionPane.showInputDialog("Dominio a verificar");
		////verificamos si el dominio/ip tiene conexion
		int a = verf2(nombre);
		if(a>0) {
			JOptionPane.showMessageDialog(null, nombre+" En linea");
			
		}else {
			JOptionPane.showMessageDialog(null, nombre+" FUERA DE LINEA","Warning",JOptionPane.ERROR_MESSAGE);
		}
	}
		
		
			
		

	
	
	///metodo para verificar un dominio o ip determinado
		

}
