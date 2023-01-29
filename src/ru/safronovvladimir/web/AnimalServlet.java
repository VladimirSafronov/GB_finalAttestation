package ru.safronovvladimir.web;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.safronovvladimir.Config;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.TypeAnimal;
import ru.safronovvladimir.service.AnimalManagementService;
import ru.safronovvladimir.util.StringUtil;

public class AnimalServlet extends HttpServlet {

  private final AnimalManagementService manager = Config.get().getAnimalManagementService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String id = req.getParameter("id");
    String action = req.getParameter("action");
    if (action == null) {
      req.setAttribute("animals", manager.getAllAnimals());
      req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
      return;
    }
    Animal animal;
    switch (action) {
      case "add":
        animal = new Animal();
        break;
      case "view":
      case "edit":
        animal = manager.get(Integer.parseInt(id));
        break;
      case "delete":
        manager.delete(Integer.parseInt(id));
        resp.sendRedirect("animal");
        return;
      default:
        throw new IllegalArgumentException("Action " + action + " is illegal");
    }
    req.setAttribute("animal", animal);
    switch (action) {
      case "add":
        req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
        break;
      case "view":
        req.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(req, resp);
        break;
      case "edit":
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String id = req.getParameter("id");
    String name = req.getParameter("name");
    Animal animal;
    final boolean isNotCreated = (id == null);
    if (isNotCreated) {
      animal = new Animal();
      animal.setName(name);
      animal.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
      animal.setType(TypeAnimal.valueOf(req.getParameter("animal_type")));
      animal.setCommands(StringUtil.toList(req.getParameter("command")));
    } else {
      int idAnimal = Integer.parseInt(id);
      animal = manager.get(idAnimal);
      String command = req.getParameter("command");
      if (command != null) {
        manager.educateCommands(idAnimal, command);
      }
    }
    if (isNotCreated) {
      manager.addAnimal(animal);
    }
    resp.sendRedirect("animal");
  }
}
