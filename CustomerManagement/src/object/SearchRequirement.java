package object;

public class SearchRequirement {
	private String requirementContent;
	private String requirementText;
	private String requirementSelect;

	public SearchRequirement(String requirementContent, String requirementText, String requirementSelect) {
		this.requirementContent = requirementContent;
		this.requirementText    = requirementText;
		this.requirementSelect  = requirementSelect;
	}

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public String getRequirementText() {
		return requirementText;
	}

	public void setRequirementText(String requirementText) {
		this.requirementText = requirementText;
	}

	public String getRequirementSelect() {
		return requirementSelect;
	}

	public void setRequirementSelect(String requirementSelect) {
		this.requirementSelect = requirementSelect;
	}
}
