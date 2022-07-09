package gameCrossing.model;


public class Rating {
	protected Integer ratingId;
	protected Integer numberOfPositiveRatings;
	protected Integer numberOfNegativeRatings;
	protected Game game;
	
	
	public Rating(Integer ratingId, Integer numberOfPositiveRatings, Integer numberOfNegativeRatings, Game game) {
		super();
		this.ratingId = ratingId;
		this.numberOfPositiveRatings = numberOfPositiveRatings;
		this.numberOfNegativeRatings = numberOfNegativeRatings;
		this.game = game;
	}
	public Rating(Integer numberOfPositiveRatings, Integer numberOfNegativeRatings, Game game) {
		super();
		this.numberOfPositiveRatings = numberOfPositiveRatings;
		this.numberOfNegativeRatings = numberOfNegativeRatings;
		this.game = game;
	}
	public Rating(Integer ratingId) {
		super();
		this.ratingId = ratingId;
	}
	
	
	public Integer getRatingId() {
		return ratingId;
	}
	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}
	public Integer getNumberOfPositiveRatings() {
		return numberOfPositiveRatings;
	}
	public void setNumberOfPositiveRatings(Integer numberOfPositiveRatings) {
		this.numberOfPositiveRatings = numberOfPositiveRatings;
	}
	public Integer getNumberOfNegativeRatings() {
		return numberOfNegativeRatings;
	}
	public void setNumberOfNegativeRatings(Integer numberOfNegativeRatings) {
		this.numberOfNegativeRatings = numberOfNegativeRatings;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
