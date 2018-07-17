public class Course {

    private String courseName;
    private int year;
    private int EAPs;

    Course(String initCourseName, int initYear, int initEAPs){
        this.courseName = initCourseName;
        this.year = initYear;
        this.EAPs = initEAPs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (year != course.year) return false;
        if (EAPs != course.EAPs) return false;
        return courseName != null ? courseName.equals(course.courseName) : course.courseName == null;

    }

    @Override
    public int hashCode() {
        int result = courseName != null ? courseName.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + EAPs;
        return result;
    }
}
