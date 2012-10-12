package uk.ac.ebi.intact.view.webapp.cytoscape;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.tab.PsimiTabException;
import psidev.psi.mi.tab.converter.tab2graphml.Tab2Cytoscapeweb;
import uk.ac.ebi.intact.view.webapp.util.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Servlet enabling the conversion of a MITAB Stream into GraphML.
 *
 * @author Samuel Kerrien (skerrien@ebi.ac.uk)
 * @version $Id$
 * @since 1.2
 */
public class GraphmlServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(GraphmlServlet.class);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

	String mitabUrl = request.getParameter("mitabUrl");
		if( mitabUrl != null ) {
			mitabUrl = UrlUtils.encodeUrl(mitabUrl);
		}
		if (log.isDebugEnabled()) log.debug("mitabUrl: " + mitabUrl );

		final URL inputUrl = new URL( mitabUrl );
		final InputStream is = inputUrl.openStream();

		ServletOutputStream stream = response.getOutputStream();
		try{
			response.setContentType("text/plain");

			final Tab2Cytoscapeweb tab2Cytoscapeweb = new Tab2Cytoscapeweb();

			String output = null;
			try {
				output = tab2Cytoscapeweb.convert(is);
			} catch (PsimiTabException e) {
				throw new IllegalStateException( "Could not parse input MITAB.", e );
			}
			stream.write(output.getBytes());
			stream.flush();
		}
		finally {
			is.close();
		}
	}

}