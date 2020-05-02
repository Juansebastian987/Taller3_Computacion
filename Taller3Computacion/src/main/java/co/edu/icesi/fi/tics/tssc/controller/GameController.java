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
import co.edu.icesi.fi.tics.tssc.model.TsscGame.ValidatedGame;
import co.edu.icesi.fi.tics.tssc.service.GameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

@Controller
public class GameController {

	@Autowired
	private GameServiceImp gameServiceImp;
	
	@Autowired
	private TopicServiceImp topicServiceImp;
	
	@GetMapping("/game/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameServiceImp.findAll());
		return "game/index";
	}

	@GetMapping("/game/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicServiceImp.findAll());
		return "game/add-game";
	}

	@PostMapping("/game/add")
	public String saveGame(@Validated(ValidatedGame.class) TsscGame tsscGame, BindingResult bindingResult,@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("name", tsscGame.getName());
			model.addAttribute("adminPassword", tsscGame.getAdminPassword());
			model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
			model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
			model.addAttribute("nGroups", tsscGame.getNGroups());
			model.addAttribute("nSprints", tsscGame.getNSprints());
			model.addAttribute("userPassword", tsscGame.getUserPassword());
			model.addAttribute("guestPassword", tsscGame.getGuestPassword());
			model.addAttribute("topics", topicServiceImp.findAll());
			
			if (bindingResult.hasErrors()) {
					return "game/add-game";
			}	
				try {		
					if (tsscGame.getTsscTopic() == null) {
						gameServiceImp.saveGame(tsscGame);
						return "redirect:/game/";
					}
					else {
						gameServiceImp.saveGameWithTopic(tsscGame, tsscGame.getTsscTopic().getId());
						return "redirect:/game/";
					}						
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return "redirect:/game/";
	}	
	
	@GetMapping("/game/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> tsscGame = gameServiceImp.findById(id);

		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);

		model.addAttribute("tsscGame", tsscGame.get());
		model.addAttribute("name", tsscGame.get().getName());
		model.addAttribute("adminPassword", tsscGame.get().getAdminPassword());
		model.addAttribute("scheduledDate", tsscGame.get().getScheduledDate());
		model.addAttribute("scheduledTime", tsscGame.get().getScheduledTime());
		model.addAttribute("nGroups", tsscGame.get().getNGroups());
		model.addAttribute("nSprints", tsscGame.get().getNSprints());
		model.addAttribute("userPassword", tsscGame.get().getUserPassword());
		model.addAttribute("guestPassword", tsscGame.get().getGuestPassword());
		model.addAttribute("topics", topicServiceImp.findAll());

		return "game/update-game";
	}

	@PostMapping("/game/edit/{id}")
	public String updateGame(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action,@Validated(ValidatedGame.class) TsscGame tsscGame, BindingResult bindingResult, Model model) {

		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {

				model.addAttribute("name", tsscGame.getName());
				model.addAttribute("adminPassword", tsscGame.getAdminPassword());
				model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
				model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
				model.addAttribute("nGroups", tsscGame.getNGroups());
				model.addAttribute("nSprints", tsscGame.getNSprints());
				model.addAttribute("userPassword", tsscGame.getUserPassword());
				model.addAttribute("guestPassword", tsscGame.getGuestPassword());
				model.addAttribute("topics", topicServiceImp.findAll());
				return "game/update-game";
			}
			else {			
				try {		
					if (tsscGame.getTsscTopic() == null) {
						gameServiceImp.saveGame(tsscGame);
					}
					else {
						gameServiceImp.saveGameWithTopic(tsscGame, tsscGame.getTsscTopic().getId());
					}						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "redirect:/game/";
			
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame tsscGame = gameServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		gameServiceImp.delete(tsscGame);
		return "redirect:/game/";
	}
	
	

	@GetMapping("/game/{id}/list-story/")
	public String showListStories(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> tsscGame = gameServiceImp.findById(id);

		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);

		model.addAttribute("tsscGame", tsscGame.get());
		model.addAttribute("stories", tsscGame.get().getTsscStories());

		return "game/list-story";
	}
	
}