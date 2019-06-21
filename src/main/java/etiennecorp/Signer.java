package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Signer {
	@Id
	Long id;
	@Index
	String idUser;
	@Index
	String idPetition;

	public Signer() {
	}

	public Signer(String idUser, String idPetition) {
		this.idUser = idUser;
		this.idPetition = idPetition;
	}
}
