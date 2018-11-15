package model;

public class User {

    private String username;
    private String rol;
    private String encoded;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String rol) {
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }
}
