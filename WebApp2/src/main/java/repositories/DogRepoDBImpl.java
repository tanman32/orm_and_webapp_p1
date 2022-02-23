package repositories;

import models.Dog;
import util.JDBCConnection;
import util.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogRepoDBImpl implements DogRepo {

    Connection conn = JDBCConnection.getConnection();

    @Override
    public Dog addDog(Dog m) {

        String sql = "INSERT INTO dog VALUES (default,?,?,?,?,?) RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //set values for all the placeholders: ?
            ps.setString(1, m.getName());
            ps.setString(2, m.getAge());
            ps.setString(3,m.getBreed());
            ps.setString(4, m.getPersonality());
            ps.setString(5, m.getFurcolor());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildDog(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Dog getDog(int id) {

        //Make a String for the SQL statement you want executed. Use Placeholders for data values.
        String sql = "SELECT * FROM dog WHERE d_id = ?";

        try {
            //Set up PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //Set values for any Placeholders
            ps.setInt(1, id);

            //Execute the query, store the results -> ResultSet
            ResultSet rs = ps.executeQuery();

            //Extract results our of ResultSet
            if(rs.next()) {
                return buildDog(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dog> getAllDogs() {

        String sql = "SELECT * FROM dog";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            //Extract all movies out of the ResultSet
            List<Dog> dogs = new ArrayList<>();
            while(rs.next()) {
                //Add each movie to our list of movies.
                dogs.add(buildDog(rs));
            }
            return dogs;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Dog updateDog(Dog change) {

        String sql = "UPDATE dog SET name=?, age=?, breed=?, personality=?, furcolor=? WHERE d_id = ? RETURNING *";
        System.out.println(sql);
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, change.getName());
            ps.setString(2, change.getAge());
            ps.setString(3, change.getBreed());
            ps.setString(4, change.getPersonality());
            ps.setString(5, change.getFurcolor());
            ps.setInt(6, change.getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildDog(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Dog deleteDog(int id) throws ResourceNotFoundException {

        String sql = "DELETE FROM dog WHERE d_id = ? RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

//            if(rs.next()) {
//                return buildDog(rs);
//            } else {
//                throw new ResourceNotFoundException("Resource with id: " + id + " was not found in database.");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Helper Method
    private Dog buildDog(ResultSet rs) throws SQLException {
        Dog m = new Dog();
        m.setId(rs.getInt("d_id"));
        m.setName(rs.getString("name"));
        m.setAge(rs.getString("age"));
        m.setBreed(rs.getString("breed"));
        m.setPersonality(rs.getString("personality"));
        m.setFurcolor(rs.getString("furcolor"));
        return m;
    }
}
