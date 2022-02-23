package services;

import models.Dog;
import util.ResourceNotFoundException;

import java.util.List;

public interface DogService {

    /*
    Sometimes your actual goal at some point in your application
    will be to perform something as basic as just a CRUD operations.
    However, what we want to avoid is entangling the various layers of our application.
    And we want to maintain order in the control flow of execution.
    Thereby, if we need to perform a simple CRUD operation we shouldn't
    skip layers, such as the Service Layer, but instead, trivially, pass through it.
     */

    //You may opt for having some Trivial Service Methods:
    public Dog addDog(Dog m);
    public Dog getDog(int id);
    public List<Dog> getAllDogs();
    public Dog updateDog(Dog change);
    public Dog deleteDog(int id) throws ResourceNotFoundException;
}
