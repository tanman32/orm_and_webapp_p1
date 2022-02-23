package services;

import controllers.DogController;
import models.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.DogRepo;
import repositories.DogRepoDBImpl;

import static org.junit.jupiter.api.Assertions.*;

class DogServiceImplTest {

    @Test
    void addDog() {
        Dog d = new Dog("Bosco","7","Dalmation","Curious","Black and White");
        DogRepo dr3 = new DogRepoDBImpl();
        DogService ds3 = new DogServiceImpl(dr3);
        DogController dc3 = new DogController(ds3);
        Assertions.assertEquals(d.getName(), ds3.addDog(d).getName());
    }

    @Test
    void getDog() {
        Dog d = new Dog("Bosco","7","Dalmation","Curious","Black and White");
        DogRepo dr3 = new DogRepoDBImpl();
        DogService ds3 = new DogServiceImpl(dr3);
        Assertions.assertEquals(null, ds3.getDog(d.getId()));
    }

    @Test
    void getAllDogs() {
        Dog d = new Dog("Bosco","7","Dalmation","Curious","Black and White");
        DogRepo dr3 = new DogRepoDBImpl();
        DogService ds3 = new DogServiceImpl(dr3);
        DogController dc3 = new DogController(ds3);
        Assertions.assertNotEquals(d, ds3.getAllDogs());
}
    @Test
    void updateDog() {
        Dog d = new Dog("Bosco","7","Dalmation","Curious","Black and White");
        DogRepo dr3 = new DogRepoDBImpl();
        DogService ds3 = new DogServiceImpl(dr3);
        Assertions.assertEquals(null , ds3.updateDog(d));
    }

    @Test
    void deleteDog() {
        Dog d = new Dog("Bosco","7","Dalmation","Curious","Black and White");
        DogRepo dr3 = new DogRepoDBImpl();
        DogService ds3 = new DogServiceImpl(dr3);
        DogController dc3 = new DogController(ds3);
        Assertions.assertNotEquals(null , ds3);
    }
}