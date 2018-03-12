

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Flugform_Servlet
 */
@WebServlet("/Flugform_Servlet")
public class Flugform_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Flugform_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter w = response.getWriter();
		w.append("<form action=\"Flugform_Servlet\" method=\"POST\">"
				+ "Flugnummer: <input type=\"text\" name=\"flugnummer\"> <br/> "
				+ "Start: <input type=\"text\" name=\"start\"> <br/> "
				+ "Ziel: <input type=\"text\" name=\"ziel\"> <br/> "
				+ "Flugzeit: <input type=\"text\" name=\"flugzeit\"> <br/> "
				+ "Strecke: <input type=\"text\" name=\"strecke\"> <br/> "
				+ "<button type=\"submit\" value=\"flugform\" name=\"Daten absenden\">Daten absenden</button></form>");
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
