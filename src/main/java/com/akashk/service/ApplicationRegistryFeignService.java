package com.akashk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "Application-Registration")

public interface ApplicationRegistryFeignService {
	
	@GetMapping("/citizen/check/{citizenId}")
	public Boolean checkCitizen(@PathVariable Integer citizenId);

}
