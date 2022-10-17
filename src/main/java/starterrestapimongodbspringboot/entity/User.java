package starterrestapimongodbspringboot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import starterrestapimongodbspringboot.constant.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Data
@Document(collection = "users")
public class User {

    @Id()
    private String userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private Role role;
    private String language;
    private String state;
    private String country;
    private String city;
    private String address;
    private LocalDateTime createdAt;
}
