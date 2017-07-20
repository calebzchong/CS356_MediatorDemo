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

public class UserDialog {

	private JFrame frame;
	private JTextField txtMsg;
	private Mediator mediator;
	private DefaultListModel<String> model;
	private String id;
	JButton btnBroadcast;
	
	/**
	 * Create the application.
	 * @param string 
	 */
	public UserDialog(Mediator mediator, String id) {
		
		this.id = id;
		this.mediator = mediator;
		
		initialize();
		
		mediator.setUserStatus(id, true);
		mediator.setDialog(id, this);
		mediator.updateMsgs(id);
		
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
		txtMsg.setBounds(10, 164, 237, 20);
		frame.getContentPane().add(txtMsg);
		txtMsg.setColumns(10);
		
		btnBroadcast = new JButton("Broadcast");
		btnBroadcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				broadcastMsg(txtMsg.getText());
				txtMsg.setText("");
			}
		});
		btnBroadcast.setBounds(257, 163, 89, 23);
		frame.getContentPane().add(btnBroadcast);
	}
	
	private void broadcastMsg( String msg ){
		mediator.broadcast( msg );
	}
	
	public void receiveMsg( String msg ){
		model.addElement(msg);
	}
}
