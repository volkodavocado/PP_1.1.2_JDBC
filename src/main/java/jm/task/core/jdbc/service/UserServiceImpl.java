package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    @Transactional
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Transactional
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Transactional
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}

