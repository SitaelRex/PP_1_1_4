package jm.task.core.jdbc;

import jm.task.core.jdbc.service.*;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("fist","dude", (byte)55);
        userService.saveUser("second","dudess", (byte)34);
        userService.saveUser("third","dude", (byte)11);
        userService.saveUser("fourth","dude", (byte)47);
        userService.getAllUsers().forEach(System.out::println);
        userService.dropUsersTable();
        Util.closeHibernateFactory();
    }
}
