package repositories;

import models.Dog;
import util.ResourceNotFoundException;

import java.util.List;

/*
Coding to Interfaces:

    - We can outline all the functionality that will be present in any implementation
    of this abstract concept. In other words, we can describe what we want our Movie
    Repository to be able to do, and any implementation of such should achieve the same goals.
 */
public interface DogRepo {

    //Fulfill the CRUD Operations for Movie
    public Dog addDog(Dog m);
    public Dog getDog(int id);
    public List<Dog> getAllDogs();
    public Dog updateDog(Dog change);
    public Dog deleteDog(int id) throws ResourceNotFoundException;

}
