

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter w = response.getWriter();
		w.write("<html>");
		w.write("<body>");
		w.write("<h1>Währungsrechner</h1>");
		
		// Betrag lesen falls gefüllt, sonst 0.00
		Double betrag = 0.0;
		try {
			betrag = Double.valueOf(request.getParameter("betrag"));
		} catch (NullPointerException e) {
			// Wenn Parameter nicht gefüllt!
		}
		
		// Form 
		w.write("<form action=\"\" method=\"GET\">"
					+ "Betrag: "
					+ "<input type=\"number\" name=\"betrag\" step=\"0.01\" value=\""+betrag+"\"> </br></br>"
					+ "Währung: "
					+ "<select name=\"waehrung\">" + 
						"  <option value=\"Pfund\">Brit. Pfund</option>" + 
						"  <option value=\"Yen\">Japanische Yen</option>" +
						"  <option value=\"Yuan\">Chinesiche Yuan</option>"
					+ "</select> </br>"
					+ "Richtung: "
					+ "<select name=\"richtung\">" + 
						"  <option value=\"EUR2Fremd\">EUR -> Fremd</option>" + 
						"  <option value=\"Fremd2EUR\">Fremd -> EUR</option>"
					+ "</select> </br><br/>"
					+ "<button type=\"submit\">Umrechnen</button>"
				+ "</form> </br></br>");
		
		w.write("<h3>Ergebnis:</h3>");
		
		// Berechnung
		try {
			// Kurs
			Double kurs = 0.00;
			switch (request.getParameter("waehrung")) {
			case "Pfund":
				kurs = 0.885;
				break;
			case "Yen":
				kurs = 131.146;
				break;
			case "Yuan":
				kurs = 7.787;
				break;
			}
			
			// Richtung
			switch (request.getParameter("richtung")) {
			case "EUR2Fremd":
				betrag = betrag * kurs;
				break;
			case "Fremd2EUR":
				betrag = betrag * (1/kurs);
				break;
			}
			
			// Ausgabe
			w.write(betrag.toString());
		} catch (NullPointerException e) {
			// Wenn man ohne Parameter aufruft!
		}
		
		w.write("</body>");
		w.write("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
