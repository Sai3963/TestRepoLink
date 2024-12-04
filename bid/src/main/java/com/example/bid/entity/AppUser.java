package com.example.bid.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Size;
import lombok.*;
//import org.thrymr.enums.Gender;
//import org.thrymr.enums.Roles;
//import org.thrymr.token.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Getter
@Setter
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    // @Email
    private String email;

    private String password;

    //private Gender gender;

    @Column(name = "mobile_number")
   // @Size(min = 10)
   // @Size(max = 10, message = "please enter valid mobile number max = 10 numbers")
    private String mobileNumber;



   // @Enumerated(EnumType.STRING)
  // private Roles roles;

    private Boolean isVerified = Boolean.FALSE;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        String passwordPatternRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()])(?=.*[0-9])[A-Z][a-zA-Z0-9!@#$%^&*]{7,11}$";
        Pattern passwordPattern = Pattern.compile(passwordPatternRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (passwordMatcher.matches()) {
            System.out.println("Entered password");
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        } else {
            throw new IllegalArgumentException("Begin with a capital letter. Contain at least one special character. Contain at least one numerical digit. Have a length between 8 and 12 characters. e.x : Rama@123");
        }
    }
}


class Test{
    public static void main(String[] args) {
        System.out.println("The number is: "+ 4);
    }
}