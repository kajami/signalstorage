package hh.palvelinohjelmointi.signalstorage.signalstorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.User;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.UserRepository;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Device;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.DeviceRepository;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Signal;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.SignalRepository;


@SpringBootApplication
public class SignalstorageApplication {
	private static final Logger log = LoggerFactory.getLogger(SignalstorageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SignalstorageApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(SignalRepository srepository, DeviceRepository drepository, UserRepository urepository) {
		return (args) -> {
		
		LocalDateTime now = LocalDateTime.now();
		log.info("Save a couple of signals, devices and users");
		
		// Device
		Device hackrf = new Device("HackRF");
		drepository.save(hackrf);
		
		//Signals
		Signal s = new Signal("AM", 10.1, now.toString(), hackrf);
		Signal s2 = new Signal("FM", 100.1, now.toString(), hackrf);
		srepository.save(s);
		srepository.save(s2);
		
		// Create users
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);

		
		log.info("fetch all signals");
		for (Signal signal: srepository.findAll()) {
			log.info(signal.toString());
		}
		
		log.info("fetch all devices");
		for (Device device: drepository.findAll()) {
			log.info(device.toString());
		}
	
		};
	
	}
}
