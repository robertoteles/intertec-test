package intertec.namevalidator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suggestedword")
public class SuggestedWord {

	private static final long serialVersionUID = 1L;

	public SuggestedWord() {
		super();
	}

	public SuggestedWord(String word) {
		super();
		this.word = word;
	}

	@Id
	@Column(nullable = false, length = 150)
	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
