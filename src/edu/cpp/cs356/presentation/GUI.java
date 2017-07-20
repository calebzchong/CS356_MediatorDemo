package edu.cpp.cs356.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Font;

public class GUI {

	private JFrame frmMediatorDemo;
	private Mediator mediator;
	private JLabel[] statusLabels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMediatorDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMediatorDemo = new JFrame();
		frmMediatorDemo.setResizable(false);
		frmMediatorDemo.setTitle("Mediator Demo");
		frmMediatorDemo.setBounds(100, 100, 425, 177);
		frmMediatorDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMediatorDemo.getContentPane().setLayout(null);
		
		// INITIALIZE MEDIATOR
		mediator = new Mediator(this);
		for ( int i = 1; i <= 4; i++){
			mediator.registerUser(""+i);
		}
		
		JButton btnOpenUser1 = new JButton("Open View");
		btnOpenUser1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOpenUser1.setMargin(new Insets(2, 2, 2, 2));
		btnOpenUser1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserDialog(mediator, "1");
			}
		});
		btnOpenUser1.setBounds(10, 61, 89, 23);
		frmMediatorDemo.getContentPane().add(btnOpenUser1);
		
		JButton btnOpenUser2 = new JButton("Open View");
		btnOpenUser2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOpenUser2.setMargin(new Insets(2, 2, 2, 2));
		btnOpenUser2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserDialog(mediator, "2");
			}
		});
		btnOpenUser2.setBounds(109, 61, 89, 23);
		frmMediatorDemo.getContentPane().add(btnOpenUser2);
		
		JButton btnOpenUser3 = new JButton("Open View");
		btnOpenUser3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOpenUser3.setMargin(new Insets(2, 2, 2, 2));
		btnOpenUser3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserDialog(mediator, "3");
			}
		});
		btnOpenUser3.setBounds(208, 61, 89, 23);
		frmMediatorDemo.getContentPane().add(btnOpenUser3);
		
		JButton btnOpenUser4 = new JButton("Open View");
		btnOpenUser4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOpenUser4.setMargin(new Insets(2, 2, 2, 2));
		btnOpenUser4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserDialog(mediator, "4");
			}
		});
		btnOpenUser4.setBounds(307, 61, 89, 23);
		frmMediatorDemo.getContentPane().add(btnOpenUser4);
		
		JLabel lblUser1 = new JLabel("User 1");
		lblUser1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser1.setBounds(10, 11, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser1);
		
		JLabel lblUser2 = new JLabel("User 2");
		lblUser2.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser2.setBounds(109, 11, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser2);
		
		JLabel lblUser3 = new JLabel("User 3");
		lblUser3.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser3.setBounds(208, 11, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser3);
		
		JLabel lblUser4 = new JLabel("User 4");
		lblUser4.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser4.setBounds(307, 11, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser4);
		
		JLabel lblUser1Status = new JLabel("Offline");
		lblUser1Status.setForeground(new Color(220, 20, 60));
		lblUser1Status.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser1Status.setBounds(10, 36, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser1Status);
		
		JLabel lblUser2Status = new JLabel("Offline");
		lblUser2Status.setForeground(new Color(220, 20, 60));
		lblUser2Status.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser2Status.setBounds(109, 36, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser2Status);
		
		JLabel lblUser3Status = new JLabel("Offline");
		lblUser3Status.setForeground(new Color(220, 20, 60));
		lblUser3Status.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser3Status.setBounds(208, 36, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser3Status);
		
		JLabel lblUser4Status = new JLabel("Offline");
		lblUser4Status.setForeground(new Color(220, 20, 60));
		lblUser4Status.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser4Status.setBounds(307, 36, 89, 14);
		frmMediatorDemo.getContentPane().add(lblUser4Status);
		
		statusLabels = new JLabel[]{ lblUser1Status, lblUser2Status, lblUser3Status, lblUser4Status };
		
		JLabel lblNewLabel = new JLabel("<html>In this demo, a Mediator class is used to facilitate communications between the four User dialog boxes.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 95, 386, 38);
		frmMediatorDemo.getContentPane().add(lblNewLabel);
	}

	public void updateStatus() {
		for ( int i = 1; i <= statusLabels.length; i++ ){
			if ( mediator.getUserStatus(""+i) ){
				setLabelOnline(statusLabels[i-1]);
			} else {
				setLabelOffline(statusLabels[i-1]);
			}
		}
	}

	private void setLabelOffline(JLabel jLabel) {
		jLabel.setText("Online");
		jLabel.setForeground(new Color(220, 20, 60));	
	}

	private void setLabelOnline(JLabel jLabel) {
		jLabel.setText("Online");
		jLabel.setForeground(new Color(50, 205, 50));
	}
}
