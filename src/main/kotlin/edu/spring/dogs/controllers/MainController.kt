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
		masterRepos.save(master)
		if(master!=null){
			when (action){
				"add" -> {
					if(master != null){
						var newDog: Dog = Dog(dog.name)

						newDog.master = master

						dogRepos.save(newDog)
					}
				}
				"give-up" -> {
					if(master != null){
						var remDog = dogRepos.findByNameAndMasterId(dog.name, id)
						if(remDog != null){
							master.giveUpDog(remDog)
							dogRepos.save(remDog)
						}
					}
				}
			}
		}
		return RedirectView("/")
	}
	@GetMapping("master/{id}/delete")
	fun deleteMaster(@PathVariable id:Int): RedirectView {
		val master = masterRepos.findById(id).orElse(null)
		if (master != null) {
			for(dog in dogRepos.findAll()){
				if(dog.master?.id == id) dog.master = null
			}
			//master.preRemove()
			masterRepos.delete(master)
		}
		return RedirectView("/")
	}
	@PostMapping("/dog/{id}/action")
	fun manageDogById(
			@PathVariable id:Int,
			@RequestParam("dog-action") action: String,
			@RequestParam("choixMaitre") idMaitre: Int
	):RedirectView{
		val dog=dogRepos.findById(id).orElse(null)
		val master=masterRepos.findById(idMaitre).orElse(null)
		masterRepos.save(master)
		if(master!=null){
			when (action){
				"remove" -> {
					dogRepos.delete(dog)
				}
				"adopt" -> {
					dog.master = master;
					master.addDog(dog)
					dogRepos.save(dog)
				}
			}
		}
		return RedirectView("/")
	}
}