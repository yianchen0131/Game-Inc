package gameCrossing.model;


import java.util.Date;


public class Game {	
	protected Integer gameId;
	protected String gameName;
	// 这里的类不重要, 可以在Dao中casting.
	protected Date dateReleased;
	protected Boolean isEnglish;
	protected String platforms;
	// call RequiredAge.ZERO.getValue() to get 0.
	protected RequiredAge requiredAge;
	public enum RequiredAge{
        ZERO("0"),
        SIXTEEN("16"),
        EIGHTEEN("18"),
        TWELVE("12"),
		SEVEN("7"),
		THREE("3");
        private final String value;
        RequiredAge(final String newValue) {
            value = newValue;
        }
        public String getValue() { 
        	return value; 
    	}
    }
	protected String categories;
	protected String genres;
	protected Integer numberOfAchievements;
	protected String numberOfOwners;
	protected Float price;
	
	
	public Game(Integer gameId, String gameName, Date dateReleased, Boolean isEnglish, String platforms,
			RequiredAge requiredAge, String categories, String genres, Integer numberOfAchievements,
			String numberOfOwners, Float price) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.dateReleased = dateReleased;
		this.isEnglish = isEnglish;
		this.platforms = platforms;
		this.requiredAge = requiredAge;
		this.categories = categories;
		this.genres = genres;
		this.numberOfAchievements = numberOfAchievements;
		this.numberOfOwners = numberOfOwners;
		this.price = price;
	}
	public Game(Integer gameId) {
		super();
		this.gameId = gameId;
	}
	public Game(String gameName, Date dateReleased, Boolean isEnglish, String platforms, RequiredAge requiredAge,
			String categories, String genres, Integer numberOfAchievements, String numberOfOwners, Float price) {
		super();
		this.gameName = gameName;
		this.dateReleased = dateReleased;
		this.isEnglish = isEnglish;
		this.platforms = platforms;
		this.requiredAge = requiredAge;
		this.categories = categories;
		this.genres = genres;
		this.numberOfAchievements = numberOfAchievements;
		this.numberOfOwners = numberOfOwners;
		this.price = price;
	}
	
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Date getDateReleased() {
		return dateReleased;
	}
	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}
	public Boolean getIsEnglish() {
		return isEnglish;
	}
	public void setIsEnglish(Boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	public String getPlatforms() {
		return platforms;
	}
	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}
	public RequiredAge getRequiredAge() {
		return requiredAge;
	}
	public void setRequiredAge(RequiredAge requiredAge) {
		this.requiredAge = requiredAge;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public Integer getNumberOfAchievements() {
		return numberOfAchievements;
	}
	public void setNumberOfAchievements(Integer numberOfAchievements) {
		this.numberOfAchievements = numberOfAchievements;
	}
	public String getNumberOfOwners() {
		return numberOfOwners;
	}
	public void setNumberOfOwners(String numberOfOwners) {
		this.numberOfOwners = numberOfOwners;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
}
