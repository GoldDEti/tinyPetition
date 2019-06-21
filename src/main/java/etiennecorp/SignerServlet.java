package etiennecorp;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.googlecode.objectify.ObjectifyService.ofy;

@WebServlet(
    name = "SignerApi",
    urlPatterns = "/signerapi"
)
public class SignerServlet extends HttpServlet {
	private Signer signature;
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  String email = UserServiceFactory.getUserService().getCurrentUser().getEmail();
	  String sondage = req.getParameter("sondage");
	  Filter f = new FilterPredicate(email,FilterOperator.EQUAL,"Signer");
	  Filter f2 = new FilterPredicate(sondage,FilterOperator.EQUAL,"Signer");
	  signature = ofy().load().type(Signer.class).filter(f).filter(f2).first().now();
	  if(signature != null)
	  {
		  resp.setContentType("Sondage already signed");
	  }
	  else
	  {
		  resp.setContentType("Sondage not signed");
	  }
  }
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  String email = UserServiceFactory.getUserService().getCurrentUser().getEmail();
	  String sondage = req.getParameter("sondage");
	  Signer s = new Signer(email,sondage);
	  Object r = ofy().save().entity(s);
  }
}