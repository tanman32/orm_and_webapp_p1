package controllers;

import com.google.gson.Gson;
import models.Dog;
import services.DogService;
import util.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class DogController {

    DogService ms;
    Gson gson = new Gson();

    public DogController(DogService ms) {
        this.ms = ms;
    }

    //This method should be called when we want to get a movie.
    public void getDogById(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String input = request.getAttribute("id").toString();
        int id = 0;
        if(input.matches("[0-9]+")) {
            id = Integer.parseInt(input);
        } else {
            response.sendError(400, "ID is not a number");
            return;
        }

        Dog m = ms.getDog(id);

        response.getWriter().append((m != null) ? gson.toJson(m): "{}");
    }

    public void getDogs(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().append(gson.toJson(ms.getAllDogs()));

    }


    public void addDog(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Extract data/information from the request
        BufferedReader reader = request.getReader();
        Dog m = gson.fromJson(reader, Dog.class);

        //Call some service(s) to process the data/information
        m = ms.addDog(m);

        //Generate a response from that processed data.
        if(m != null) {
            response.setStatus(201);
            response.getWriter().append(gson.toJson(m));
        } else {
            response.getWriter().append("{}");
        }


    }

    public void updateDog(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Dog m = gson.fromJson(request.getReader(), Dog.class);
        m.setId((int) request.getAttribute("id"));
        //System.out.println(m.getId());

        m = ms.updateDog(m);
        //System.out.println("The value of m is: " + m + "********************************");

        response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
    }

    public void deleteDog(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException, IOException {

        int id = (int) request.getAttribute("id");

        Dog m = ms.deleteDog(id);

//        response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
        response.setStatus(204);
    }
}
