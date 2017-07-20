package edu.cpp.cs356.presentation;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Mediator {
	
	private Map< String, UserDataContainer> users;
	private GUI gui;
	
	public Mediator(GUI gui){
		this.gui = gui;
		users = new HashMap<>(); 
	}
	
	public void registerUser( String id ){
		if ( !users.containsKey(id) ){
			users.put(id, new UserDataContainer());
		}
	}

	public void setUserStatus( String id, boolean status ){
		users.get(id).setOnlineStatus(status);
		gui.updateStatus();
	}
	
	public void setDialog( String id, UserDialog dialog ){
		users.get(id).setDialog(dialog);
	}
	
	public void broadcast( String msg ){
		for ( UserDataContainer u : users.values() ){
			if ( u.isOnline() ){
				u.getDialog().receiveMsg(msg);
			} else {
				u.getMsgs().add(msg);
			}
		}
	}
	
	public boolean getUserStatus( String id ){
		UserDataContainer u = users.get(id);
		if ( u == null ){
			System.out.println("UserID not found.");
			return false;
		}
		return users.get(id).isOnline();
	}
	
	public void updateMsgs(String id) {
		Queue<String> msgs = users.get(id).getMsgs();
		while ( !msgs.isEmpty() ){
			users.get(id).getDialog().receiveMsg(msgs.poll());
		}
	}
	
}
