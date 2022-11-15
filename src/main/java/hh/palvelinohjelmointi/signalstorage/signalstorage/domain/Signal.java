package hh.palvelinohjelmointi.signalstorage.signalstorage.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;

@Entity
public class Signal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String type;
	private double frequency;
	private String datetime;
	
	@ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "deviceId")
	private Device device;

	public Signal() {}
	
	public Signal(String type, double frequency, String datetime, Device device) {
		super();
		this.type = type;
		this.frequency = frequency;
		this.datetime = datetime;
		this.device = device;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public String toString(){
	      return "Type: "+this.type +", Frequency: "+ this.frequency+ ", Date and time: " + this.datetime+ ", Device: " + this.device.getName() + ", Signal ID: " + this.id;
	}

}
