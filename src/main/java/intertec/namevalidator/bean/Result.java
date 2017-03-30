package intertec.namevalidator.bean;

import java.util.List;

import intertec.namevalidator.model.Username;

public class Result {
	private Boolean isValid = Boolean.FALSE;
	private List<Username> usernames;
	private String message;

	/**
	 * 
	 * @return
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * 
	 * @param isValid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public List<Username> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<Username> usernames) {
		this.usernames = usernames;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
