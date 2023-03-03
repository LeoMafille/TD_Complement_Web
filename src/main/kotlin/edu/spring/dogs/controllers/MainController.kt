package edu.spring.dogs.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
	
	@RequestMapping("/", "")
	fun indexAction(){
		
	}
	
	@PostMapping("/master/add")
	fun addMasterAction(){
		
	}
	
	@PostMapping("/master/{id}/dog")
	fun manageDogsByMasterId(
		@PathVariable id:Int
	){
		
	}
	
	@GetMapping("/master/{id}/delete")
	fun deleteMasterById(
		@PathVariable id:Int
	){
		
	}
	
	@PostMapping("/dog/{id}/action")
	fun manageDogById(
		@PathVariable id:Int
	){
		
	}
}