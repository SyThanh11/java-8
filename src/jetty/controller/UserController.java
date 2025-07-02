package jetty.controller;

import jetty.dto.UserDTO;
import jetty.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public String getAllUsers(){
        List<UserDTO> users = userService.getUsers();
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++){
            UserDTO user = users.get(i);
            json.append(String.format("{\"id\":%d,\"name\":\"%s\"}", user.getId(), user.getName()));
            if (i < users.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }
}
