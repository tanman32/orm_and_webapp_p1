package repositories;

import models.Person;
import util.JDBCConnection;
import util.ResourceNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepoDBImpl implements PersonRepo{

        Connection conn = JDBCConnection.getConnection();

        @Override
        public Person addPerson(Person m) {

            String sql = "INSERT INTO person VALUES (default,?,?,?,?,?) RETURNING *";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                //set values for all the placeholders: ?
                ps.setString(1, m.getName());
                ps.setString(2, m.getAge());
                ps.setString(3,m.getHeight());
                ps.setString(4, m.getBuild());
                ps.setString(5, m.getTalent());

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    return buildPerson(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Person getPerson(int id) {

            //Make a String for the SQL statement you want executed. Use Placeholders for data values.
            String sql = "SELECT * FROM person WHERE p_id = ?";

            try {
                //Set up PreparedStatement
                PreparedStatement ps = conn.prepareStatement(sql);
                //Set values for any Placeholders
                ps.setInt(1, id);

                //Execute the query, store the results -> ResultSet
                ResultSet rs = ps.executeQuery();

                //Extract results our of ResultSet
                if(rs.next()) {
                    return buildPerson(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<Person> getAllPersons() {

            String sql = "SELECT * FROM person";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                //Extract all movies out of the ResultSet
                List<Person> persons = new ArrayList<>();
                while(rs.next()) {
                    //Add each movie to our list of movies.
                    persons.add(buildPerson(rs));
                }
                return persons;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public Person updatePerson(Person change) {

            String sql = "UPDATE person set name=?, age=?, height=?, build=?, talent=? WHERE p_id = ? RETURNING *";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, change.getName());
                ps.setString(2, change.getAge());
                ps.setString(3, change.getHeight());
                ps.setString(4, change.getBuild());
                ps.setString(5, change.getTalent());
                ps.setInt(6, change.getId());

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    return buildPerson(rs);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public Person deletePerson(int id) throws ResourceNotFoundException {

            String sql = "DELETE FROM person WHERE p_id = ? RETURNING *";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

//                if(rs.next()) {
//                    return buildPerson(rs);
//                } else {
//                    throw new ResourceNotFoundException("Resource with id: " + id + " was not found in database.");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Helper Method
        private Person buildPerson(ResultSet rs) throws SQLException {
            Person m = new Person();
            m.setId(rs.getInt("p_id"));
            m.setName(rs.getString("name"));
            m.setAge(rs.getString("age"));
            m.setHeight(rs.getString("height"));
            m.setBuild(rs.getString("build"));
            m.setTalent(rs.getString("talent"));
            return m;
        }
}
