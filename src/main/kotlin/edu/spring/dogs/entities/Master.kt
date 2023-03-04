package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Master() {
	constructor(firstname:String,lastname:String) : this(){
		this.firstname = firstname
		this.lastname = lastname
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int=0
    open lateinit var firstname:String
	open lateinit var lastname:String
	
	@OneToMany(cascade=[CascadeType.PERSIST, CascadeType.MERGE])
    open var dogs:MutableSet<Dog> = mutableSetOf<Dog>()
	
	@PreRemove
	fun preRemove():Unit{
		for(dog in dogs){
			dog.master = null
		}
		dogs.clear()
	}
	
	fun addDog(dog: Dog):Boolean{
		if(!dogs.contains(dog)){
			dogs += dog
			dog.master = this
			return true
		}
		return false
	}
	
	fun giveUpDog(dog: Dog):Boolean{
		if(dogs.contains(dog)){
			dogs -= dog
			dog.master = null
			return true
		}
		return false
	}
}