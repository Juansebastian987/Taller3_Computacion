package co.edu.icesi.fi.tics.tssc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic.ValidatedTopic;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

@Controller
public class TopicController {

	@Autowired
	private TopicServiceImp topicServiceImp;
	
	@GetMapping("/topic/")
	public String indexUser(Model model) {
		model.addAttribute("topics", topicServiceImp.findAll());
		return "topic/index";
	}

	@GetMapping("/topic/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topic/add-topic";
	}
	
	@PostMapping("/topic/add")
	public String saveTopic(@Validated(ValidatedTopic.class) TsscTopic tsscTopic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model m) {
			
		if (action != null && !action.equals("Cancelar")) {
			m.addAttribute("name", tsscTopic.getName());
			m.addAttribute("description", tsscTopic.getDescription());
			m.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
			m.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
			m.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
			
			if (bindingResult.hasErrors()) {
					return "topic/add-topic";
			}	
				try {
					topicServiceImp.saveTopic(tsscTopic);
					return "redirect:/topic/";
				} catch (Exception e) {
					e.printStackTrace();
				}		
		}
		return "redirect:/topic/";
	}	
	
	@GetMapping("/topic/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model m) {
		Optional<TsscTopic> tsscTopic = topicServiceImp.findById(id);

		if (tsscTopic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		
		m.addAttribute("tsscTopic", tsscTopic.get());
		m.addAttribute("name", tsscTopic.get().getName());
		m.addAttribute("description", tsscTopic.get().getDescription());
		m.addAttribute("defaultGroups", tsscTopic.get().getDefaultGroups());
		m.addAttribute("defaultSprints", tsscTopic.get().getDefaultSprints());
		m.addAttribute("groupPrefix", tsscTopic.get().getGroupPrefix());
		return "topic/update-topic";
	}
	
	@PostMapping("/topic/edit/{id}")
	public String updateTopic(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action,@Validated(ValidatedTopic.class) TsscTopic tsscTopic, BindingResult bindingResult, Model m) {

		if (action != null && !action.equals("Cancelar")) {
			if(bindingResult.hasErrors()) {
				m.addAttribute("name", tsscTopic.getName());
				m.addAttribute("description", tsscTopic.getDescription());
				m.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
				m.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
				m.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
				return "topic/update-topic";
			  }else {
					try {
						topicServiceImp.saveTopic(tsscTopic);
					} catch (Exception e) {
						e.printStackTrace();
					}
			  }
			}
		return "redirect:/topic/";
	}

	@GetMapping("/topic/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic tsscTopic = topicServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		topicServiceImp.delete(tsscTopic);
		return "redirect:/topic/";
	}
	
}