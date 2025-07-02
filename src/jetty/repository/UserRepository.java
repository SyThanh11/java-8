package jetty.repository;

import jetty.dto.UserDTO;

import java.util.Arrays;
import java.util.List;

public class UserRepository {
    public List<UserDTO> findAll() {
        return Arrays.asList(
                new UserDTO(1, "Alice"),
                new UserDTO(2, "Bob")
        );
    }
}
