package co.com.psl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Entity
public class Customer extends ResourceSupport{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;
    private String firstName;
    private String lastName;

    @OneToMany
    private List<Task> tasks;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getcustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

