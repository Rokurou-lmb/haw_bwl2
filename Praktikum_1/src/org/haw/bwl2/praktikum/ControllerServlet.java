package org.haw.bwl2.praktikum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haw.bwl2.praktikum.produkt.Produkt_I;
import org.haw.bwl2.praktikum.produkt.persistence.ProduktLoader_I;
import org.haw.bwl2.praktikum.produkt.persistence.db.DBConfiguration;
import org.haw.bwl2.praktikum.produkt.persistence.db.DBConfigurationSingleton;
import org.haw.bwl2.praktikum.produkt.persistence.db.oracle.OracleDBProduktLoader;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			DBConfiguration config = DBConfigurationSingleton.getInstance().getConfiguration();
			ProduktLoader_I loader = new OracleDBProduktLoader(config);
			List<Produkt_I> produkte = loader.loadAlleProdukte();
			
			for(Produkt_I produkt : produkte) {
				writer.println(produkt + "<br />");
			}
		} catch (SQLException e) {
			writer.println(e.getMessage());
		}
		writer.flush();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
