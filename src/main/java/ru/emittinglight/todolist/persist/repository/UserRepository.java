package ru.emittinglight.todolist.persist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.emittinglight.todolist.persist.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
