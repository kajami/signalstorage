package hh.palvelinohjelmointi.signalstorage.signalstorage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Device;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.DeviceRepository;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Signal;

@Controller
public class DeviceController {
	
	@Autowired
	private DeviceRepository repository;
	
	// Show list of all devices
	@RequestMapping(value="/devices", method=RequestMethod.GET)
    public String devices(Model model) {
		model.addAttribute("devices", repository.findAll());
		return "devices";
	}

	// Save new Device
	@RequestMapping(value="/savedevice", method=RequestMethod.POST)
    public String saveDevice(Device device) {
		repository.save(device);
		return "redirect:add-signal";
	}
	
	// Add new Device
	@RequestMapping(value="/add-device", method=RequestMethod.GET)
    public String addDevice(Model model) {
		model.addAttribute("device", new Device());
		return "add-device";
	}
	
	// Add new Device from update
	@RequestMapping(value="/add-updated-device", method=RequestMethod.GET)
    public String addUpdatedDevice(Model model) {
		model.addAttribute("device", new Device());
		return "add-updated-device";
	}
	
	// Save updated Device
	@RequestMapping(value="/saveUpdatedDevice", method=RequestMethod.POST)
    public String saveUpdatedDevice(Device device) {
		repository.save(device);
		return "redirect:devices";
	}
	
	// Delete Device
	@RequestMapping(value="/delete-device/{id}", method=RequestMethod.GET)
    public String deleteDevice(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../devices";
	}
	
	//RESTful service to get all signals
    @RequestMapping(value="/device-api", method = RequestMethod.GET)
    public @ResponseBody List<Device> DeviceListRest() {	
        return (List<Device>) repository.findAll();
    }
    
	// RESTful service to get signal by id
    @RequestMapping(value="/device-api/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Device> findDeviceRest(@PathVariable("id") Long deviceId) {	
    	return repository.findById(deviceId);
    }
    
    // RESTful service to save new signal
    @RequestMapping(value="/device-api", method = RequestMethod.POST)
    public @ResponseBody Device saveDeviceRest(@RequestBody Device device) {	
    	return repository.save(device);
    }
	

}
