package jetty.service;

import jetty.dto.UserDTO;
import jetty.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<UserDTO> getUsers(){
        return this.userRepository.findAll();
    }
}
