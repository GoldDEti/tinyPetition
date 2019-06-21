package etiennecorp;

import java.io.Console;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@WebServlet(name = "PetitionServlet", urlPatterns = { "/petitionapi" })
public class PetitionServlet extends HttpServlet {

	private Petition pet;
	private Entity petition;
	private DatastoreService datastore;

	public PetitionServlet() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	};

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (getPetition(request.getParameter("petitionId"))) {
			response.setStatus(200);
		} else {
			response.setStatus(400);
		}
	}

	public boolean getPetition(String idPetition) {
		Filter filt = new FilterPredicate("id", FilterOperator.EQUAL, idPetition);
		Query q = new Query("petition").setFilter(filt);
		UserService userService = UserServiceFactory.getUserService();
		petition = datastore.prepare(q).asSingleEntity();
		if (petition != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String idCreator = request.getParameter("idcreator");
		String name = request.getParameter("name");
		String description = request.getParameter("desc");
		pet = new Petition(name, description, idCreator);
		Object r = ofy().save().entity(pet);
	}
}