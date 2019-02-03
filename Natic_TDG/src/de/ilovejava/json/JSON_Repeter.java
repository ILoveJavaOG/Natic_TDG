package de.ilovejava.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import de.ilovejava.skin.API_Skin;
import de.ilovejava.uuid.uuidfetcher;

public class JSON_Repeter {
	JSONObject decs;
	JSONObject decs_Walker;
	JSONObject decs_Final;
	String jstring;
	String player_Name;
	String skin_Value;
	String uuid;
	String url_Final;
	public JSON_Repeter(String name) {
		player_Name = name;
		uuid = uuidfetcher.getUUID(name).toString().replace("-", "");
		getSkinValue();
	}
	
	private void getSkinValue() {
		API_Skin skin = new API_Skin(uuid);
		skin_Value = skin.getSkinValue();
		decode();
	}
	
	private void decode() {
		jstring = Base64Coder.decodeString(skin_Value);
		getJSONUrl();
	}
	
	private void getJSONUrl() {
		try {
			decs = (JSONObject) new JSONParser().parse(jstring);
			if(decs.containsKey("textures")) {
				 decs_Walker = (JSONObject) decs.get("textures");
				if(decs_Walker.containsKey("SKIN")) {
					decs_Final = (JSONObject) decs_Walker.get("SKIN");
					url_Final = decs_Final.get("url").toString();
				}else {url_Final = null;}
			}else {
				url_Final = null;
			}
		} catch (ParseException e) {e.printStackTrace();}
	}
	
	public String getSkinUrl() {
		return url_Final;
	}
}
