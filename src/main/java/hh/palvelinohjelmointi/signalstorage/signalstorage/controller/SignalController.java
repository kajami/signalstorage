package hh.palvelinohjelmointi.signalstorage.signalstorage.controller;

import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.DeviceRepository;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.Signal;
import hh.palvelinohjelmointi.signalstorage.signalstorage.domain.SignalRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class SignalController {
	
	@Autowired
	private SignalRepository repository; 
	
	@Autowired
	private DeviceRepository drepository; 
	
	// Get list of signals
	@RequestMapping(value="/signals", method=RequestMethod.GET)
    public String signals(Model model) {
		model.addAttribute("signals", repository.findAll());
		return "signals";
	}
	
	// Add new signal
	@RequestMapping(value="/add-signal", method=RequestMethod.GET)
    public String addSignal(Model model) {
		model.addAttribute("signal", new Signal());
		model.addAttribute("devices", drepository.findAll());
		return "add-signal";
	}
	
	// Save new signal
	@RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveSignal(Signal signal) {
		repository.save(signal);
		return "redirect:signals";
	}
	
	// Delete signal
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteSignal(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../signals";
	}
    
    // Update signal
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public String updateSignal(@PathVariable("id") long signalId, Model model) {
		model.addAttribute("devices", drepository.findAll());
		model.addAttribute("signal", repository.findById(signalId));
		return "update-signal";
	}
    
    // Save update
    @RequestMapping(value="/saveupdate", method=RequestMethod.POST)
    public String saveUpdate(Signal signal) {
       repository.save(signal);
       return "redirect:signals";
    }
	
	//RESTful service to get all signals
    @RequestMapping(value="/signal-api", method = RequestMethod.GET)
    public @ResponseBody List<Signal> SignalListRest() {	
        return (List<Signal>) repository.findAll();
    }
    
	// RESTful service to get signal by id
    @RequestMapping(value="/signal-api/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Signal> findSignalRest(@PathVariable("id") Long signalId) {	
    	return repository.findById(signalId);
    }
    
    // RESTful service to save new signal
    @RequestMapping(value="/signal-api", method = RequestMethod.POST)
    public @ResponseBody Signal saveSignalRest(@RequestBody Signal signal) {	
    	return repository.save(signal);
    }
  

}
