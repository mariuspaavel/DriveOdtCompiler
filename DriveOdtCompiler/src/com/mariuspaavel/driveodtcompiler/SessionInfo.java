package com.mariuspaavel.driveodtcompiler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SessionInfo {
	GoogleAccessToken token;
	
	private DriveConnection conn;
	
	long expiration_ts;
	String userKey;
	
	
	
	private String workDirId;
	private ArrayList<Item> workPath;
	
	
	Item[] compilelist;
	
	public String getPathString() {
		StringBuilder output = new StringBuilder("/");
		for(int i = 0; i < workPath.size(); i++) {
			output.append(workPath.get(i).getName());
			if(i != workPath.size()-1)output.append('/');
		}
		return output.toString();
	}
	
	public void change_directory(Item dir) {
		workPath.add(dir);
	}
	public void change_to_parent_dir() {
		if(workPath.size() == 0)return;
		workPath.remove(workPath.size()-1);
	}
	public Item getWorkDir() {
		if(workPath.size() == 0) {
			return null;
		}
		return workPath.get(workPath.size()-1);
	}
	

	
	public SessionInfo(String json) throws JsonMappingException, JsonProcessingException {
		token = Utils.objectMapper.readValue(json, GoogleAccessToken.class);
		
		expiration_ts = System.currentTimeMillis() + token.getExpires_in()*1000;
		
		workPath = new ArrayList<Item>();
		
		compilelist = new Item[0];
		
		conn = new DriveConnection(this);
		//userKey = (long)(Long.MAX_VALUE * Math.random());
	}
	
	
	public void updateCompileList(Item[] updatedlist) {
		this.compilelist = updatedlist;
	}
	
	public DriveConnection getDriveConnection() {
		return conn;
	}
	
	
	private static HashMap<String, SessionInfo> sessions = new HashMap<String, SessionInfo>();
	
	public static String addSession(SessionInfo sessionInfo) {
		do {
			sessionInfo.userKey = Long.toString((long)(Long.MAX_VALUE * Math.random()));
		}while(sessions.containsKey(sessionInfo.userKey));
		sessions.put(sessionInfo.userKey, sessionInfo);
		return sessionInfo.userKey;
	}
	public static SessionInfo getSession(String key) {
		return sessions.get(key);
	}
	private static void update() {
		long timestamp = System.currentTimeMillis();
		for(String key : sessions.keySet()) {
			SessionInfo si = sessions.get(key);
			if(si.expiration_ts - 10000 < timestamp) {
				sessions.remove(key);
			}
		}
	}
	
	private static Thread updater = new Thread(() -> {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update();
		
	});
	static {
		updater.start();
	}
	

	
}
