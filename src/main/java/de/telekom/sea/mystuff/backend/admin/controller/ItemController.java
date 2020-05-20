package de.telekom.sea.mystuff.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.ItemRepo;

@Controller
public class ItemController {

	private final ItemRepo itemRepo;

	@Autowired
	public ItemController(ItemRepo repository) {
		this.itemRepo = repository;
	}

	@GetMapping("/items")
	public String getAllItems(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "items";
	}

	@GetMapping("/item")
	public String getItem(Model model, @RequestParam(required = false) Long id) {

		Item item = new Item();
		model.addAttribute("item", item);

		return "item";
	}

	@PostMapping("/item")
	public String postItem(@ModelAttribute Item item) {
		itemRepo.save(item);
		return "redirect:/items";
	}

	@RequestMapping("item/delete")
	public String deleteItem(@RequestParam(required = false) Long id) {
		itemRepo.deleteById(id);
		return "redirect:/items";
	}

}
