package etiennecorp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@WebServlet(name = "UtilisateurControler", urlPatterns = { "/utilisateurapi" })
public class UtilisateurControler extends HttpServlet {

	private Utilisateur user;
	private UserService userService;

	public UtilisateurControler() {
		userService = UserServiceFactory.getUserService();
	};

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filter filt = new FilterPredicate("Name", FilterOperator.EQUAL, request.getParameter("name"));
		Filter filt2 = new FilterPredicate("FirstName", FilterOperator.EQUAL, request.getParameter("firstname"));
		Filter filt3 = new FilterPredicate("email", FilterOperator.EQUAL, userService.getCurrentUser().getEmail());
		Object user = ofy().load().type(Signer.class).filter(filt).filter(filt2).filter(filt3).first().now();
		if (user != null) {
			response.setStatus(200);
		} else {
			response.setStatus(400);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String firstName = req.getParameter("firstName");
		String pseudo = req.getParameter("pseudo");
		String email = userService.getCurrentUser().getEmail();
		user = new Utilisateur(name, firstName, pseudo, email);
		Object r = ofy().save().entity(user);
	}
}
