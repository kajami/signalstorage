package hh.palvelinohjelmointi.signalstorage.signalstorage;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Device;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Signal;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.SignalRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SignalRepositoryTest {
	
	@Autowired
	SignalRepository repository;
	
	LocalDateTime now = LocalDateTime.now();
	Device hackrf = new Device("HackRF");
	Signal s = new Signal("AM", 102.11, now.toString(), hackrf);
	
	@Test
	public void findByFrequency () {
		List <Signal> signals = repository.findByFrequency(10.1);
	    assertThat(signals).hasSize(1);
	    assertThat(signals.get(0).getFrequency()).isEqualTo(10.1);
	}
	
	@Test
	public void createSignal() {
    	repository.save(s);
	    assertThat(s.getId()).isNotNull();
    }
	
	@Test
	public void deleteSignal() {
    	repository.save(s);
    	repository.deleteById(s.getId());
		List <Signal> signals = repository.findByFrequency(102.11);
	    assertThat(signals).hasSize(0);
    }
	
	@Test
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }
}
