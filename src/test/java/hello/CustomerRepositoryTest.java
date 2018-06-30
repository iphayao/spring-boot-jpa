package hello;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void testFindByFirstName() {
        Customer customer = new Customer("first", "last");
        entityManager.persist(customer);

        List<Customer> findByFirstName = customerRepo.findByFirstName(customer.getFirstName());

        assertEquals(customer.getFirstName(), findByFirstName.get(0).getFirstName());

    }

}