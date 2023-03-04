package edu.spring.dogs.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

    @Repository
    interface DogRepository: CrudRepository<Dog, Int> {
		
		fun findByMasterIsNull():List<Dog>{
			var doggos : MutableList<Dog> = mutableListOf<Dog>()
			for(dog in findAll()){
				if(dog.master == null) doggos += dog
			}
			return doggos
		}
		
		fun findByNameAndMasterId(name:String, id:Int):Dog?{
			return findAll().find { it.name.equals(name) && it.master?.id == id }
		}
    }