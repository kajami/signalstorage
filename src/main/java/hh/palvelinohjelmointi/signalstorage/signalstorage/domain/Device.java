package hh.palvelinohjelmointi.signalstorage.signalstorage.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Device {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long deviceId;

	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
	private List<Signal> signals;

	public Device() {}
	
	public Device(String name) {
		this.name = name;
	}
	
	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Signal> getSignals() {
		return signals;
	}

	public void setSignals(List<Signal> signals) {
		this.signals = signals;
	}
	
	@Override
	public String toString() {
		return "Device [id=" + deviceId + ", name=" + name + "]";
	}

}
