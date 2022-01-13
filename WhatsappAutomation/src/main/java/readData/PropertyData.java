package readData;

public class PropertyData {
	private  String photoLocation;
	private  String photoEnd;
	private  String URL;
	private String dashboardURL;
	private  String waitSecLogin;
	private String waitPhotoCrop;
	private String statusToSearch;
	private String db;
	private  String DBURL;
	private  String DBPort;
	private String DBName;
	private  String username;
	private  String password;
	private String maleGender;
	private String femaleGender;
	private String marriedId;
	private String unmarriedId;
	private String appUsername;
	private String appPassword;
	private String gamcaUsername;
	private String gamcaPassword;
	private boolean debug;
	
	public  String getPhotoLocation() {
		return photoLocation;
	}
	public  void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}
	public  String getPhotoEnd() {
		return photoEnd;
	}
	public  void setPhotoEnd(String photoEnd) {
		this.photoEnd = photoEnd;
	}
	public  String getURL() {
		return URL;
	}
	public  void setURL(String uRL) {
		this.URL = uRL;
	}
	public String getDashboardURL() {
		return dashboardURL;
	}
	public void setDashboardURL(String dashboardURL) {
		this.dashboardURL = dashboardURL;
	}
	public  String getWaitSecLogin() {
		return waitSecLogin;
	}
	public  void setWaitSecLogin(String waitSecLogin) {
		this.waitSecLogin = waitSecLogin;
	}
	public String getWaitPhotoCrop() {
		return waitPhotoCrop;
	}
	public void setWaitPhotoCrop(String waitPhotoCrop) {
		this.waitPhotoCrop = waitPhotoCrop;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public  String getDBURL() {
		return DBURL;
	}
	public  void setDBURL(String dBURL) {
		this.DBURL = dBURL;
	}
	public  String getDBPort() {
		return DBPort;
	}
	public  void setDBPort(String dBPort) {
		this.DBPort = dBPort;
	}
	public String getDBName() {
		return DBName;
	}
	public void setDBName(String dBName) {
		DBName = dBName;
	}
	public  String getUsername() {
		return username;
	}
	public  void setUsername(String username) {
		this.username = username;
	}
	public  String getPassword() {
		return password;
	}
	public  void setPassword(String password) {
		this.password = password;
	}
	public String getMaleGender() {
		return maleGender;
	}
	public void setMaleGender(String maleGender) {
		this.maleGender = maleGender;
	}
	public String getFemaleGender() {
		return femaleGender;
	}
	public void setFemaleGender(String femaleGender) {
		this.femaleGender = femaleGender;
	}
	public String getMarriedId() {
		return marriedId;
	}
	public void setMarriedId(String marriedId) {
		this.marriedId = marriedId;
	}
	public String getUnmarriedId() {
		return unmarriedId;
	}
	public void setUnmarriedId(String unmarriedId) {
		this.unmarriedId = unmarriedId;
	}
	public String getStatusToSearch() {
		return statusToSearch;
	}
	public void setStatusToSearch(String statusToSearch) {
		if(statusToSearch.equalsIgnoreCase("new"))
			this.statusToSearch = "New";
		else 
			this.statusToSearch = "All";
	}
	public String getAppUsername() {
		return appUsername;
	}
	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}
	public String getAppPassword() {
		return appPassword;
	}
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	public String getGamcaUsername() {
		return gamcaUsername;
	}
	public void setGamcaUsername(String gamcaUsername) {
		this.gamcaUsername = gamcaUsername;
	}
	public String getGamcaPassword() {
		return gamcaPassword;
	}
	public void setGamcaPassword(String gamcaPassword) {
		this.gamcaPassword = gamcaPassword;
	}
	public boolean getDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	
	
	
	
}
