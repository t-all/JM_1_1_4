package jdbc;

import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        User user1 = new User("Fedot", "Fedotovich", (byte) 44);
        User user2 = new User("Ivan", "Ivanovich", (byte) 21);
        User user3 = new User("Semen", "Semenovich", (byte) 35);
        User user4 = new User("Petr", "Petrovich", (byte) 51);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(),user1.getAge());
        System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");
        userService.saveUser(user2.getName(), user2.getLastName(),user2.getAge());
        System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");
        userService.saveUser(user3.getName(), user3.getLastName(),user3.getAge());
        System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");
        userService.saveUser(user4.getName(), user4.getLastName(),user4.getAge());
        System.out.println("User с именем – " + user4.getName() + " добавлен в базу данных");

//        userService.dropUsersTable();

//        userService.cleanUsersTable();
//        userService.removeUserById(1);

        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

    }
}
