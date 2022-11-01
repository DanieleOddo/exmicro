package acme.oddo.data.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import acme.oddo.data.entities.CustomerEntity;

@DataJpaTest
public class CustomerRepositoryTest {
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void itShouldSaveUser() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("TEST");
        customerEntity.setSurname("SURNAME");

        customerEntity = entityManager.persistAndFlush(customerEntity);
        assertThat(customerRepository.findByCustomerID(customerEntity.getCustomerID()).getName()).isEqualTo(customerEntity.getName());
    }

}
