package com.example.dataAgrigationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataAgrigationService.Logic.Operations;
import com.example.dataAgrigationService.callToService.CallService;
import com.example.dataAgrigationService.dataObjects.UserData;

@SpringBootApplication
@RestController
public class DataStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataStoreServiceApplication.class, args);
	}
	
	@PostMapping("/store")
	public String updatUsers(@RequestBody DataAgrigationServiceResult dataAgrigationServiceResult)
	{
		Operations.getInstance().addAmountOfMonyToUser(dataAgrigationServiceResult.getMRequestRowData());
		Set<String> setOfChangeUser = dataAgrigationServiceResult.getMRequestRowData().keySet();
		
		ArrayList<UserData> resultArrayList = Operations.getInstance().getUsersInformation(setOfChangeUser);
		
		ArrayList NameAndAmountList = new ArrayList();
		
		resultArrayList.forEach(userData -> NameAndAmountList.add( userData.getName() + " " + userData.getAmmountOfMoney() ) );
		
		CallService callService = new CallService();
		return callService.sendPostRequest("http://127.0.0.1:8080/showData",NameAndAmountList);
		
	}

	@PostMapping("/serch")
	public String updatUsers(@RequestBody Set<String> usersList)
	{
			
		ArrayList<UserData> resultArrayList = Operations.getInstance().getUsersInformation(usersList);
		
		ArrayList<String> NameAndAmountList = new ArrayList();
		resultArrayList.forEach(userData -> NameAndAmountList.add( userData.getName() + " " + userData.getAmmountOfMoney() ) );
		
		CallService callService = new CallService();
		return callService.sendPostRequest("http://127.0.0.1:8080/showData",NameAndAmountList);
		
	}
}

