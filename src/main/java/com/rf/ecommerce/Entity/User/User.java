package com.rf.ecommerce.Entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Table(name = "Kullanici")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2,max = 255)
    private String username;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçüj harf ve sayi kullanin")
    @NotNull
    @NotEmpty
    private String password;
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    @Column(unique = true)
    @NotEmpty
    @NotNull
    private String email;
    private String address;
}
