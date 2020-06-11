package com.example.Project.Controller.Object;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class RegisObj {

    @NotBlank(message = "This field is required")
    private String firstName;

    @NotBlank(message = "This field is required")
    private String lastName;

    @NotBlank(message = "This field is required")
    private String password;

    @NotBlank(message = "This field is required")
    private String email;

    @NotBlank(message = "This field is required")
    private String usertype;

    @AssertTrue
    private Boolean terms;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
