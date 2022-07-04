package hrms.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrms.core.entities.User;
import hrms.hrms.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    public Authority(Integer id, Role roleName) {
        this.id = id;
        this.name = roleName.toString();
    }

    public Authority(Integer aid) {
        this.id = aid;
    }

    public void setName(Role roleName) {
        this.name = roleName.toString();
    }
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public void addUser(User user) {
        this.user = user;
    }


}
