package com.example.dataAgrigationService.dataObjects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {

	private String name;
	private String ID;
	private Integer ammountOfMoney;
	private String creditCard;
	
}
