package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Nikita", "Zhikharev", "zhikh@mail.ru", new Car("Toyota", "Supra")));
        userService.add(new User("Emily", "Smith", "smith@mail.ru", new Car("Toyota", "Corola")));
        userService.add(new User("Daiv", "Black", "black@mail.ru", new Car("Reno", "Logan")));
        userService.add(new User("Elena", "James", "james@mail.ru", new Car("Ford", "Focus")));

        userService.listUsers().forEach(user -> System.out.println(user));

        System.out.println(userService.getCarOwner("Reno", "Logan"));
        context.close();
    }
}
