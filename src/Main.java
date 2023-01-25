import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import ru.safronovvladimir.Config;
import ru.safronovvladimir.model.Animal;
import ru.safronovvladimir.model.TypeAnimal;
import ru.safronovvladimir.service.AnimalManagementService;
import ru.safronovvladimir.service.AnimalManagementServiceImpl;
import ru.safronovvladimir.util.StringUtil;

public class Main {

  public static void main(String[] args) {
    LocalDate exDate = LocalDate.of(2000, 1, 1);
    AnimalManagementService manager = Config.get().getAnimalManagementService();

//    List<String> commands = manager.getAllCommands("Greta");
//    System.out.println(commands);

    manager.educateCommands("Leopold", "command1", "command2");
  }
}