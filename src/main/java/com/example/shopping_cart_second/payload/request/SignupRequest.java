package com.example.shopping_cart_second.payload.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
  @NotBlank(message = "the username is blank")
  @Size(min = 3, max = 20,message = "size the username must be between 3 and 20")
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email(message = "the Email is not valid")
  private String email;

  private Set<String> role;

  
  @NotBlank
  @Size(min = 6, max = 40,message = "the password should 6 to 40 characters")
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
