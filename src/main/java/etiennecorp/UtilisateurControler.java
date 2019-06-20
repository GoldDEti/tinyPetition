package etiennecorp;

import java.io.Console;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

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



@WebServlet(
	    name = "UtilisateurControler",
	    urlPatterns = {"/hello"}
	)
public class UtilisateurControler extends HttpServlet{

	private Entity user;
	private DatastoreService datastore;
	public UtilisateurControler() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	};
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		if(getUserFromRequest())
		{
			response.setStatus(200);
			System.out.println("lol");
		}
		else
		{
			response.setStatus(400);
			System.out.println("mdr");
		}
	}
	public boolean getUserFromRequest()
	{
		Filter filt = new FilterPredicate("Name",FilterOperator.EQUAL,"Soleau");
		Query q = new Query("utilisateur").setFilter(filt);
		UserService userService = UserServiceFactory.getUserService();
		System.out.println(userService.getCurrentUser().getUserId());
		user = datastore.prepare(q).asSingleEntity();
		if(user != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
