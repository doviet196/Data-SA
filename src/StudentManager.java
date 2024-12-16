import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }
    public void addStudent(String id, String name, double marks) {
        if (marks < 0 || marks > 10) {
            System.out.println("Invalid Marks! Please enter marks between 0 and 10.");
            return;
        }
        students.add(new Student(id, name, marks));
    }

    public void editStudent(String id, String newName, double newMarks) {
        boolean studentFound = false;

        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.set(students.indexOf(student), new Student(id, newName, newMarks));
                studentFound = true;
                break;
            }
        }

        if (!studentFound) {
            System.out.println("Student with ID " + id + " not found.");
        }

    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public void bubbleSortByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    public void mergeSortByMarks() {
        students = mergeSort(students);

    }
    private ArrayList<Student> mergeSort(ArrayList<Student> studentList) {
        if (studentList.size() <= 1) {
            return studentList;
        }
        int middle = studentList.size() / 2;
        ArrayList<Student> left = new ArrayList<>(studentList.subList(0, middle));
        ArrayList<Student> right = new ArrayList<>(studentList.subList(middle, studentList.size()));
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private ArrayList<Student> merge(ArrayList<Student> left, ArrayList<Student> right) {
        ArrayList<Student> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMarks() >= right.get(j).getMarks()) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }
    public void heapSortByMarks() {
        int n = students.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(students, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Student temp = students.get(0);
            students.set(0, students.get(i));
            students.set(i, temp);

            heapify(students, i, 0);
        }
    }

    private void heapify(ArrayList<Student> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left).getMarks() > list.get(largest).getMarks()) {
            largest = left;
        }

        if (right < n && list.get(right).getMarks() > list.get(largest).getMarks()) {
            largest = right;
        }

        if (largest != i) {
            Student swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);

            heapify(list, n, largest);
        }
    }

    public Student searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }


    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
}