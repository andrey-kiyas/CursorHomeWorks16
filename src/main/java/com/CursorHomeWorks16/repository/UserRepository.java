package com.CursorHomeWorks16.repository;

import com.CursorHomeWorks16.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
