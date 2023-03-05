package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Toy() {
	constructor(type:String,label:String) : this(){
		this.label=label
		this.type=type
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=null
    open var type:String?=null
    open lateinit var label:String
}