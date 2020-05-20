package de.telekom.sea.mystuff.backend.dataloader;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.ItemRepo;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ItemRepo repository;

	@Autowired
	public void setItemRepo(ItemRepo itemRepo) {
		this.repository = itemRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Item item1 = new Item();
		item1.setName("Computer");
		item1.setAmount(3);
		item1.setLocation("Wohnzimmer");
		item1.setDescription("Alte Laptops, die nicht mehr genutzt werden");
		item1.setLastUsed(Date.valueOf("2020-02-17"));
		repository.save(item1);

		Item item2 = new Item();
		item2.setName("Schuhe");
		item2.setAmount(17);
		item2.setLocation("Keller");
		item2.setDescription("verranzte Schuhe, die eh kein Schwein mehr anzieht.");
		item2.setLastUsed(Date.valueOf("2020-02-18"));
		repository.save(item2);

		Item item3 = new Item();
		item3.setName("Nägel");
		item3.setAmount(450);
		item3.setLocation("Dachboden");
		item3.setDescription("Nägel kann man immer mal gebrauchen.");
		item3.setLastUsed(Date.valueOf("2020-02-19"));
		repository.save(item3);

	}

}
