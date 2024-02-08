public class User {
    Integer id;
    String username;
    String email;
    String password;

    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return id + " " + username;
    }
}
