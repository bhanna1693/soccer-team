package com.tts.soccerProject.player;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PlayerController {
	
	@Autowired 
	private PlayerRepository playerRepository;
	
	//lists all the players
	@GetMapping("/")
	public ModelAndView index(Player player) {
		ModelAndView mv = new ModelAndView("player/index");
		mv.addObject("player", playerRepository.findAll());
		return mv;
	}
	
	//handles the saving of the new player
	@PostMapping("/player/new")
	public ModelAndView createPlayer(Player player) {
		ModelAndView mv = new ModelAndView("player/view-player");
		Player newPlayer = playerRepository.save(player);
		return mv;
	}
	
	//shows the form for creating a player
	@GetMapping("/player/new")
	public ModelAndView newPlayerForm(Player player) {
		ModelAndView mv = new ModelAndView("player/add-player");
		return mv;
	}
	
	//shows the form for editing a blog post
	@GetMapping("/player/edit/{id}")
	public ModelAndView updatePlayerForm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("player/edit");
		Optional<Player> editPlayer = playerRepository.findById(id);
		mv.addObject("player", editPlayer);
		return mv;
	}
	
	//saves the edits to the blog post
	@PutMapping("/player/edit")
	public ModelAndView updatePlayer(Player player) {
		ModelAndView mv = new ModelAndView("redirect:/");
		playerRepository.save(player);
		return mv;
	}
	
	//deletes blog post
	@DeleteMapping("/player/delete/{id}")
	public ModelAndView deletePlayer(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("redirect:/");
		playerRepository.deleteById(id);
		return mv;
	}
}
