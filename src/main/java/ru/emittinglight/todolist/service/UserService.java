package ru.emittinglight.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emittinglight.todolist.persist.entity.User;
import ru.emittinglight.todolist.persist.repository.UserRepository;
import ru.emittinglight.todolist.represent.UserRepresent;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserRepresent userRepresent) {
        User user = new User();
        user.setUsername(userRepresent.getUsername());
        user.setPassword(userRepresent.getPassword());
        userRepository.save(user);
    }
}
