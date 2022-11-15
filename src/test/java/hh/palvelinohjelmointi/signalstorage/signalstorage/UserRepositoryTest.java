package hh.palvelinohjelmointi.signalstorage.signalstorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.User;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository urepository;
	
	User user = new User("userOne", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
	
	@Test
	public void findByName () {
		User user = urepository.findByUsername("user");
	    assertThat(user.getUsername()).isEqualTo("user");
	}
	
	@Test
	public void createNewUser() {
		urepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
	@Test
	public void deleteUser() {
    	urepository.save(user);
    	urepository.deleteById(user.getId());
		User deletedUser = urepository.findByUsername("userOne");
        assertNull(deletedUser);
    }
	
	@Test
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
    	urepository.save(user);
        urepository.deleteAll();
        assertThat(urepository.count()).isEqualTo(0);
    }
}
