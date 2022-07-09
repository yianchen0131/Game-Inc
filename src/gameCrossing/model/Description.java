package gameCrossing.model;


public class Description {
	protected Integer descripId;
	protected String detailedDescription;
	protected String aboutTheGame;
	protected String shortDescription;
	protected Game game;
	
	
	public Description(Integer descripId, String detailedDescription, String aboutTheGame, String shortDescription,
			Game game) {
		super();
		this.descripId = descripId;
		this.detailedDescription = detailedDescription;
		this.aboutTheGame = aboutTheGame;
		this.shortDescription = shortDescription;
		this.game = game;
	}
	public Description(Integer descripId) {
		super();
		this.descripId = descripId;
	}
	public Description(String detailedDescription, String aboutTheGame, String shortDescription, Game game) {
		super();
		this.detailedDescription = detailedDescription;
		this.aboutTheGame = aboutTheGame;
		this.shortDescription = shortDescription;
		this.game = game;
	}
	
	
	public Integer getDescripId() {
		return descripId;
	}
	public void setDescripId(Integer descripId) {
		this.descripId = descripId;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public String getAboutTheGame() {
		return aboutTheGame;
	}
	public void setAboutTheGame(String aboutTheGame) {
		this.aboutTheGame = aboutTheGame;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
