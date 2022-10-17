package starterrestapimongodbspringboot.dto;

import lombok.Data;
import starterrestapimongodbspringboot.annotation.ValidateGender;
import starterrestapimongodbspringboot.constant.Role;

import javax.validation.constraints.*;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Data
public class UserRequest {

    private String userId;

    @NotBlank(message = "user name can't be empty or null")
    private String userName;

    @Email(message = "you have entered invalid email ID")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "you have entered invalid mobile number")
    private String phoneNumber;

    @NotNull(message = "date of birth can't be null")
    private String dateOfBirth;

    @NotNull(message = "gender can't be null")
    @ValidateGender
    private String gender;

    @NotNull(message = "role can't be null")
    private Role role;

    @NotNull(message = "language can't be null")
    private String language;

    @NotNull(message = "state can't be null")
    private String state;

    @NotNull(message = "country can't be null")
    private String country;

    @NotNull(message = "city can't be null")
    private String city;

    @NotBlank(message = "address can't be empty or null")
    private String address;
}
