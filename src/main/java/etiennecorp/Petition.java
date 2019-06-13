package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Petition {
	@Id Long id;
	@Index String name;
	String description;
	//utilisateur[] lstSignature;

	private Petition(){};
	
	public Petition(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
}
