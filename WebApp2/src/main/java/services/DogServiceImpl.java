package services;

import models.Dog;
import repositories.DogRepo;
import util.ResourceNotFoundException;
import java.util.List;

public class DogServiceImpl implements DogService {

    private DogRepo mr;

    //This is a process called Dependency Injection
    public DogServiceImpl(DogRepo mr) {
        //Constructor Injection -> When the dependency need for the Class is fulfilled in a Constructor.
        this.mr = mr;
    }

    //Our Trivial Services
    @Override
    public Dog addDog(Dog m) {
        return mr.addDog(m);
    }

    @Override
    public Dog getDog(int id) {
        return mr.getDog(id);
    }

    @Override
    public List<Dog> getAllDogs() {
        return mr.getAllDogs();
    }

    @Override
    public Dog updateDog(Dog change) {
        return mr.updateDog(change);
    }

    @Override
    public Dog deleteDog(int id) throws ResourceNotFoundException {
        return mr.deleteDog(id);
    }

}
