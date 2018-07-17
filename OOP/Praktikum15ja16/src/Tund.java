import java.util.HashMap;
import java.util.Map;

public class Tund {



    public static void main(String[] args) {

        Map<Course, String> courseTeachers = new HashMap<>();

        Course kursus1 = new Course("OOP", 2016, 6);
        Course kursus2 = new Course("OOP", 2016, 6);
        Course kursus3 = new Course("Stuff", 2016, 3);
        courseTeachers.put(kursus1, "Taavi");
        courseTeachers.put(kursus2, "Märt");
        courseTeachers.put(kursus3, "Someone");

    }
}
