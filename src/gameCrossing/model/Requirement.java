package gameCrossing.model;


public class Requirement {
	protected Integer requirementId;
	protected String pcRequire;
	protected String macRequire;
	protected String linuxRequire;
	protected String minimum_requirement;
	protected String recommended_requirement;
	protected Game game;
	
	
	public Requirement(Integer requirementId, String pcRequire, String macRequire, String linuxRequire,
			String minimum_requirement, String recommended_requirement, Game game) {
		super();
		this.requirementId = requirementId;
		this.pcRequire = pcRequire;
		this.macRequire = macRequire;
		this.linuxRequire = linuxRequire;
		this.minimum_requirement = minimum_requirement;
		this.recommended_requirement = recommended_requirement;
		this.game = game;
	}
	public Requirement(String pcRequire, String macRequire, String linuxRequire, String minimum_requirement,
			String recommended_requirement, Game game) {
		super();
		this.pcRequire = pcRequire;
		this.macRequire = macRequire;
		this.linuxRequire = linuxRequire;
		this.minimum_requirement = minimum_requirement;
		this.recommended_requirement = recommended_requirement;
		this.game = game;
	}
	public Requirement(Integer requirementId) {
		super();
		this.requirementId = requirementId;
	}
	
	
	public Integer getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(Integer requirementId) {
		this.requirementId = requirementId;
	}
	public String getPcRequire() {
		return pcRequire;
	}
	public void setPcRequire(String pcRequire) {
		this.pcRequire = pcRequire;
	}
	public String getMacRequire() {
		return macRequire;
	}
	public void setMacRequire(String macRequire) {
		this.macRequire = macRequire;
	}
	public String getLinuxRequire() {
		return linuxRequire;
	}
	public void setLinuxRequire(String linuxRequire) {
		this.linuxRequire = linuxRequire;
	}
	public String getMinimum_requirement() {
		return minimum_requirement;
	}
	public void setMinimum_requirement(String minimum_requirement) {
		this.minimum_requirement = minimum_requirement;
	}
	public String getRecommended_requirement() {
		return recommended_requirement;
	}
	public void setRecommended_requirement(String recommended_requirement) {
		this.recommended_requirement = recommended_requirement;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
