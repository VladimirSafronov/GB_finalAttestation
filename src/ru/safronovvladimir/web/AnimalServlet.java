package ru.safronovvladimir.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.safronovvladimir.Config;
import ru.safronovvladimir.service.AnimalManagementService;

public class AnimalServlet extends HttpServlet {

  private final AnimalManagementService manager = Config.get().getAnimalManagementService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
//    String id = req.getParameter("id");
    req.setAttribute("animals", manager.getAllAnimals());
    req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
