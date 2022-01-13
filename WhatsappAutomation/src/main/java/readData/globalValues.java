package readData;

public class globalValues {
	private static String webURL="https://web.whatsapp.com";
	private static String seperator=",";
	private static String propertyFileName="data.properties";
	private static String logs="./automation.logs";
	private static String hashFile= "automation.lic";
	private static String version= "1.0.001";
	public static String getWebURL() {
		return webURL;
	}
	public static String getSeperator() {
		return seperator;
	} 
	public static String getPropertyFileName() {
		return propertyFileName;
	}
	public static String getLogs() {
		return logs;
	}
	public static String getHashFile() {
		return hashFile;
	}
	public static String getVersion() {
		return version;
	}
	
}
