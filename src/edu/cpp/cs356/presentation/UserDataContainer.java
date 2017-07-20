package edu.cpp.cs356.presentation;

import java.util.LinkedList;
import java.util.Queue;

public class UserDataContainer {
	
	private boolean online;
	private UserDialog dialog;
	private Queue<String> msgs;

	public UserDataContainer(){
		this.msgs = new LinkedList<String>();
	}
	
	public Queue<String> getMsgs() {
		return msgs;
	}
	
	public UserDialog getDialog() {
		return dialog;
	}
	
	public void setDialog( UserDialog dialog ) {
		this.dialog = dialog;
	}

	public boolean isOnline() {
		return online;
	}
	
	public void setOnlineStatus(boolean status) {
		this.online = status;
	}
	
}
