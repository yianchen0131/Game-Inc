package gameCrossing.model;


public class Tag {
	protected Integer tagId;
	protected Integer _1980s;
	protected Integer _1990s;
	protected Integer _2_5d;
	protected Integer _2d;
	protected Integer _3d;
	protected Game game;
	
	
	public Tag(Integer tagId, Integer _1980s, Integer _1990s, Integer _2_5d, Integer _2d, Integer _3d, Game game) {
		super();
		this.tagId = tagId;
		this._1980s = _1980s;
		this._1990s = _1990s;
		this._2_5d = _2_5d;
		this._2d = _2d;
		this._3d = _3d;
		this.game = game;
	}
	public Tag(Integer _1980s, Integer _1990s, Integer _2_5d, Integer _2d, Integer _3d, Game game) {
		super();
		this._1980s = _1980s;
		this._1990s = _1990s;
		this._2_5d = _2_5d;
		this._2d = _2d;
		this._3d = _3d;
		this.game = game;
	}
	public Tag(Integer tagId) {
		super();
		this.tagId = tagId;
	}
	
	
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public Integer get_1980s() {
		return _1980s;
	}
	public void set_1980s(Integer _1980s) {
		this._1980s = _1980s;
	}
	public Integer get_1990s() {
		return _1990s;
	}
	public void set_1990s(Integer _1990s) {
		this._1990s = _1990s;
	}
	public Integer get_2_5d() {
		return _2_5d;
	}
	public void set_2_5d(Integer _2_5d) {
		this._2_5d = _2_5d;
	}
	public Integer get_2d() {
		return _2d;
	}
	public void set_2d(Integer _2d) {
		this._2d = _2d;
	}
	public Integer get_3d() {
		return _3d;
	}
	public void set_3d(Integer _3d) {
		this._3d = _3d;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
