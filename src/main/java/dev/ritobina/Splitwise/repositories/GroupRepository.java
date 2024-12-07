package dev.ritobina.Splitwise.repositories;

import dev.ritobina.Splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
}
