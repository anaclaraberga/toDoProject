package br.edu.fag.toDoProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fag.toDoProject.models.User;
import br.edu.fag.toDoProject.repositories.TaskRepository;
import br.edu.fag.toDoProject.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow();
    }
}
