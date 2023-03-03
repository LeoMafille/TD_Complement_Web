package edu.spring.dogs.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

    @Repository
    interface DogRepository: CrudRepository<Dog, Int> {
		
		open fun findByMasterIsNull():Set<Dog>{
			var doggos : Set<Dog> = mutableSetOf<Dog>()
			
			for(dog in findAll()){
				if(dog.master == null) doggos += dog
			}
			
			return doggos
		}
		
		open fun findByNameAndMasterId(name:String, id:Int):Dog?{
			for(dog in findAll()){
				if(dog.name == name && dog.master?.id == id) return dog;
			}
			return null
		}
    }