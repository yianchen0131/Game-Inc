package gameCrossing.model;


public class Support {
	protected Integer supportId;
	protected String website;
	protected String supportUrl;
	protected String supportEmail;
	protected Game game;
	
	
	public Support(Integer supportId, String website, String supportUrl, String supportEmail, Game game) {
		super();
		this.supportId = supportId;
		this.website = website;
		this.supportUrl = supportUrl;
		this.supportEmail = supportEmail;
		this.game = game;
	}
	public Support(String website, String supportUrl, String supportEmail, Game game) {
		super();
		this.website = website;
		this.supportUrl = supportUrl;
		this.supportEmail = supportEmail;
		this.game = game;
	}
	public Support(Integer supportId) {
		super();
		this.supportId = supportId;
	}
	
	
	public Integer getSupportId() {
		return supportId;
	}
	public void setSupportId(Integer supportId) {
		this.supportId = supportId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSupportUrl() {
		return supportUrl;
	}
	public void setSupportUrl(String supportUrl) {
		this.supportUrl = supportUrl;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
