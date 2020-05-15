import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class ApplicationRunner {
	DatabaseConnectionService connector = new DatabaseConnectionService(null, null);
	ImportManager im;
	AthleteManagementService ams;
	TeamManagementService tms; 
	MatchManagementService mms; 
	BroomstickManagementService bms;
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
	
	//Initialization of variables
	JFrame runFrame = new JFrame("Insertion, Deletion, and Updation");
	JPanel runPanel = new JPanel();
	String[] selects = {"Athlete", "Team", "Match", "Broomstick"};
	JComboBox runBox = new JComboBox(selects);
	JTextField runField11 = new JTextField("");
	JTextField runField12 = new JTextField("");
	JTextField runField13 = new JTextField("");
	JTextField runField14 = new JTextField("");
	JTextField runField15 = new JTextField("");
	JTextField runField16 = new JTextField("");
	JTextField runField17 = new JTextField("");
	JTextField runField18 = new JTextField("");
	JTextField runField21 = new JTextField("");
	JTextField runField22 = new JTextField("");
	JTextField runField23 = new JTextField("");
	JTextField runField24 = new JTextField("");
	JTextField runField25 = new JTextField("");
	JTextField runField26 = new JTextField("");
	JTextField runField27 = new JTextField("");
	JTextField runField28 = new JTextField("");
	JTextField runField31 = new JTextField("");
	JTextField runField32 = new JTextField("");
	JTextField runField33 = new JTextField("");
	JTextField runField34 = new JTextField("");
	JTextField runField35 = new JTextField("");
	JLabel runLabel11 = new JLabel("");
	JLabel runLabel12 = new JLabel("");
	JLabel runLabel13 = new JLabel("");
	JLabel runLabel14 = new JLabel("");
	JLabel runLabel15 = new JLabel("");
	JLabel runLabel16 = new JLabel("");
	JLabel runLabel17 = new JLabel("");
	JLabel runLabel18 = new JLabel("");
	JLabel runLabel21 = new JLabel("");
	JLabel runLabel22 = new JLabel("");
	JLabel runLabel23 = new JLabel("");
	JLabel runLabel24 = new JLabel("");
	JLabel runLabel25 = new JLabel("");
	JLabel runLabel26 = new JLabel("");
	JLabel runLabel27 = new JLabel("");
	JLabel runLabel28 = new JLabel("");
	JLabel runLabel31 = new JLabel("");
	JLabel runLabel32 = new JLabel("");
	JLabel runLabel33 = new JLabel("");
	JLabel runLabel34 = new JLabel("");
	JLabel runLabel35 = new JLabel("");
	//All purpose boxes for use in place of labels
	JComboBox runBox1 = new JComboBox();
	JComboBox runBox2 = new JComboBox();
	JComboBox runBox3 = new JComboBox();
	JComboBox runBox4 = new JComboBox();
	JComboBox runBox5 = new JComboBox();
	JComboBox runBox6 = new JComboBox();
	JComboBox runBox7 = new JComboBox();
	JComboBox runBox8 = new JComboBox();
	JComboBox runBox9 = new JComboBox();
	JComboBox runBox10 = new JComboBox();
	JButton insertButton = new JButton("Insert");
	JButton updateButton = new JButton("Update");
	JButton deleteButton = new JButton("Delete");
	ArrayList<JTextField> ap = new ArrayList<JTextField>();
	ArrayList<JLabel> al = new ArrayList<JLabel>();
	ArrayList<JComboBox> acb = new ArrayList<JComboBox>();
	String athleteFields[] = {"Name", "Bludger Hits", "Grade", "Points Scored", "School", "Injuries", "Fouls", "Ejections", "Name, ID", "Bludger Hits", "Grade", "Points Scored", "School", "Injuries", "Fouls", "Ejections", "Name, ID"};

	public void runProgram() {
		runFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		runBox.setSelectedItem("Athlete");
		
		//General
		runFrame.setSize(1800, 900);
		runFrame.setLocation((int)screenSize.getWidth()/2-900, (int)screenSize.getHeight()/2-450);
		runPanel.setSize(1400, 800);
		runPanel.setLocation(100, 50);
		runPanel.setLayout(null);
		runPanel.add(insertButton);
		runPanel.add(updateButton);
		runPanel.add(deleteButton);
		runPanel.add(runBox);
		ap.add(runField11);
		ap.add(runField12);
		ap.add(runField13);
		ap.add(runField14);
		ap.add(runField15);
		ap.add(runField16);
		ap.add(runField17);
		ap.add(runField18);
		ap.add(runField21);
		ap.add(runField22);
		ap.add(runField23);
		ap.add(runField24);
		ap.add(runField25);
		ap.add(runField26);
		ap.add(runField27);
		ap.add(runField28);
		ap.add(runField31);
		ap.add(runField32);
		ap.add(runField33);
		ap.add(runField34);
		ap.add(runField35);
		al.add(runLabel11);
		al.add(runLabel12);
		al.add(runLabel13);
		al.add(runLabel14);
		al.add(runLabel15);
		al.add(runLabel16);
		al.add(runLabel17);
		al.add(runLabel18);
		al.add(runLabel21);
		al.add(runLabel22);
		al.add(runLabel23);
		al.add(runLabel24);
		al.add(runLabel25);
		al.add(runLabel26);
		al.add(runLabel27);
		al.add(runLabel28);
		al.add(runLabel31);
		al.add(runLabel32);
		al.add(runLabel33);
		al.add(runLabel34);
		al.add(runLabel35);
		acb.add(runBox1);
		acb.add(runBox2);
		acb.add(runBox3);
		acb.add(runBox4);
		acb.add(runBox5);
		acb.add(runBox6);
		acb.add(runBox7);
		acb.add(runBox8);
		acb.add(runBox9);
		acb.add(runBox10);
		
		for(JTextField tempField : ap) {
			tempField.setSize(130,20);
			runPanel.add(tempField);
			tempField.setLocation(-500,-500);
		}
		for(JLabel tempLabel : al) {
			tempLabel.setSize(100,20);
			runPanel.add(tempLabel);
			tempLabel.setLocation(-500,-500);
		}
		for(JComboBox tempBox : acb) {
			tempBox.setSize(130,20);
			runPanel.add(tempBox);
			tempBox.setLocation(-500,-500);
		}
		
		
		//General
		insertButton.setLocation(runFrame.getWidth()-200, 100);
		insertButton.setSize(100, 40);
		updateButton.setLocation(runFrame.getWidth()-200, 400);
		updateButton.setSize(100, 40);
		deleteButton.setLocation(runFrame.getWidth()-200, 700);
		deleteButton.setSize(100, 40);
		runBox.setLocation(100 ,50);
		runBox.setSize(130, 20);
		
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String)runBox.getSelectedItem()) {
				case "Athlete":
					ams.InsertAthlete(runField11.getText(), runField12.getText(), runField13.getText(), runField14.getText(), runField15.getText(), runField16.getText(), runField17.getText(), runField18.getText());
					runAthleteInput();
					break;
				case "Team":
					tms.InsertTeam(runField12.getText(), runField13.getText(), runField11.getText());
					runTeamInput();
					break;
				case "Match":
					String temp = (String)acb.get(2).getSelectedItem();
					int pos = temp.indexOf(",", 0);
					String ID2 = temp.substring(0,pos);
					temp = (String)acb.get(3).getSelectedItem();
					pos = temp.indexOf(",", 0);
					String ID3 = temp.substring(0,pos);
					mms.InsertMatch(ID2, ID3, runField13.getText(), runField14.getText(), runField15.getText(), runField16.getText());
					runMatchInput();
					break;
				case "Broomstick":
					bms.InsertBroomstick(runField11.getText(), runField12.getText(), runField13.getText());
					runBroomstickInput();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Somehow your selection box has managed to evade the range of selectable options. Wpw. Impressive.");	
				}
				//ams.InsertAthlete(runField11.getText(), runField12.getText(), runField13.getText(), runField14.getText(), runField15.getText(), runField16.getText(), runField17.getText(), runField18.getText());
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String)runBox.getSelectedItem()) {
				case "Athlete":
					String temp = (String)acb.get(0).getSelectedItem();
					int pos = temp.indexOf(",", 0);
					String ID = temp.substring(0,pos);
					String Name = temp.substring(pos+1);
					ams.UpdateAthlete(ID, Name, runField22.getText(), runField23.getText(), runField24.getText(), runField25.getText(), runField26.getText(), runField27.getText(), runField28.getText());
					runAthleteInput();
					break;
				case "Team":
					temp = (String)acb.get(0).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					Name = temp.substring(pos+1);
					tms.UpdateTeam(runField15.getText(), runField16.getText(), Name, ID);
					runTeamInput();
					break;
				case "Match":
					temp = (String)acb.get(0).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					Name = temp.substring(pos+1);
					temp = (String)acb.get(4).getSelectedItem();
					pos = temp.indexOf(",", 0);
					String ID2 = temp.substring(0,pos);
					temp = (String)acb.get(5).getSelectedItem();
					pos = temp.indexOf(",", 0);
					String ID3 = temp.substring(0,pos);
					mms.UpdateMatch(ID, ID2, ID3, runField22.getText(), runField23.getText(), runField24.getText(), runField25.getText());
					runMatchInput();
					break;
				case "Broomstick":
					temp = (String)acb.get(0).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					bms.UpdateBroomstick(ID, runField15.getText(), runField16.getText(), runField17.getText());
					runBroomstickInput();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Somehow your selection box has managed to evade the range of selectable options. Wpw. Impressive.");	
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String)runBox.getSelectedItem()) {
				case "Athlete":
					String temp = (String)acb.get(1).getSelectedItem();
					int pos = temp.indexOf(",", 0);
					String ID = temp.substring(0,pos);
					ams.DeleteAthlete(ID);
					runAthleteInput();
					break;
				case "Team":
					temp = (String)acb.get(1).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					tms.Delete(ID);
					runTeamInput();
					break;
				case "Match":
					temp = (String)acb.get(1).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					mms.DeleteMatch(ID);
					runMatchInput();
					break;
				case "Broomstick":
					temp = (String)acb.get(1).getSelectedItem();
					pos = temp.indexOf(",", 0);
					ID = temp.substring(0,pos);
					bms.DeleteBroomstick(ID);
					runBroomstickInput();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Somehow your selection box has managed to evade the range of selectable options. Wpw. Impressive.");	
				}
				
			}
		});
		runBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String)runBox.getSelectedItem()) {
				case "Athlete":
					runAthleteInput();
					break;
				case "Team":
					runTeamInput();
					break;
				case "Match":
					runMatchInput();
					break;
				case "Broomstick":
					runBroomstickInput();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Somehow your selection box has managed to evade the range of selectable options. Wpw. Impressive.");	
				}
			}
		});
		
		runFrame.add(runPanel);
		runFrame.setVisible(true);
		runAthleteInput();
	}
	
	public void clearAllElements() {
		for(JComboBox tempBox : acb) { 
			ActionListener[] al = tempBox.getActionListeners();
			for(ActionListener tempAl : al) {
				tempBox.removeActionListener(tempAl);
			}
			tempBox.removeAllItems();
			tempBox.setLocation(-5000, 5000);
		}
		for(JLabel tempLabel : al) {
			tempLabel.setText("");
			tempLabel.setLocation(-5000,5000);
		}
		for(JTextField tempField : ap) {
			tempField.setText("");
			tempField.setLocation(-5000,5000);
		}
	}
	
	public void runAthleteInput() {
		clearAllElements();
		ArrayList<String> temps = ams.getNameAndIndex();
		Object[] toArr = temps.toArray();
		String[] holder = new String[temps.size()];
		int t = 0;
		for(Object tempObject : toArr) {
			runBox1.addItem((String)tempObject);
			runBox2.addItem((String)tempObject);
		}
		String athleteFields[] = {"Name", "Bludger Hits", "Grade", "Points Scored", "School", "Injuries", "Fouls", "Ejections", "Name, ID", "Bludger Hits", "Grade", "Points Scored", "School", "Injuries", "Fouls", "Ejections", "Name, ID"};
		for(int a = 0; a < 17; a++) {
			al.get(a).setText(athleteFields[a]);
		}
		//Athlete-Specific (for other teams, just run place all excess outside the bounds of the screen
		int i = 0;
		int j = 0;
		int k = 0;
		for(JTextField tempField : ap) {
			tempField.setEditable(true);
			if (i <= 7) {
				tempField.setLocation(60+(i++*170), 120);
			}
			else if (i <= 15) {
				if (i == 8) {
					runBox1.setLocation(60+(j++*170),420); //0 box is a nameID box
					runBox1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String temp = (String)acb.get(0).getSelectedItem();
							int pos = temp.indexOf(",", 0);
							String ID = temp.substring(0,pos);
							String[] temparr = {"BludgerHits", "Grade", "PointsScored", "School", "Injuries", "Fouls", "Ejections"};
							ArrayList<String> tempInfo = ams.getInfoFromID(ID);
							runField22.setText(tempInfo.get(0));
							runField23.setText(tempInfo.get(1));
							runField24.setText(tempInfo.get(2));
							runField25.setText(tempInfo.get(3));
							runField26.setText(tempInfo.get(4));
							runField27.setText(tempInfo.get(5));
							runField28.setText(tempInfo.get(6));
						}
					});
					i++;
				} else {
					tempField.setLocation(60+(j++*170), 420);
					i++;
				}
			}
			else if (i <= 16) {
				if (i == 16) {
					runBox2.setLocation(60+(k++*170), 720); //1 box is a nameID box
					i++;
				} else {
					tempField.setLocation(60+(k++*170), 720);
					i++;
				}
			}
		}
		i = 0;
		j = 0;
		k = 0;
		for(JLabel tempLabel : al) {
			tempLabel.setVisible(true);
			if (i <= 7) {
				tempLabel.setLocation(60+(i++*170), 100);
			}
			else if (i <= 15) {
				tempLabel.setLocation(60+(j++*170), 400);
				i++;
			}
			else if (i <= 16) {
				tempLabel.setLocation(60+(k++*170), 700);
				i++;
			}
		}
	}
	
	public void runTeamInput() {
		clearAllElements();
		ArrayList<String> temps = tms.getNameAndIndex();
		Object[] toArr = temps.toArray();
		String[] holder = new String[temps.size()];
		int t = 0;
		for(Object tempObject : toArr) {
			runBox1.addItem((String)tempObject);
			runBox2.addItem((String)tempObject);
		}
		String teamFields[] = {"Name", "County", "State", "Name, ID", "County", "State", "Name, ID"};
		for(int a = 0; a < 7; a++) {
			al.get(a).setText(teamFields[a]);
		}
		int i = 0;
		int j = 0;
		int k = 0;
		for(JTextField tempField : ap) {
			tempField.setEditable(true);
			if (i <= 2) {
				tempField.setLocation(60+(i++*170), 120);
			}
			else if (i <= 5) {
				if (i == 3) {
					runBox1.setLocation(60+(j++*170),420); //0 box is a nameID box
					runBox1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String temp = (String)acb.get(0).getSelectedItem();
							int pos = temp.indexOf(",", 0);
							String ID = temp.substring(0,pos);
							//String[] temparr = {"BludgerHits", "Grade", "PointsScored", "School", "Injuries", "Fouls", "Ejections"};
							ArrayList<String> tempInfo = tms.getInfoFromID(ID);
							runField16.setText(tempInfo.get(0));
							runField15.setText(tempInfo.get(1));
						}
					});
					i++;
				} else {
					tempField.setLocation(60+(j++*170), 420);
					i++;
				}
			}
			else if (i <= 6) {
				if (i == 6) {
					runBox2.setLocation(60+(k++*170), 720); //1 box is a nameID box
					i++;
				} else {
					tempField.setLocation(60+(k++*170), 720);
					i++;
				}
			}
			else {
				tempField.setLocation(-5000, 5000);
			}
		}
		i = 0;
		j = 0;
		k = 0;
		for(JLabel tempLabel : al) {
			tempLabel.setVisible(true);
			if (i <= 2) {
				tempLabel.setLocation(60+(i++*170), 100);
			}
			else if (i <= 5) {
				tempLabel.setLocation(60+(j++*170), 400);
				i++;
			}
			else if (i <= 6) {
				tempLabel.setLocation(60+(k++*170), 700);
				i++;
			}
			else {
				tempLabel.setLocation(-5000, 5000);
			}
		}
	}
	
	public void runMatchInput() {
		clearAllElements();
		ArrayList<String> temps = mms.getMatches();
		Object[] toArr = temps.toArray();
		String[] holder = new String[temps.size()];
		int t = 0;
		for(Object tempObject : toArr) {
			runBox1.addItem((String)tempObject);
			runBox2.addItem((String)tempObject);
		}
		ArrayList<String> tempsT = tms.getNameAndIndex();
		Object[] toArrT = tempsT.toArray();
		String[] holderT = new String[tempsT.size()];
		int T = 0;
		for(Object tempObject : toArrT) {
			runBox3.addItem((String)tempObject);
			runBox4.addItem((String)tempObject);
			runBox5.addItem((String)tempObject);
			runBox6.addItem((String)tempObject);
		}
		String matchFields[] = {"Home Team", "Away Team", "Home Score", "Away Score", "Date", "Stadium", "Matches", "Home Team", "Away Team", "Home Score", "Away Score", "Date", "Stadium", "Matches"};
		for(int a = 0; a < 14; a++) {
			al.get(a).setText(matchFields[a]);
		}
		//Athlete-Specific (for other teams, just run place all excess outside the bounds of the screen
		int i = 0;
		int j = 0;
		int k = 0;
		for(JTextField tempField : ap) {
			tempField.setEditable(true);
			if (i <= 5) {
				if (i == 0) {
					runBox3.setLocation(60+(i++*170),120);
				}
				else if (i == 1) {
					runBox4.setLocation(60+(i++*170),120);
				}
				else{
					tempField.setLocation(60+(i++*170), 120);
				}
			}
			else if (i <= 12) {
				if (i == 6) {
					runBox1.setLocation(60+(j++*170),420); //0 box is a nameID box
					runBox1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String temp = (String)acb.get(0).getSelectedItem();
							int pos = temp.indexOf(",", 0);
							String ID = temp.substring(0,pos);
							//String[] temparr = {"BludgerHits", "Grade", "PointsScored", "School", "Injuries", "Fouls", "Ejections"};
							ArrayList<String> tempInfo = mms.getInfoFromID(ID);
							runBox5.setSelectedItem(tempInfo.get(6)+","+tempInfo.get(0)); //HomeTeamID
							runBox6.setSelectedItem(tempInfo.get(7)+","+tempInfo.get(1)); //AwayTeamID
							runField22.setText(tempInfo.get(2));
							runField23.setText(tempInfo.get(3));
							runField24.setText(tempInfo.get(4));
							runField25.setText(tempInfo.get(5));
						}
					});
					i++;
				}
				else if (i == 7) {
					runBox5.setLocation(60+(j++*170),420);
					i++;
				}
				else if (i == 8) {
					runBox6.setLocation(60+(j++*170),420);
					i++;
				}
				else {
					tempField.setLocation(60+(j++*170), 420);
					i++;
				}
			}
			else if (i <= 13) {
				if (i == 13) {
					runBox2.setLocation(60+(k++*170), 720); //1 box is a nameID box
					i++;
				} else {
					tempField.setLocation(60+(k++*170), 720);
					i++;
				}
			}
			else {
				tempField.setLocation(-5000, 5000);
			}
		}
		i = 0;
		j = 0;
		k = 0;
		for(JLabel tempLabel : al) {
			tempLabel.setVisible(true);
			if (i <= 5) {
				tempLabel.setLocation(60+(i++*170), 100);
			}
			else if (i <= 12) {
				tempLabel.setLocation(60+(j++*170), 400);
				i++;
			}
			else if (i <= 13) {
				tempLabel.setLocation(60+(k++*170), 700);
				i++;
			}
			else {
				tempLabel.setLocation(-5000, 5000);
			}
		}
	}
	
	public void runBroomstickInput() {
		clearAllElements();
		ArrayList<String> temps = bms.getBroomsticks();
		Object[] toArr = temps.toArray();
		String[] holder = new String[temps.size()];
		int t = 0;
		for(Object tempObject : toArr) {
			runBox1.addItem((String)tempObject);
			runBox2.addItem((String)tempObject);
		}
		String broomstickFields[] = {"Make", "Model", "Release Date", "Broomsticks", "Make", "Model", "Release Date", "Broomsticks"};
		for(int a = 0; a < 8; a++) {
			al.get(a).setText(broomstickFields[a]);
		}
		//Athlete-Specific (for other teams, just run place all excess outside the bounds of the screen
		int i = 0;
		int j = 0;
		int k = 0;
		for(JTextField tempField : ap) {
			tempField.setEditable(true);
			if (i <= 2) {
				tempField.setLocation(60+(i++*170), 120);
			}
			else if (i <= 6) {
				if (i == 3) {
					runBox1.setLocation(60+(j++*170),420); //0 box is a nameID box
					runBox1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String temp = (String)acb.get(0).getSelectedItem();
							int pos = temp.indexOf(",", 0);
							String ID = temp.substring(0,pos);
							//String[] temparr = {"BludgerHits", "Grade", "PointsScored", "School", "Injuries", "Fouls", "Ejections"};
							ArrayList<String> tempInfo = bms.getInfoFromID(ID);
							runField15.setText(tempInfo.get(0));
							runField16.setText(tempInfo.get(1));
							runField17.setText(tempInfo.get(2));
						}
					});
					i++;
				} else {
					tempField.setLocation(60+(j++*170), 420);
					i++;
				}
			}
			else if (i <= 7) {
				if (i == 7) {
					runBox2.setLocation(60+(k++*170), 720); //1 box is a nameID box
					i++;
				} else {
					tempField.setLocation(60+(k++*170), 720);
					i++;
				}
			}
		}
		i = 0;
		j = 0;
		k = 0;
		for(JLabel tempLabel : al) {
			tempLabel.setVisible(true);
			if (i <= 2) {
				tempLabel.setLocation(60+(i++*170), 100);
			}
			else if (i <= 6) {
				tempLabel.setLocation(60+(j++*170), 400);
				i++;
			}
			else if (i <= 7) {
				tempLabel.setLocation(60+(k++*170), 700);
				i++;
			}
		}
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
					ams = new AthleteManagementService(connector.getConnection());
					tms = new TeamManagementService(connector.getConnection());
					mms = new MatchManagementService(connector.getConnection());
					bms = new BroomstickManagementService(connector.getConnection());
					im = new ImportManager(connector.getConnection());
					im.importTeams();
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