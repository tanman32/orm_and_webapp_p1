package repositories;

import models.Dog;
import models.Person;
import util.ResourceNotFoundException;

import java.util.List;

public interface PersonRepo {
    public Person addPerson(Person m);
    public Person getPerson(int id);
    public List<Person> getAllPersons();
    public Person updatePerson(Person change);
    public Person deletePerson(int id) throws ResourceNotFoundException;
}
