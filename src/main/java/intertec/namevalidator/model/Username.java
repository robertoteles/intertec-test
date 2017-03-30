package intertec.namevalidator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author roberto
 *
 */
@Entity
@Table(name="username")
public class Username implements Comparable<Username>{

	
	
	public Username() {
		super();
	}

	public Username(String name) {
		super();
		this.name = name;
	}
	
	@Id @Column(nullable=false, length=150) 
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Username [name=" + name + "]";
	}
	
	/**
	 * 
	 */
	public int compareTo(Username o) {
		 return this.name.compareTo(o.name);
	}
	
	
}
