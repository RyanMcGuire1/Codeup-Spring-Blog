package codeupspring.springblog.Models;

import com.mysql.cj.protocol.ColumnDefinition;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(){
    }

    public User(User copy){
        this.id = copy.id;
        this.email = copy.email;
        this.password = copy.password;
        this.username = copy.username;
    }

    public User(String password, String email, String username, long id){
        this.password = password;
        this.email = email;
        this.username = username;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
