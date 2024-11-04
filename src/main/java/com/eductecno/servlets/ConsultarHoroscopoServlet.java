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

        // Obtener el usuario y su fecha de nacimiento
        Usuario usuario = (Usuario) session.getAttribute("usuario"); // Asegúrate de que 'usuario' esté en la sesión
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener la fecha de nacimiento del usuario
        Date fechaNacimiento = usuario.getFechaNacimiento();

        // Obtener la lista de signos desde el DAO
        List<Horoscopo> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();

        // Buscar el signo correspondiente a la fecha de nacimiento
        String signo = null;
        for (Horoscopo h : listaHoroscopo) {
            if ((fechaNacimiento.after(h.getFechaInicio()) || fechaNacimiento.equals(h.getFechaInicio())) &&
                    (fechaNacimiento.before(h.getFechaFin()) || fechaNacimiento.equals(h.getFechaFin()))) {
                signo = h.getAnimal();
                break;
            }
        }

        // Guardar el signo en la sesión para mostrarlo en la JSP
        session.setAttribute("animal", signo);

        // Redirigir a la página JSP para mostrar el resultado
        response.sendRedirect("conoceAnimal.jsp");
    }
}
