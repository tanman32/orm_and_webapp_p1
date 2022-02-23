package controllers;

import com.google.gson.Gson;
import models.Person;
import services.PersonService;
import util.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class PersonController {
    PersonService ms;
    Gson gson = new Gson();

    public PersonController(PersonService ms) {
        this.ms = ms;
    }

    //This method should be called when we want to get a movie.
    public void getPersonById(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String input = request.getAttribute("id").toString();
        int id = 0;
        if(input.matches("[0-9]+")) {
            id = Integer.parseInt(input);
        } else {
            response.sendError(400, "ID is not a number");
            return;
        }

        Person m = ms.getPerson(id);

        response.getWriter().append((m != null) ? gson.toJson(m): "{}");
    }

    public void getPersons(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().append(gson.toJson(ms.getAllPersons()));

    }


    public void addPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Extract data/information from the request
        BufferedReader reader = request.getReader();
        Person m = gson.fromJson(reader, Person.class);

        //Call some service(s) to process the data/information
        m = ms.addPerson(m);

        //Generate a response from that processed data.
        if(m != null) {
            response.setStatus(201);
            response.getWriter().append(gson.toJson(m));
        } else {
            response.getWriter().append("{}");
        }


    }

    public void updatePerson(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Person m = gson.fromJson(request.getReader(), Person.class);
        m.setId((int) request.getAttribute("id"));

        m = ms.updatePerson(m);

        response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
    }

    public void deletePerson(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException, IOException {

        int id = (int) request.getAttribute("id");

        Person m = ms.deletePerson(id);

//        response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
        response.setStatus(204);
    }
}
