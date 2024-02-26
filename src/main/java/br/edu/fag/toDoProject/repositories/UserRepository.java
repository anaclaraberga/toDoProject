package br.edu.fag.toDoProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fag.toDoProject.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
