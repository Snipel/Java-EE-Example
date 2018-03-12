
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Flug;

/**
 * Servlet implementation class Flugliste_Servlet
 */
@WebServlet("/Flugliste_Servlet")
public class Flugliste_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Flugliste_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter w = response.getWriter();
		w.append("<table><tr><th>Flugnummer</th><th>Flugzeit</th></tr>");
		for(Flug f: holeFluegeAusDB()) {
			w.append("<tr><td>"+ f.getFlugnr() + "</td><td>"+ f.getFlugzeit()+ "</td></tr>");
		}
		w.append("</table>");
	}

	@SuppressWarnings("unchecked")
	private List<Flug> holeFluegeAusDB() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Flugbuchung_Web_Selbststudium");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flug.findAll");
		em.getTransaction().commit();
		return query.getResultList();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
