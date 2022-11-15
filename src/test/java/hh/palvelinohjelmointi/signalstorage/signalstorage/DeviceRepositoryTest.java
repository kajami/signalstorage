package hh.palvelinohjelmointi.signalstorage.signalstorage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Device;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.DeviceRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DeviceRepositoryTest {
	
	@Autowired
	DeviceRepository repository;
	
	Device d = new Device("testDevice");
	
	@Test
	public void findByName () {
		List <Device> devices = repository.findByName("HackRF");
	    assertThat(devices).hasSize(1);
	    assertThat(devices.get(0).getName()).isEqualTo("HackRF");
	}
	
	@Test
	public void createNewDevice() {
		repository.save(d);
    	assertThat(d.getDeviceId()).isNotNull();
    }
	
	@Test
	public void deleteDevice() {
    	repository.save(d);
    	repository.deleteById(d.getDeviceId());
		List <Device> devices = repository.findByName("testDevice");    	
	    assertThat(devices).hasSize(0);
    }
	
	@Test
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
    	repository.save(d);
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }

}
