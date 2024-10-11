package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity (name="usertable")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String role;
    private String address;
    private String nic;
    private String number;
    private String email;
    private String password;
}
