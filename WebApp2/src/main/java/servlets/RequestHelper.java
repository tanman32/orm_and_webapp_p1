package servlets;

import controllers.PersonController;
import controllers.DogController;
import repositories.PersonRepo;
import repositories.PersonRepoDBImpl;
import repositories.DogRepo;
import repositories.DogRepoDBImpl;
import services.*;
import services.DogServiceImpl;
import util.ResourceNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//This Class is acting as a Delegator - to tell our code where it should be processed next.
public class RequestHelper {

    //This is us trying to use Dependency Injection to help decouple our classes from having to manage
    // Objects (dependencies) that they need
    static DogRepo mr = new DogRepoDBImpl();
    static DogService ms = new DogServiceImpl(mr);
    static DogController mc = new DogController(ms);

    static PersonRepo mr2 = new PersonRepoDBImpl();
    static PersonService ms2 = new PersonServiceImpl(mr2);
    static PersonController mc2 = new PersonController(ms2);

    /**
     * This method will delegate other methods on the Controller layer of our application
     * to process the request
     * @param request - the HTTP Request
     * @param response - the HTTP Response
     */
    public static void getProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String uri = request.getRequestURI();
        System.out.println(uri);

        String[] uriTokens = uri.split("/");
        System.out.println(Arrays.toString(uriTokens));

        switch (uriTokens.length) {
            //if the uriTokens only has two elements, a blank element and the project name, then nothing to process.
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            //if the uriTokens is exactly 3 then it also has the collection name, but no path parameter.
            case 3: {
                //Call our getAll<Insert Entity Here> methods.
                if(("dog").equals(uriTokens[2])) mc.getDogs(request, response);
                else if(("person").equals(uriTokens[2])) mc2.getPersons(request, response);
                else response.sendError(400, "Collection does not exist");
                break;
            }
            case 4: {
                //Call our get<Insert Entity Here> by Id service method.
                request.setAttribute("id", uriTokens[3]);
                if(("dog").equals(uriTokens[2])) mc.getDogById(request, response);
                if(("person").equals(uriTokens[2])) mc2.getPersonById(request, response);
                break;
            }
            default: {
                response.sendError(400);
                break;
            }
        }

    }

    public static void postProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String uri = request.getRequestURI();
        System.out.println(uri);

        String[] uriTokens = uri.split("/");
        System.out.println(Arrays.toString(uriTokens));

        switch (uriTokens.length) {
            //if the uriTokens only has two elements, a blank element and the project name, then nothing to process.
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            //if the uriTokens is exactly 3 then it also has the collection name, but no path parameter.
            case 3: {
                //Call our getAll<Insert Entity Here> methods.
                if (("dog").equals(uriTokens[2])) mc.addDog(request, response);
                else if (("person").equals(uriTokens[2])) mc2.addPerson(request, response);
                else response.sendError(400, "Collection does not exist");
                break;
            }
            default: {
                response.sendError(400);
                break;
            }

        }

    }

    public static void putProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();
        System.out.println(uri);

        String[] uriTokens = uri.split("/");
        System.out.println(Arrays.toString(uriTokens));

        switch (uriTokens.length) {
            //if the uriTokens only has two elements, a blank element and the project name, then nothing to process.
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            //if the uriTokens is exactly 3 then it also has the collection name, but no path parameter.
            case 4: {
                int id = 0;
                //Test
                String input = uriTokens[3];

                if(input.matches("[0-9]+")) {
                    id = Integer.parseInt(input);
                } else {
                    response.sendError(400, "ID is not a number");
                    return;
                }

                //Test
                request.setAttribute("id", id);
                if (("dog").equals(uriTokens[2])) mc.updateDog(request, response);
                else if (("person").equals(uriTokens[2])) mc2.updatePerson(request, response);
                else response.sendError(400, "Collection does not exist");
                break;
            }
            default: {
                response.sendError(400);
                break;
            }

        }

    }

    public static void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ResourceNotFoundException {

        String uri = request.getRequestURI();
        System.out.println(uri);

        String[] uriTokens = uri.split("/");
        System.out.println(Arrays.toString(uriTokens));

        switch (uriTokens.length) {
            //if the uriTokens only has two elements, a blank element and the project name, then nothing to process.
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            //if the uriTokens is exactly 3 then it also has the collection name, but no path parameter.
            case 4: {
                int id = 0;
                String input = uriTokens[3];

                if(input.matches("[0-9]+")) {
                    id = Integer.parseInt(input);
                } else {
                    response.sendError(400, "ID is not a number");
                    return;
                }

                request.setAttribute("id", id);
                if (("dog").equals(uriTokens[2])) mc.deleteDog(request, response);
                else if (("person").equals(uriTokens[2])) mc2.deletePerson(request, response);
                else response.sendError(400, "Collection does not exist");
                break;
            }
            default: {
                response.sendError(400);
                break;
            }

        }
    }
}
