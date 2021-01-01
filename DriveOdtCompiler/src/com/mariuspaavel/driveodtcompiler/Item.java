package com.mariuspaavel.driveodtcompiler;


public class Item {
	private String id;
	private String name;
	private String mimeType;
	private String kind;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	
	
	
	/*
	public Item (JSONObject json) throws DriveResponseParsingException {
		try {
			id = json.get("id").toString();
			name = json.get("name").toString();
			mime = json.get("mimeType").toString();
		}catch(NullPointerException e) {
			throw new DriveResponseParsingException();
		}
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("mimeType", mime);
		return json;
	}
	public static JSONArray arrayToJSON(Item[] input) {
		JSONArray output = new JSONArray();
		for(Item i : input)output.add(i.toJson());
		return output;
	}
	@Override
	public String toString() {
		return toJson().toString();
	}
	
	public static Item[] parseItemListResponse(JSONArray array) throws DriveResponseParsingException {
		Item[] result = new Item[array.size()];
		try {
			for(int i = 0; i< array.size(); i++) {
				result[i] = new Item((JSONObject)array.get(i));
			}
			return result;
		}catch(ClassCastException | NullPointerException e) {
			throw new DriveResponseParsingException();
		}
	}
	*/
}
