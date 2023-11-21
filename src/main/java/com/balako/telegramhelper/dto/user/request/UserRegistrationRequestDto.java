package com.balako.telegramhelper.dto.user.request;

import com.balako.telegramhelper.validation.FieldsValueMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@FieldsValueMatch(field = "password", fieldMatch = "repeatedPassword")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 8, max = 50)
    private String password;
    @NotBlank
    @Length(min = 8, max = 50)
    private String repeatedPassword;
}
