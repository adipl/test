package com.example.dataAgrigationService.Logic;

import java.util.HashMap;
import java.util.Map;

import com.example.dataAgrigationService.dataObjects.UserData;

public class UserRepository 
{
	
	private Map<String,UserData> UserRepositoryMap = new HashMap();
	private static Object lockObject = new Object();
	private static UserRepository instance;
	
	private UserRepository() {}
	
	public static UserRepository getInstance()
	{
		if(instance == null)
		{
			synchronized (lockObject) {
				if(instance == null)
				{
					instance = new UserRepository();
				}
			}
		}
		
		return instance;
	}
	
	
	public UserData getUser(String user)
	{
		return UserRepositoryMap.get(user);
	}
	
	public UserData setUser(String user,UserData userData)
	{
		return UserRepositoryMap.put(user,userData);
	}

}
