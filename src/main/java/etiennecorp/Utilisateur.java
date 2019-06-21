package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Utilisateur {

	@Id long id;
	String name;
	String firstName;
	@Index String pseudo;
	@Index String email;
	
	private Utilisateur(){};
	
	public Utilisateur(String name, String firstName, String pseudo, String email)
	{
		this.name = name;
		this.firstName = firstName;
		this.pseudo = pseudo;
		this.email = email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
}
