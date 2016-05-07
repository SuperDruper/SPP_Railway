package code.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class User {

    @Min(value = 0, message = "Id field can't be negative!")
    private int id;

    @Size(min = 1, max = 50, message = "Name must be between {min} and {max} characters long!")
    @Pattern(regexp = "[a-zA-Z\\d\\-]*",
            message = "Name may contain only latin characters, digits or hyphen!")
    private String name;

    @Size(min = 1, max = 50, message = "Surname must be between {min} and {max} characters long!")
    @Pattern(regexp = "[a-zA-Z\\d\\-]*",
            message = "Surname may contain only latin characters, digits or hyphen!")
    private String surname;

    @Size(min = 1, max = 255, message = "Email must be between {min} and {max} characters long!")
    @NotNull(message = "Email must be set!")
    @Email(message = "Email format is incorrect!")
    private String email;

    @Size(min = 1, max = 50, message = "Login must be between {min} and {max} characters long!")
    @Pattern(regexp = "[a-zA-Z\\d_\\-\\.]+",
            message = "Login may contain only latin characters, digits, points, hyphen or underscore!")
    @NotNull(message = "Login must be set!")
    private String login;

    @Size(min = 8, max = 50, message = "Password must be between {min} and {max} characters long!")
    @Pattern(regexp = "[a-zA-Z\\d_\\-\\.]+",
            message = "Password may contain only latin characters, digits, points, hyphen or underscore!")
    @NotNull(message = "Password must be set!")
    private String password;

    private Collection<Ticket> tickets;

    @NotNull(message = "Role can't be null!")
    @Valid
    private Role role;



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "u_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "u_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "u_surname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "u_email", nullable = false, insertable = true, updatable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "u_login", nullable = false, insertable = true, updatable = true, length = 50)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "u_password", nullable = false, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToOne
    @JoinColumn(name = "rl_id", referencedColumnName = "rl_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
