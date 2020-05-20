package de.telekom.sea.mystuff.backend.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.ItemRepo;

@RestController
@RequestMapping("/api/v1")
public class ItemRestController {

	private final ItemRepo repository;

	@Autowired
	public ItemRestController(ItemRepo itemRepo) {
		this.repository = itemRepo;
	}

	@GetMapping("items")
	public List<Item> getAll() {
		return repository.findAll();
	}

	@GetMapping("items/{id}")
	public Item get(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PostMapping("items")
	public Item create(@RequestBody Item newItem) {
		return repository.save(newItem);
	}

	@PutMapping("items/{id}")
	public Item replace(@RequestBody Item newItem, @PathVariable Long id) {
		return repository.findById(id).map(item -> {
			item.setName(newItem.getName());
			item.setAmount(newItem.getAmount());
			item.setDescription(newItem.getDescription());
			item.setLastUsed(newItem.getLastUsed());
			item.setLocation(newItem.getLocation());
			return repository.save(item);
		}).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@DeleteMapping("items/{id}")
	public void delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException(id);
		}
	}

}
