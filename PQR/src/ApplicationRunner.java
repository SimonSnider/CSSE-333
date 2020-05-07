import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

public class ApplicationRunner {
	DatabaseConnectionService connector = new DatabaseConnectionService(null, null);
	ConnectionManagementDevice cmd = new ConnectionManagementDevice(connector.getConnection());
	AthleteManagementService ams = new AthleteManagementService(cmd);
	TeamManagementService tms = new TeamManagementService(cmd);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame = new JFrame("PQR");
	JFrame frame2 = new JFrame("PQR Connect");
	//JWindow window = new JWindow(frame);
	JButton button = new JButton("Connect");
	Dimension dimension = new Dimension();
	Dimension dimension2 = new Dimension();
	Dimension dimension3 = new Dimension();
	//ActionListener buttonPress = new Listener();
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	
	public void componentAlignX(JComponent comp, float flt) {
		comp.setAlignmentX(flt);
	}
	public void componentAlignY(JComponent comp, float flt) {
		comp.setAlignmentY(flt);
	}
	public void componentAlignXY(JComponent comp, float fltx, float flty) {
		componentAlignX(comp, fltx);
		componentAlignY(comp, flty);
	}
	
	public void runProgram() {
		
	}
	
	public void runConnect() {
		Color entryBackground = new Color(255, 255, 255, 127);
		Color failureBackground = new Color(179, 55, 55, 250);
		Color successBackground = new Color(52, 179, 90, 250);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					JWindow window = new JWindow(frame2);
					window.setBounds((int)screenSize.getWidth()/2-100, (int)screenSize.getHeight()/2-20, 200, 50);
					JLabel label = new JLabel();
					componentAlignXY(label, JLabel.CENTER_ALIGNMENT, JLabel.CENTER_ALIGNMENT);
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setVerticalAlignment(JLabel.CENTER);
					Timer timer = new Timer(3000, new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            window.setVisible(false);
				            window.dispose();
				            frame2.setVisible(false);
				            frame2.dispose();
				            runProgram();
				        }
				    });
					timer.setRepeats(false);
					window.add(label);
				try {
					boolean t = connector.connect(null, null);
					if (t == true) {
						timer.start();
						label.setText("Successfully connected!");
						window.setBackground(successBackground);
						window.setVisible(true);
					}
					else {
						timer.start();
						label.setText("Connection failed.");
						window.setBackground(failureBackground);
						window.setVisible(true);
					}
				} catch (ClassNotFoundException e1) {
					label.setText("Connection failed.");
					window.setVisible(true);
					e1.printStackTrace();
					timer.start();
				}
			}
		});
		componentAlignXY(button, JButton.CENTER_ALIGNMENT, JButton.CENTER_ALIGNMENT);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setVerticalAlignment(JButton.CENTER);
		frame2.setSize(300, 200);
		frame2.setBounds((int)screenSize.getWidth()/2-150, (int)screenSize.getHeight()/2-100, 300, 200);
		frame2.add(panel4);
		panel4.setBounds(0, 0, 200, 100);
		panel4.setBackground(entryBackground);
		frame2.setVisible(true);
		//panel4.add(button);
		panel4.setLayout( new GridBagLayout() );
		panel4.add(button, new GridBagConstraints());
	}
	
	public void runApplication(String[] args) {
		runConnect();
		/*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				JWindow window = new JWindow(frame);
				window.setBounds(650, 350, 300, 200);
				JLabel label = new JLabel();
				window.add(label);
				try {
					boolean b = connector.connect(null, null);
					if (b == true) {
						label.setText("Successfully connected!");
						window.setVisible(true);
					}
					else {
						label.setText("Connection failed.");
						window.setVisible(true);
					}
				} catch (ClassNotFoundException e1) {
					label.setText("Connection failed.");
					panel.setVisible(true);
					e1.printStackTrace();
				}
			}});
		frame.setSize(1600, 900);
		panel.add(new JLabel("Add Entries"));
		//window.add(new JLabel("New window"));
		Color entryBackground = new Color(218, 235, 247, 127);
		Color PQRBackground = new Color(0, 0, 0, 15);
		panel.setBackground(entryBackground);
		panel2.setBackground(PQRBackground);
		panel2.setSize(1600, 700);
		panel.setSize(1582, 200);
		panel3.setSize(1582, 35);
		panel3.setBackground(new Color(0,0,0,0));
		//window.setVisible(true);
		dimension = panel.getSize();
		dimension2 = panel2.getSize();
		dimension3 = panel3.getSize();
		Rectangle rect = frame.getBounds();
		panel.setLocation(0, 0);
		panel2.setLocation(rect.width - dimension2.width, dimension.height);
		panel3.setLocation(0, 0);
		button.setBounds(rect.width - 250, rect.height - 800, 100, 50);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		panel3.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		frame.add(button);
		frame.add(panel3);
		frame.add(panel);
		frame.add(panel2);
		
		
		frame.setLayout(null);
		frame.setVisible(true);
		*/
	}

}