package com.eductecno.servlets;
import com.eductecno.dao.HoroscopoDAOImp;
import com.eductecno.modelo.Horoscopo;
import com.eductecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ConsultarHoroscopoServlet")
public class ConsultarHoroscopoServlet extends HttpServlet {

    private HoroscopoDAOImp horoscopoDAO = new HoroscopoDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Usuario usuario = (Usuario) session.getAttribute("usuario"); // Asegúrate de que 'usuario' esté en la sesión
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Date fechaNacimiento = usuario.getFechaNacimiento();

        List<Horoscopo> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();

        String signo = null;
        for (Horoscopo h : listaHoroscopo) {
            if ((fechaNacimiento.after(h.getFechaInicio()) || fechaNacimiento.equals(h.getFechaInicio())) &&
                    (fechaNacimiento.before(h.getFechaFin()) || fechaNacimiento.equals(h.getFechaFin()))) {
                signo = h.getAnimal();
                break;
            }
        }

        session.setAttribute("animal", signo);

        response.sendRedirect("conoceAnimal.jsp");
    }
}
