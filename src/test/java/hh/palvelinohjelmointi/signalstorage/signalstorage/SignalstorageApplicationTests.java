package hh.palvelinohjelmointi.signalstorage.signalstorage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.palvelinohjelmointi.signalstorage.signalstorage.controller.*;

@SpringBootTest
class SignalstorageApplicationTests {
	
	@Autowired
	private SignalController bcontroller ;
	
	@Autowired
	private DeviceController dcontroller;
	
	@Autowired
	private UserDetailServiceImpl ucontroller;

	@Test
	void contextLoads() {
		assertThat(bcontroller).isNotNull();
		assertThat(dcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}

}
