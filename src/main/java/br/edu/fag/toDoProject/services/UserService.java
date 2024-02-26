package br.edu.fag.toDoProject.services;

import org.springframework.stereotype.Service;

import br.edu.fag.toDoProject.repositories.TaskRepository;
import br.edu.fag.toDoProject.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    private TaskRepository taskRepository;


}
