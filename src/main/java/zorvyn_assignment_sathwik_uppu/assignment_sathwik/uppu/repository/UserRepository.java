package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}