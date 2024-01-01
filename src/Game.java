import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Game {
	
	Font titleFont, messageFont, itemFont;
	Timer timer;
	
	JFrame window;
	JPanel cookiePanel, counterPanel, itemPanel, messagePanel;
	JLabel counterLabel, perSecondLabel;
	JTextArea messageArea;
	JButton cookieButton, item1, item2, item3, item4;
	ImageIcon cookieIcon;
	
	CookieHandler cHandler = new CookieHandler();
	MouseHandler mHandler = new MouseHandler();
	
	int cookieCounter, cursorPrice, cursorNumber, timerSpeed, grandmaPrice, grandmaNumber, grandmaUnlockPrice;
	double perSecond;
	boolean timerOn, grandmaUnlock;
	

	public static void main(String[] args) {
		
		
		new Game();
		
	}
	
	
	public Game() {
		
		perSecond = 0;
		cookieCounter = 0;
		cursorNumber = 0;
		cursorPrice = 10;
		grandmaNumber = 0;
		grandmaUnlockPrice = 50;
		grandmaPrice = 100; //FIX to 100
		grandmaUnlock = false;
		timerSpeed = 0;
		timerOn = false;
		
		
		createFont();
		createUI();
		
	}
	
	public void createFont() {
		
		titleFont = new Font("Comic Sans MS", Font.BOLD, 30);
		itemFont = new Font("Comic Sans MS", Font.BOLD, 24);
		messageFont = new Font("Comic Sans MS", Font.PLAIN, 15);
		
	}
	
	public void createUI() {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		window = new JFrame("Cookie Clicker");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLocationRelativeTo(null);		
		window.setLayout(null);
		window.setResizable(false);
		
		cookiePanel = new JPanel();
		cookiePanel.setBounds(100, 200, 200, 200);
		cookiePanel.setBackground(Color.black);
		window.add(cookiePanel);
		
		ImageIcon cookieImage = new ImageIcon(getClass().getClassLoader().getResource("cookieImage.png"));
		cookieButton = new JButton();
		cookieButton.setBackground(Color.black);
		cookieButton.setFocusPainted(false);
		cookieButton.setBorder(null);
		cookieButton.setIcon(cookieImage);
		cookieButton.addActionListener(cHandler);
		cookieButton.setActionCommand("cookie");
		cookiePanel.add(cookieButton);
		
		counterPanel = new JPanel();
		counterPanel.setBounds(130, 120, 200, 70);
		counterPanel.setLayout(new GridLayout(2, 1));
		counterPanel.setBackground(Color.black);
		window.add(counterPanel);
		
		counterLabel = new JLabel("Cookies: " + cookieCounter);
		counterLabel.setForeground(Color.white);
		counterLabel.setBackground(Color.black);
		counterLabel.setFont(titleFont);
		counterPanel.add(counterLabel);
		
		perSecondLabel = new JLabel();
		perSecondLabel.setForeground(Color.white);
		perSecondLabel.setBackground(Color.black);
		perSecondLabel.setFont(messageFont);
		counterPanel.add(perSecondLabel);
		
		itemPanel = new JPanel();
		itemPanel.setBounds(425, 135, 250, 270);
		itemPanel.setBackground(Color.black);
		itemPanel.setLayout(new GridLayout(4, 1));
		window.add(itemPanel);
		
		item1 = new JButton("Cursor (" + cursorNumber + ")");
		item1.setForeground(Color.white);
		item1.setBackground(Color.black);
		item1.setFocusPainted(false);
		item1.setFont(itemFont);
		item1.addActionListener(cHandler);
		item1.setActionCommand("cursor");
		item1.addMouseListener(mHandler);
		itemPanel.add(item1);
		item2 = new JButton("?");
		item2.setForeground(Color.white);
		item2.setBackground(Color.black);
		item2.setFocusPainted(false);
		item2.setFont(itemFont);
		item2.addActionListener(cHandler);
		item2.setActionCommand("grandma");
		item2.addMouseListener(mHandler);
		itemPanel.add(item2);
		item3 = new JButton("?");
		item3.setForeground(Color.white);
		item3.setBackground(Color.black);
		item3.setFocusPainted(false);
		item3.setFont(itemFont);
		item3.addMouseListener(mHandler);
		itemPanel.add(item3);
		item4 = new JButton("?");
		item4.setForeground(Color.white);
		item4.setBackground(Color.black);
		item4.setFocusPainted(false);
		item4.setFont(itemFont);
		item4.addMouseListener(mHandler);
		itemPanel.add(item4);
		
		messagePanel = new JPanel();
		messagePanel.setBounds(425, 25, 250, 90);
		messagePanel.setBackground(Color.black);
		window.add(messagePanel);
		
		messageArea = new JTextArea();
		messageArea.setBounds(425, 25, 250, 90);
		messageArea.setForeground(Color.white);
		messageArea.setBackground(Color.black);
		messageArea.setFont(messageFont);
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		messagePanel.add(messageArea);
		
		
		window.setVisible(true);
		
		
	}
	
	public void setTimer() {
		
		double speed = 1/perSecond * 1000;
		timerSpeed = (int)speed;
		String perSecondFormatted = String.format("%.1f", perSecond);
		perSecondLabel.setText("per second: " + perSecondFormatted);
		
		if(!timerOn) {
			timerOn = true;
		}
		else if(timerOn) {
			timer.stop();
		}
		
		timer = new Timer(timerSpeed, new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cookieCounter++;
				counterLabel.setText("Cookies: " + cookieCounter);
				grandmaUnlocked();
				
			}
		});
		
		timer.start();
		
	}
	
	public void grandmaUnlocked() {
		
		if(!grandmaUnlock) {
			if(cookieCounter >= grandmaUnlockPrice) {
				grandmaUnlock = true;
				item2.setText("Grandma (" + grandmaNumber + ")");
			}
		}
		
	}
	
	public class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			JButton button = (JButton)e.getSource();
			
			if(button == item1) {
				messageArea.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks the cookie every 10 seconds.");
			}
			else if(button == item2) {
				if(!grandmaUnlock) {
					messageArea.setText("\nItem is currently locked");
				}
				else if(grandmaUnlock) {
					messageArea.setText("Grandma\n[price: " + grandmaPrice + "]\nAutoclicks the cookie every second.");
				}
			}
			else if(button == item3 || button == item4) {
				messageArea.setText("\nItem is currently locked");
			}
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			JButton button = (JButton)e.getSource();
			if(button == item1 || button == item2 || button == item3 || button == item4) {
				messageArea.setText(null);
			}
		}
		
	}
	
	public class CookieHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			switch(command) {
			case "cookie" :
				cookieCounter++;
				counterLabel.setText("Cookies: " + cookieCounter);
				grandmaUnlocked();
				break;
			case "cursor":
				if(cookieCounter >= cursorPrice) {
					cursorNumber++;
					cookieCounter = cookieCounter - cursorPrice;
					cursorPrice = cursorPrice + 5;
					counterLabel.setText("Cookies: " + cookieCounter);
					item1.setText("Cursor (" + cursorNumber + ")");
					messageArea.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks the cookie every 10 seconds.");
					perSecond = perSecond + 0.1;
					setTimer();
				}
				else {
					messageArea.setText("\nYou need more cookies!");
				}
				
				break;
			case "grandma":
				if(grandmaUnlock) {
					if(cookieCounter >= grandmaPrice) {
						grandmaNumber++;
						cookieCounter = cookieCounter - grandmaPrice;
						grandmaPrice = grandmaPrice + 25;
						counterLabel.setText("Cookies: " + cookieCounter);
						item2.setText("Grandma (" + grandmaNumber + ")");
						messageArea.setText("Grandma\n[price: " + grandmaPrice + "]\nAutoclicks the cookie every second.");
						perSecond = perSecond + 1;
						setTimer();
					}
					else {
						messageArea.setText("\nYou need more cookies!");
				}
				}
				
				
				break;
			}
			
			
		}
		
	}

}
