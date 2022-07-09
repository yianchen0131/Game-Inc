package gameCrossing.model;


public class Playtime {
	protected Integer playtimeId;
	protected Integer averagePlaytime;
	protected Integer medianPlaytime;
	protected Game game;
	
	
	public Playtime(Integer playtimeId, Integer averagePlaytime, Integer medianPlaytime, Game game) {
		super();
		this.playtimeId = playtimeId;
		this.averagePlaytime = averagePlaytime;
		this.medianPlaytime = medianPlaytime;
		this.game = game;
	}
	public Playtime(Integer averagePlaytime, Integer medianPlaytime, Game game) {
		super();
		this.averagePlaytime = averagePlaytime;
		this.medianPlaytime = medianPlaytime;
		this.game = game;
	}
	public Playtime(Integer playtimeId) {
		super();
		this.playtimeId = playtimeId;
	}
	
	
	public Integer getPlaytimeId() {
		return playtimeId;
	}
	public void setPlaytimeId(Integer playtimeId) {
		this.playtimeId = playtimeId;
	}
	public Integer getAveragePlaytime() {
		return averagePlaytime;
	}
	public void setAveragePlaytime(Integer averagePlaytime) {
		this.averagePlaytime = averagePlaytime;
	}
	public Integer getMedianPlaytime() {
		return medianPlaytime;
	}
	public void setMedianPlaytime(Integer medianPlaytime) {
		this.medianPlaytime = medianPlaytime;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
