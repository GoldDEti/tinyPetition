package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Petition {
	@Id
	Long id;
	String name;
	String description;
	@Index
	int nbSignature;
	@Index
	String idCreateur;

	public Petition() {
	};

	public Petition(String name, String description, String idCreateur) {
		this.name = name;
		this.description = description;
		this.idCreateur = idCreateur;
	}
}
