package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("Создана новая база данных!");

        userService.saveUser("Bib","Bibovich",(byte) 43);
        userService.saveUser("Vupsen","Pupsenevich",(byte) 20);
        userService.saveUser("Pupsen", "Vupsenevich", (byte) 20);
        userService.saveUser("lupa", "Buhgalterov", (byte) 98);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();
        System.out.println("База данных очищена!\n");

        userService.dropUsersTable();
        System.out.println("База данных была удалена!");
    }
}
