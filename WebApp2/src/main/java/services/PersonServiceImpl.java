package services;

import models.Person;
import repositories.PersonRepo;
import util.ResourceNotFoundException;

import java.util.List;

public class PersonServiceImpl implements PersonService{


        private PersonRepo mr;

        //This is a process called Dependency Injection
    public PersonServiceImpl(PersonRepo mr) {
            //Constructor Injection -> When the dependency need for the Class is fulfilled in a Constructor.
            this.mr = mr;
        }

        //Our Trivial Services
        @Override
        public Person addPerson(Person m) {
            return mr.addPerson(m);
        }

        @Override
        public Person getPerson(int id) {
            return mr.getPerson(id);
        }

        @Override
        public List<Person> getAllPersons() {
            return mr.getAllPersons();
        }

        @Override
        public Person updatePerson(Person change) {
            return mr.updatePerson(change);
        }

        @Override
        public Person deletePerson(int id) throws ResourceNotFoundException {
            return mr.deletePerson(id);
        }

    }
