package com.mart.Agrimart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity(name="users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String phoneNo;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns =@JoinColumn(name="role_id")

    )
    private Set<Role> roles=new HashSet<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "verification_token")
    private String verificationToken;
    @Column(name = "verification_token_expiry")
    private LocalDateTime verificationTokenExpiry;


    @OneToMany(mappedBy ="owner",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Product> products=new ArrayList<>();
}
