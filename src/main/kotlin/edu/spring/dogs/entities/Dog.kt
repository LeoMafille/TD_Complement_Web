package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Dog() {
	constructor(name:String) : this(){
		this.name = name
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	open var id:Int?=0

	@Column(length = 20, unique = true, nullable = false)
	open lateinit var name:String

	@OneToOne
	open var master:Master?=null

	@OneToMany
	open val toys = mutableSetOf<Toy>()
}