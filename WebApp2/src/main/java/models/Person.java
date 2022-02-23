package models;
//import annotations.Column;
//import annotations.Table;

//@Table(name = "person")
public class Person {

   // @Column(name = "p_id", primaryKey = true)
    private int id;
   // @Column(name = "name")
    private String name;
   // @Column(name = "age")
    private String age;
   // @Column(name = "height")
    private String height;
    //@Column(name = "build")
    private String build;
    //@Column(name = "talent")
    private String talent;

    public Person() {
    }

    public Person(String name, String age, String height, String build, String talent) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.build = build;
        this.talent = talent;
    }

    public Person(int id, String name, String age, String height, String build, String talent) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.build = build;
        this.talent = talent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", build='" + build + '\'' +
                ", talent='" + talent + '\'' +
                '}';
    }
}
