import javax.swing.*;
import java.awt.*;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CClient extends JFrame 
{
	public static String serverip = "";
	public static boolean ip = false;
	public static boolean connectionFailed = false; 
	private static CClient client;
	private static AddressFrame addressFrame;
	private static Registry[] reg = new Registry[2];
	private static GameSessionInterface[] gi = new GameSessionInterface[2];
	
	
	private static void getIPAndConnect() throws InterruptedException 
	{

		addressFrame = new AddressFrame();

		do 
		{
			if (connectionFailed == true) 
			{
				addressFrame.lab.setText("(Connection Failed) Enter IP Address of Server");
				addressFrame.setSize(500,300);
			}
			addressFrame.setVisible(true);
			addressFrame.text.requestFocus();

			while (!ip) 
			{
				Thread.sleep(50);
			}
			addressFrame.setVisible(false); 
			try 
			{

				reg[0] = LocateRegistry.getRegistry(serverip, 8888);
				gi[0] = (GameSessionInterface) reg[0].lookup("Session1");

				reg[1] = LocateRegistry.getRegistry(serverip, 8888);
				gi[1] = (GameSessionInterface) reg[1].lookup("Session2");

				connectionFailed = false;
			} 
			catch (RemoteException e) 
			{
				connectionFailed = true; 
				ip = false;
			} 
			catch (NotBoundException e) 
			{
				connectionFailed = true;
				ip = false;
			}

		} while (connectionFailed);
	}
	
	public static void main(String[] args) throws InterruptedException, RemoteException 
	{
		getIPAndConnect();
	}	
	
	
}