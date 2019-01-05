package com.example.dataAgrigationService.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.example.dataAgrigationService.dataObjects.UserData;

public class Operations
{
	
	private static Object lockObject = new Object();
	private static Operations instance;
	
	private Operations() {}
	
	public static Operations getInstance()
	{
		if(instance == null)
		{
			synchronized (lockObject) {
				if(instance == null)
				{
					instance = new Operations();
				}
			}
		}
		
		return instance;
	}
	
	
	public void addAmountOfMonyToUser(Map<String, Integer> requestRowData)
	{
		Iterator<String> it = requestRowData.keySet().iterator();
		for(;it.hasNext();)
		{
			String userKey = it.next();
			Integer amountOfMounyToAdd = requestRowData.get(userKey);
			UserData userData = UserRepository.getInstance().getUser(userKey);
			
			if(userData == null)
			{
				userData = UserData.builder().ID("122").name(userKey).creditCard("123124134").ammountOfMoney(0).build();
			}
			
			userData.setAmmountOfMoney(userData.getAmmountOfMoney() + amountOfMounyToAdd);
			UserRepository.getInstance().setUser(userKey, userData);
		}
		
	}
	
	
	public ArrayList<UserData> getUsersInformation(Set<String> users)
	{
		ArrayList<UserData> result = new  ArrayList<UserData>();
		
		users.forEach(user -> result.add(UserRepository.getInstance().getUser(user)) );
		
		return result;
	}
	
	

}
