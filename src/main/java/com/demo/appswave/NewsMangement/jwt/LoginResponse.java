package com.demo.appswave.NewsMangement.jwt;

import java.util.List;

public class LoginResponse {
    private String jwtToken;
    private String email;
    private List<String> roles;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public LoginResponse(String email, List<String> roles, String jwtToken) {
        this.email=email;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
