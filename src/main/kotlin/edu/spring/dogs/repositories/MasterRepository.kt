package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Master
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

    @Repository
    interface MasterRepository: CrudRepository<Master, Int> {

    }