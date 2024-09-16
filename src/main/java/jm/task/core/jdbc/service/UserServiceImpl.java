package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Пользователь " + name + " " + lastName + " добавлен в базу данных\n");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
        System.out.println("Пользователь с идентфикатором № " + id + " был удален.\n");
    }

    public List<User> getAllUsers() {
        System.out.println("Общее количество пользователей базы данных:");
        List<User> users =  userDaoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
