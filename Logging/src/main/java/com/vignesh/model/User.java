package com.vignesh.model;



import lombok.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // One-to-one with Account
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
}
