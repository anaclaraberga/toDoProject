package br.edu.fag.toDoProject.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fag.toDoProject.models.User;
import br.edu.fag.toDoProject.models.dto.UserCreateDTO;
import br.edu.fag.toDoProject.models.dto.UserUpdateDTO;
import br.edu.fag.toDoProject.models.enums.ProfileEnum;
import br.edu.fag.toDoProject.repositories.TaskRepository;
import br.edu.fag.toDoProject.repositories.UserRepository;
import br.edu.fag.toDoProject.services.exceptions.DataBindingViolationException;
import br.edu.fag.toDoProject.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Id: " + id + ", Tipos: " + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas.");
        }
    }

    public User fromDTO(@Valid UserCreateDTO obj) {
        User user = new User();
        user.setUsername(obj.getUsername());
        user.setPassword(obj.getPassword());
        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setPassword(obj.getPassword());
        return user;
    }
}
