package de.ilovejava.updatecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import de.ilovejava.utils.Util_Utils;

public class Update {
	Integer id;
	URL resource;
	String version;
	
	public Update(Integer i) {
		version = Util_Utils.getInstance().getDescription().getVersion();
		id = i;
		getVersion();
	}
	
	private void getVersion() {
		try {
			resource = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id);
		} catch (MalformedURLException e) {e.printStackTrace();}
	}
	
	public int getID() {
		return id;
	}
	
	public String getLastVersion() {
		return version;
	}
	
	public String getURL() {
		return "https://www.spigotmc.org/resources/" + id;
	}
	
	public boolean checkUpdate() throws IOException {
		URLConnection con;
		con = resource.openConnection();
		this.version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
		return !Util_Utils.getInstance().getDescription().getVersion().equals(version);
	}
}
