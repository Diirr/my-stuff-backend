package de.telekom.sea.mystuff.backend.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {

//	public Item(String string, int i, String string2, String string3, String string4) {
//		// TODO Auto-generated constructor stub
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int amount;

	private String location;

	private String description;

	private Date lastUsed;

}
