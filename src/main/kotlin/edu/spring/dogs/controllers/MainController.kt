package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.DogRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import java.lang.ProcessBuilder.Redirect

@Controller
class MainController {
	
	@Autowired
	lateinit var masterRepos: MasterRepository

	@Autowired
	lateinit var dogRepos: DogRepository
	
	@RequestMapping("/", "")
	fun indexAction(modelMap: ModelMap):String{

		masterRepos.deleteAll()
		dogRepos.deleteAll()

		modelMap["proprios"]=masterRepos.findAll()
		modelMap["dogsAdoption"]=dogRepos.findByMasterIsNull()

		return "index"
	}
	
	@PostMapping("/master/add")
	fun addMasterAction(
			@ModelAttribute master: Master,
			@RequestParam("addButton") action: String,
	):RedirectView {
		masterRepos.save(master)

		return RedirectView("/")
	}
	
	@PostMapping("/master/{id}/dog")
	fun manageDogsByMasterId(
		@PathVariable id:Int,
		@ModelAttribute dog: Dog,
		@RequestParam("dog-action") action: String
	):RedirectView{
		val master=masterRepos.findById(id).orElse(null)
		if(master!=null){
			when (action){
				"add" -> {
					dog.master = master
					dogRepos.save(dog)
				}
				"giveup" -> {
					dog.master = null
					dogRepos.save(dog)
				}
			}

		}
		return RedirectView("/")
	}
	
	@GetMapping("/master/{id}/delete")
	fun deleteMasterById(
		@PathVariable id:Int
	): RedirectView {
		masterRepos.deleteById(id)
		return RedirectView("/")
	}
	
	@PostMapping("/dog/{id}/action")
	fun manageDogById(
		@PathVariable id:Int
	){
		
	}
}