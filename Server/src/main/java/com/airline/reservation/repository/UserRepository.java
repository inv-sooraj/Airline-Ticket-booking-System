package com.airline.reservation.repository;
import com.airline.reservation.entity.User;
import com.airline.reservation.view.UserView;
import java.util.Collection;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository  extends Repository<User, Integer> {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserIdAndPassword(Integer userId, String password);
    Collection<User> findAllByRole(Integer role);
    boolean existsByEmail(String email);
    Collection<User> findAll();
    Collection<User>findByFullNameContaining(String userName);

}
