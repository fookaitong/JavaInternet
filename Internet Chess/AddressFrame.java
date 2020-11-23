import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddressFrame extends JFrame
{
	JPanel pan;
	JButton button;
	JLabel lab;
	JTextField text;
	
	public AddressFrame()
	{
		int x,y;
		this.setSize(500,300);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		x = d.width - this.getWidth();
		y = d.height - this.getHeight();
		this.setLocation(x,y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Enter IP Address of Server");
		
		pan = new JPanel();
		lab = new JLabel("Enter IP Address of Server");
		text = new JTextField("", 20);
		button = new JButton("Enter");
		ButtonListener butListener = new ButtonListener();
		button.addActionListener(butListener);
		
		pan.add(lab);
		pan.add(text);
		pan.add(button);
		this.add(pan);
		this.setVisible(false);
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == button)
			{	
				CClient.serverip = text.getText();
				CClient.ip = true;
			}
		}
	}
	
}