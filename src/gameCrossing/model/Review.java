package gameCrossing.model;


import java.util.Date;


public class Review {
	protected Integer reviewId;
	protected Date datePosted; 
	protected Long funny;
	protected Integer helpful;
	protected Integer hourPlayed;
	protected Boolean isEarlyAccessed;
	protected Recommendation recommendation;
	// call Recommendation.RECOMMENDED.getValue() to get "Recommended".
	public enum Recommendation {
		RECOMMENDED("Recommended"), 
		NOTRECOMMENDED("NotRecommended");
		private final String value;
		Recommendation(final String newValue) {
			value = newValue;
		}
		public String getValue() {
			return value;
		}
	}
	protected String writtenContent;
	protected Game game;
	protected User user;
	
	
	public Review(Integer reviewId, Date datePosted, Long funny, Integer helpful, Integer hourPlayed,
			Boolean isEarlyAccessed, Recommendation recommendation, String writtenContent, Game game, User user) {
		super();
		this.reviewId = reviewId;
		this.datePosted = datePosted;
		this.funny = funny;
		this.helpful = helpful;
		this.hourPlayed = hourPlayed;
		this.isEarlyAccessed = isEarlyAccessed;
		this.recommendation = recommendation;
		this.writtenContent = writtenContent;
		this.game = game;
		this.user = user;
	}
	public Review(Date datePosted, Long funny, Integer helpful, Integer hourPlayed, Boolean isEarlyAccessed,
			Recommendation recommendation, String writtenContent, Game game, User user) {
		super();
		this.datePosted = datePosted;
		this.funny = funny;
		this.helpful = helpful;
		this.hourPlayed = hourPlayed;
		this.isEarlyAccessed = isEarlyAccessed;
		this.recommendation = recommendation;
		this.writtenContent = writtenContent;
		this.game = game;
		this.user = user;
	}
	public Review(Integer reviewId) {
		super();
		this.reviewId = reviewId;
	}
	
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public Long getFunny() {
		return funny;
	}
	public void setFunny(Long funny) {
		this.funny = funny;
	}
	public Integer getHelpful() {
		return helpful;
	}
	public void setHelpful(Integer helpful) {
		this.helpful = helpful;
	}
	public Integer getHourPlayed() {
		return hourPlayed;
	}
	public void setHourPlayed(Integer hourPlayed) {
		this.hourPlayed = hourPlayed;
	}
	public Boolean getIsEarlyAccessed() {
		return isEarlyAccessed;
	}
	public void setIsEarlyAccessed(Boolean isEarlyAccessed) {
		this.isEarlyAccessed = isEarlyAccessed;
	}
	public Recommendation getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}
	public String getWrittenContent() {
		return writtenContent;
	}
	public void setWrittenContent(String writtenContent) {
		this.writtenContent = writtenContent;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
