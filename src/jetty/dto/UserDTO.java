package jetty.dto;

public class UserDTO {
    private int id;
    private String name;

    public UserDTO(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
}
