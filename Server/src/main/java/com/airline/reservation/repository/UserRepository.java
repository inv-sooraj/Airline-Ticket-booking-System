package com.airline.reservation.repository;

import com.airline.reservation.entity.User;
import java.util.Collection;
import com.airline.reservation.exception.BadRequestException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user) throws BadRequestException;

    Optional<User> findByEmail(String email);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    Collection<User> findAllByRole(Integer role);

    boolean existsByEmail(String email);

    Collection<User> findByFullNameContaining(String userName);

    Optional<User> findByUserId(Integer currentUserId);

    Collection<User> findAllByStatus(Integer Status);

    @Modifying
    @Transactional
    @Query("update  User p set p.status = 0 where p.userId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);
}
