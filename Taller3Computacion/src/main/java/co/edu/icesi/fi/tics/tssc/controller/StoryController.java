package co.edu.icesi.fi.tics.tssc.controller;

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

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscStory.ValidatedStory;
import co.edu.icesi.fi.tics.tssc.service.GameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.StoryServiceImp;

@Controller
public class StoryController {

	@Autowired
	private StoryServiceImp storyServiceImp;
	
	@Autowired
	private GameServiceImp gameServiceImp;
	
	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyServiceImp.findAll());
		return "story/index";
	}

	@GetMapping("/story/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameServiceImp.findAll());
		return "story/add-story";
	}
	
	@PostMapping("/story/add")
	public String saveStory(@Validated(ValidatedStory.class) TsscStory tsscStory, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {

		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()){
				model.addAttribute("description", tsscStory.getDescription());
				model.addAttribute("businessValue", tsscStory.getBusinessValue());
				model.addAttribute("initialSprint", tsscStory.getInitialSprint());
				model.addAttribute("priority", tsscStory.getPriority());
				model.addAttribute("games", gameServiceImp.findAll());
				return "story/add-story";
			}
			else {
				try {
					gameServiceImp.findById(tsscStory.getTsscGame().getId()).get().getTsscStories().add(tsscStory);
					storyServiceImp.saveStory(tsscStory, tsscStory.getTsscGame().getId());
					return "redirect:/story/";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/story/";	
	}
	
	@GetMapping("/story/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {	
		Optional<TsscStory> tsscStory = storyServiceImp.findById(id);
		if (tsscStory == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);

		model.addAttribute("tsscStory", tsscStory.get());
		model.addAttribute("description", tsscStory.get().getDescription());
		model.addAttribute("businessValue", tsscStory.get().getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.get().getInitialSprint());
		model.addAttribute("priority", tsscStory.get().getPriority());
		model.addAttribute("games", gameServiceImp.findAll());
		return "story/update-story";
	}
	
	@PostMapping("story/edit/{id}")
	public String updateStory(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action, @Validated(ValidatedStory.class) TsscStory tsscStory, BindingResult bindingResult, Model model) {

		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameServiceImp.findAll());
				model.addAttribute("description", tsscStory.getDescription());
				model.addAttribute("businessValue", tsscStory.getBusinessValue());
				model.addAttribute("initialSprint", tsscStory.getInitialSprint());
				model.addAttribute("priority", tsscStory.getPriority());
				
				return "story/update-story";
			}else {
				try {
					storyServiceImp.saveStory(tsscStory, tsscStory.getTsscGame().getId());
					return "redirect:/story/";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/story/";
	}
	
	@GetMapping("/story/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscStory tsscStory = storyServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid story Id:" + id));
		storyServiceImp.delete(tsscStory);
		return "redirect:/story/";
	}

	@GetMapping("/game/list/{id}")
	public String showListStories(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> tsscGame = gameServiceImp.findById(id);

		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);

		model.addAttribute("tsscGame", tsscGame.get());
		model.addAttribute("stories", tsscGame.get().getTsscStories());

		return "game/list-story";
	}
	
}