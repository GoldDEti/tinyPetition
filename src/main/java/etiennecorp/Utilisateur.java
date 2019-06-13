package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Utilisateur {

	@Id Long id;
	String name;
	String firstName;
	@Index String pseudo;
	
	private Utilisateur(){};
	
	public Utilisateur(String name, String firstName, String pseudo)
	{
		this.name = name;
		this.firstName = firstName;
		this.pseudo = pseudo;
	}
}
