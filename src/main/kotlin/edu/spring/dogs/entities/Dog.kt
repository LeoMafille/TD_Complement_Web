package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Dog() {
	constructor(name: String) : this() {
		this.name = name
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=0
    
	open lateinit var name:String

	@ManyToOne
	open var master:Master?=null
	
	@OneToMany
	open var toys:MutableSet<Toy> = mutableSetOf<Toy>()

}