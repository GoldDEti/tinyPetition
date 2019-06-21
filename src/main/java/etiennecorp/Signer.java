package etiennecorp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Signer {
	@Id Long id;
	@Index
	String email;
	@Index
	String sondage;

	public Signer() {
	}

	public Signer(String email, String sondage) {
		this.email = email;
		this.sondage = sondage;
	}
}
