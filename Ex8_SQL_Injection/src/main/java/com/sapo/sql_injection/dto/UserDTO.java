package com.sapo.sql_injection.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank
    private Integer id;

    @NotBlank
    @Size(min = 6, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 6,max = 50)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String email;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
