package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Honda", 1);
      Car car2 = new Car("Opel", 2);
      Car car3 = new Car("Reno", 3);
      Car car4 = new Car("Haval", 4);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         }
         System.out.println();

      }

      System.out.println("--------------------------------------------------");
      User userCar = userService.getUserByCar("Reno", 3);
      System.out.println("Id = " + userCar.getId());
      System.out.println("First Name = " + userCar.getFirstName());
      System.out.println("Last Name = " + userCar.getLastName());
      System.out.println("Email = " + userCar.getEmail());
      context.close();
   }
}
