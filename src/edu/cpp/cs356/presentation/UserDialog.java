package edu.cpp.cs356.presentation;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Insets;

public class UserDialog {

	private JFrame frame;
	private JTextField txtMsg;
	private Mediator mediator;
	private DefaultListModel<String> model;
	private String id;
	private JButton btnBroadcast;
	
	/**
	 * Create the application.
	 * @param string 
	 */
	public UserDialog(Mediator mediator, String id) {
		
		this.id = id;
		this.mediator = mediator;
		
		initialize();
		
//		mediator.setUserStatus(id, true);
		mediator.setDialog(id, this);
//		mediator.updateMsgs(id);
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediator.setUserStatus(id, false);
			}
		});
		frame.setBounds(100, 100, 370, 232);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("User " + id);
		frame.getContentPane().setLayout(null);
		
		model = new DefaultListModel<>();
		JList<String> list = new JList<>( model );
		list.setBounds(10, 11, 336, 142);
		frame.getContentPane().add(list);
		
		txtMsg = new JTextField();
		txtMsg.setEnabled(false);
		txtMsg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBroadcast.doClick();
				}
			}
		});
		txtMsg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMsg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMsg.getText().trim().equals(""))
					txtMsg.setText("Enter message here");
			}
			@Override
			public void focusGained(FocusEvent e) {
				if( txtMsg.getText().trim().equals("Enter message here"))
					txtMsg.setText("");
			}
		});
		txtMsg.setText("Enter message here");
		txtMsg.setBounds(80, 164, 196, 20);
		frame.getContentPane().add(txtMsg);
		txtMsg.setColumns(10);
		
		btnBroadcast = new JButton("Broadcast");
		btnBroadcast.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBroadcast.setEnabled(false);
		btnBroadcast.setMargin(new Insets(2, 2, 2, 2));
		btnBroadcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				broadcastMsg("User " + id + ": " + txtMsg.getText());
				txtMsg.setText("");
			}
		});
		btnBroadcast.setBounds(286, 163, 60, 23);
		frame.getContentPane().add(btnBroadcast);
		
		JButton btnOnlineToggle = new JButton("Go Online");
		btnOnlineToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleOnline();
			}

			private void toggleOnline() {
				if ( mediator.getUserStatus(id) ){
					mediator.setUserStatus(id, false);
					txtMsg.setEnabled(false);
					btnBroadcast.setEnabled(false);
					btnOnlineToggle.setText("Go Online");
				} else {
					mediator.setUserStatus(id, true);
					txtMsg.setEnabled(true);
					btnBroadcast.setEnabled(true);
					mediator.updateMsgs(id);
					btnOnlineToggle.setText("Go Offline");
				}
			}
		});
		btnOnlineToggle.setMargin(new Insets(2, 2, 2, 2));
		btnOnlineToggle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOnlineToggle.setBounds(10, 164, 60, 23);
		frame.getContentPane().add(btnOnlineToggle);
	}
	
	private void broadcastMsg( String msg ){
		mediator.broadcast( msg );
	}
	
	public void receiveMsg( String msg ){
		model.addElement(msg);
	}
}
