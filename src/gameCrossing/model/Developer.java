package gameCrossing.model;


public class Developer {
	protected Integer developerId;
	protected String developerName;
	// foreign key相当于另一个对象.
	protected Game game;
	
	
	public Developer(Integer developerId, String developerName, Game game) {
		super();
		this.developerId = developerId;
		this.developerName = developerName;
		this.game = game;
	}
	public Developer(Integer developerId) {
		super();
		this.developerId = developerId;
	}
	public Developer(String developerName, Game game) {
		super();
		this.developerName = developerName;
		this.game = game;
	}
	
	
	public Integer getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	public String getDeveloperName() {
		return developerName;
	}
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
