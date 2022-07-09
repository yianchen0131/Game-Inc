package gameCrossing.model;


public class Media {
	
	protected Integer mediaId;
	protected String headerImage;
	protected String screenshot;
	protected String background;
	protected String movie;
	protected Game game;
	
	
	public Media(Integer mediaId, String headerImage, String screenshot, String background, String movie, Game game) {
		super();
		this.mediaId = mediaId;
		this.headerImage = headerImage;
		this.screenshot = screenshot;
		this.background = background;
		this.movie = movie;
		this.game = game;
	}
	public Media(String headerImage, String screenshot, String background, String movie, Game game) {
		super();
		this.headerImage = headerImage;
		this.screenshot = screenshot;
		this.background = background;
		this.movie = movie;
		this.game = game;
	}
	public Media(Integer mediaId) {
		super();
		this.mediaId = mediaId;
	}
	
	
	public Integer getMediaId() {
		return mediaId;
	}
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	public String getHeaderImage() {
		return headerImage;
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	public String getScreenshot() {
		return screenshot;
	}
	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
