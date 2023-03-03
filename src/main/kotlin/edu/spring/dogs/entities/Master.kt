package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Master() {
	constructor(firstname:String,lastname:String) : this(){
		this.firstname = firstname
		this.lastname = lastname
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=0
    open lateinit var firstname:String
	open lateinit var lastname:String
	
	@OneToMany(cascade=[])
    open var dogs:Set<Dog> = mutableSetOf<Dog>()
	
	@PreRemove
	fun preRemove():Unit{
		for(dog in dogs){
			dog.master = null
		}
	}
	
	fun addDog(dog: Dog):Boolean{
		if(!dogs.contains(dog)){
			dogs += dog
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