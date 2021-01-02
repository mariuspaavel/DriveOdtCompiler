package com.mariuspaavel.driveodtcompiler;

import java.util.HashMap;

public class DriveConnection {
	private SessionInfo sess;
	
	public DriveConnection(SessionInfo sess) {
		this.sess = sess;
	}
	
	public Item[] list_dir(Item dir) throws Exception {
		String q = "'root'+in+parents+and+trashed+=+false";
		if (dir != null) q = String.format("'%s'+in+parents", dir.getId());
		return filesystem_query(q);
	}
	
	public Item[] filesystem_query(String q) throws Exception {
		String access_token = sess.token.getAccess_token();
		var params = new HashMap<String, String>();
		//params.put("access_token", access_token);
		params.put("q", q);
		//String fields = "'id,+name,+mimeType'";
		//params.put("fields", fields);
		String httpresponse = HttpReqSync.get("https://www.googleapis.com/drive/v3/files", params, sess);
		
		try {
			DriveQuery rs = Utils.objectMapper.readValue(httpresponse, DriveQuery.class);
			return rs.getFiles();
		}
		catch(Exception e) {
			var e1 = new DriveErrorException(httpresponse, e);
			e1.initCause(e);
			throw e1;
		}
		
	}
	public Item get_item(String id) throws Exception {
		String access_token = sess.token.getAccess_token();
		var params = new HashMap<String, String>();
		
		//params.put("access_token", access_token);
		
		//String fields = "'id,+name,+mimeType'";
		//params.put("fields", fields);
		String httpresponse = HttpReqSync.get("https://www.googleapis.com/drive/v3/files/"+id, params, sess);
		try {
			Item resp = Utils.objectMapper.readValue(httpresponse, Item.class);
			return resp;
		}catch(Exception e) {
			var e1 = new DriveErrorException(httpresponse, e);
			e1.initCause(e);
			throw e1;
		}
		
	}
	public byte[] downloadFile(Item item) throws Exception {
		String access_token = sess.token.getAccess_token();
		var params = new HashMap<String, String>();
		//params.put("access_token", access_token);
		params.put("alt", "media");
		//params.put("responseType", "stream");
		//String fields = "'id,+name,+mimeType'";
		//params.put("fields", fields);
		byte[] httpresponse = HttpReqSync.download("https://www.googleapis.com/drive/v3/files/"+item.getId(), params, sess);
		
		return httpresponse;
	}
}
