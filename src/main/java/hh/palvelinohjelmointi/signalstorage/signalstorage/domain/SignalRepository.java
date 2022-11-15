package hh.palvelinohjelmointi.signalstorage.signalstorage.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SignalRepository extends CrudRepository<Signal, Long>{
	
	List<Signal> findByFrequency(double frequency);

}
