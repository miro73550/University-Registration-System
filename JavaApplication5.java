import java.util.Stack;
import java.util.Scanner;


class Node {
    int sID;
    int cID;
    Node next, sc;

    public Node(int ID) {
        this.sID = ID;
        this.sID = ID;
        next = null;
        sc = null;
    }

    public Node(int sID, int cID) {
        this.sID = sID;
        this.cID = cID;
        next = null;
        sc = null;
    }
}

class student {
    static Node head;
    static Node tail;
    static int count;
    static Node LastStudent;

    public boolean ISexisted(int ID) {
        Node temp = head;
        while (temp != null) {
            if (temp.sID == ID) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void addstudent(int studentID) {
        if (!ISexisted(studentID)) {
            Node newStudent = new Node(studentID);
            if (head == null && tail == null) {
                head = tail = newStudent;
            } else {
                tail.next = newStudent;
                tail = tail.next;
            }

            count++;
        } else {
            System.out.println("Student " + studentID + " already exists.");
        }
    }

    public int removeStudent(int studentID) {
        if (ISexisted(studentID)) {
            course c = new course();
            Node student = getStudent(studentID);
            Node current = student.sc;
            while (current != null) {
                Node course = c.getCourse(current.cID);
                Node temp1 = course.sc;
                if (temp1.next == null) {
                    course.sc = null;
                } else if (temp1.sID == studentID) {

                    course.sc = temp1.next;
                } else {
                    while (temp1.next.sID != studentID) {
                        temp1 = temp1.next;
                    }
                    if (temp1.next.next == null) {
                        temp1.next = null;
                    } else {
                        temp1.next = temp1.next.next;
                    }

                }
                current = current.sc;
            }

            Node tmp = head;
            if (tmp.next == null) {
                head = null;
                return tmp.sID;
            } else if (tmp.sID == studentID) {
                head = head.next;
                return tmp.sID;
            } else {
                while (tmp.next.sID != studentID) {
                    tmp = tmp.next;
                }
                int z = student.sID;
                if (tmp.next.next == null) {
                    tmp.next = null;
                    tail = tmp;
                } else {
                    tmp.next = tmp.next.next;
                }
                return z;
            }
        }

        return -1;

    }

    public int getLastStudentAdded() {
        if (head == null && tail == null) {
            return -1;
        } else {
            return tail.sID;
        }
    }

    public Node getStudent(int ID) {
        Node tmp = head;
        while (tmp.sID != ID) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public void display() {
        if (head != null) {
            System.out.println("In our university system we have : ");
            for (Node tmp = head; tmp != null; tmp = tmp.next) {
                System.out.println("Student with ID : " + tmp.sID);
            }
        } else {
            System.out.println("There are no students in our university system yet.");
        }
    }
}

class course {
    static Node head;
    static Node tail;
    static int count;
    static Node LastCourse;

    public void addcourse(int courseID) {
        if (!ISexisted(courseID)) {
            Node newCourse = new Node(courseID);
            if (head == null && tail == null) {
                head = tail = newCourse;
            } else {
                tail.next = newCourse;
                tail = tail.next;
            }
            count++;
        } else {
            System.out.println("Course " + courseID + " already exists.");
        }

    }

    public boolean ISexisted(int ID) {
        Node temp = head;
        while (temp != null) {
            if (temp.sID == ID) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int removeCourse(int courseID) {
        if (ISexisted(courseID)) {
            student s = new student();
            Node course = getCourse(courseID);
            Node current = course.sc;
            while (current != null) {
                Node student = s.getStudent(current.sID);
                Node temp1 = student.sc;
                if (temp1.sc == null) {
                    student.sc = null;
                } else if (temp1.cID == courseID) {
                    student.sc = temp1.sc;
                } else {
                    while (temp1.sc.cID != courseID) {
                        temp1 = temp1.sc;
                    }
                    if (temp1.sc.sc == null) {
                        temp1.sc = null;
                    } else {
                        temp1.sc = temp1.sc.sc;
                    }
                }

                current = current.next;
            }

            Node tmp = head;
            if (tmp.next == null) {
                head = null;
                return tmp.sID;
            } else if (tmp.sID == courseID) {
                head = head.next;
                return tmp.sID;
            } else {
                while (tmp.next.sID != courseID) {
                    tmp = tmp.next;
                }
                int z = course.sID;
                if (tmp.next.next == null) {
                    tmp.next = null;
                    tail = tmp;
                } else {
                    tmp.next = tmp.next.next;
                }
                return z;
            }
        }

        return -1;

    }

    public int getLastCourseAdded() {
        if (head == null && tail == null) {
            return -1;
        } else {
            return tail.sID;
        }
    }

    public Node getCourse(int ID) {
        Node tmp = head;
        if (ISexisted(ID)) {

            while (tmp.sID != ID) {
                tmp = tmp.next;
            }

        }
        return tmp;
    }

    public void display() {
        if (head != null) {
            System.out.println("In our university system we have : ");
            for (Node tmp = head; tmp != null; tmp = tmp.next) {
                System.out.println("Course with ID : " + tmp.sID);
            }
        } else {
            System.out.println("There are no courses in our university system yet.");
        }
    }
}

class Registration {
    student S;
    course C;
    stack record;

    public Registration(student S, course C, stack record) {
        this.S = S;
        this.C = C;
        this.record = record;
    }

    public void enroll(int sID, int cID) {
        if (!isenrolled(sID, cID)) {
            if (S.ISexisted(sID)) {
                if (C.ISexisted(cID)) {
                    if (!isFullCourse(cID)) {
                        int count = isNormalStudent(sID);
                        if (count < 7) {
                            Node student = S.getStudent(sID);
                            Node course = C.getCourse(cID);
                            Node enrollment = new Node(sID, cID);
                            if (student.sc == null || student.sc.cID > cID) {
                                enrollment.sc = student.sc;
                                student.sc = enrollment;
                            } else {
                                Node temp = student.sc;
                                while (temp.sc != null && temp.sc.cID < cID) {
                                    temp = temp.sc;
                                }
                                enrollment.sc = temp.sc;
                                temp.sc = enrollment;
                            }
                            if (course.sc == null || course.sc.sID > sID) {
                                enrollment.next = course.sc;
                                course.sc = enrollment;
                            } else {
                                Node temp = course.sc;
                                while (temp.next != null && temp.next.sID < sID) {
                                    temp = temp.next;
                                }
                                enrollment.next = temp.next;
                                temp.next = enrollment;
                            }
                            System.out.println("Student " + sID + " Enrolled Successfully in Course " + cID);
                        } else {
                            System.out.println("Student " + sID + " Has Enrolled 7 Courses Already.");
                        }
                    } else {
                        System.out.println("Course " + cID + " is Full.");
                    }
                    record.saveAction(new Action("enroll", sID, cID));
                } else {
                    System.out.println("Course " + cID + " doesn't exist");
                }
            } else {
                System.out.println("Student " + sID + " doesn't exist");
            }

        } else {
            System.out.println("Student With ID: " + sID +
                    " is Enrolled Already in Course: " + cID);
        }
    }

    public boolean isenrolled(int s_ID, int c_ID) {

        if (S.ISexisted(s_ID)) {
            if (C.ISexisted(c_ID)) {
                Node s = S.getStudent(s_ID);
                Node tmp = s.sc;
                while (tmp != null) {
                    if (tmp.cID == c_ID) {
                        return true;
                    }
                    tmp = tmp.sc;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public int removEnrollment(int sID, int cID) {
        if (S.ISexisted(sID)) {
            if (C.ISexisted(cID)) {
                if (isenrolled(sID, cID)) {
                    Node removed;
                    Node course = C.getCourse(cID);
                    Node temp1 = course.sc;
                    if (temp1.next == null) {
                        course.sc = null;
                    } else if (temp1.sID == sID) {
                        course.sc = temp1.next;
                    } else {
                        while (temp1.next.sID != sID) {
                            temp1 = temp1.next;
                        }
                        temp1.next = temp1.next.next;
                    }
                    Node student = S.getStudent(sID);
                    Node temp2 = student.sc;
                    if (temp2.sc == null) {
                        removed = temp2;
                        student.sc = null;
                        System.out.println("Student With ID " + removed.sID +
                                " Has Been Removed from Course " + removed.cID);
                        record.saveAction(new Action("remove", sID, cID));
                        return removed.sID;
                    } else if (temp2.cID == cID) {
                        removed = temp2;
                        student.sc = temp2.sc;
                        System.out.println("Student With ID " + removed.sID +
                                " Has Been Removed from Course " + removed.cID);
                        record.saveAction(new Action("remove", sID, cID));
                        return removed.sID;
                    } else {
                        while (temp2.sc.cID != cID) {
                            temp2 = temp2.sc;
                        }
                        removed = temp2.sc;
                        temp2.sc = temp2.sc.sc;
                        System.out.println("Student With ID " + removed.sID +
                                " Has Been Removed from Course " + removed.cID);
                        record.saveAction(new Action("remove", sID, cID));
                        return removed.sID;
                    }
                } else {
                    System.out.println("Student " + sID + " Hasn't Been Enrolled in Course " + cID + " Before");
                }
            } else {
                System.out.println("Course " + cID + " doesn`t exist");
            }
        } else {
            System.out.println("Student " + sID + " doesn`t exist ");
        }
        return -1;
    }

    public boolean isFullCourse(int cID) {
        int count = 0;
        if (C.ISexisted(cID)) {
            Node course = C.getCourse(cID);
            Node temp = course.sc;
            if (temp == null) {
                return false;
            } else {
                while (temp != null) {
                    count++;
                    temp = temp.next;
                }
            }

        } else {
            System.out.println("Course: " + cID + " doesn`t exist !");
        }
        if (!(count >= 30))
            return false;
        else
            return true;
    }

    public int isNormalStudent(int sID) {
        if (S.ISexisted(sID)) {
            int count = 0;
            Node student = S.getStudent(sID);
            Node temp = student.sc;
            if (temp == null)
                return 0;
            else {
                while (temp != null) {
                    count++;
                    temp = temp.sc;
                }
                if (count >= 2 && count <= 7) {
                    System.out.println("This Studnet is Normal he has enrolled in " + count + " courses.");
                } else {
                    System.out.println("This student is not Normal.");
                }
                return count;
            }

        } else {
            System.out.println("This student doesn't Exist.");
            return -1;
        }
    }

    public void listCoursesByStudent(int sID) {
        if (!S.ISexisted(sID)) {
            System.out.println("Student " + sID + " doesn't exist.");
            return;
        }
        Node Student = S.getStudent(sID);
        Node CourseLink = Student.sc;
        if (CourseLink == null) {
            System.out.println("Student " + sID + " is not enrolled in any courses.");
        } else {
            System.out.println("Courses enrolled by student " + sID + ":");
            while (CourseLink != null) {
                System.out.println("Course ID: " + CourseLink.cID);
                CourseLink = CourseLink.sc;
            }

        }
    }

    public void listStudentByCourse(int cID) {
        if (!C.ISexisted(cID)) {
            System.out.println("Course: " + cID + " does not exist.");
        } else {
            Node Course = C.getCourse(cID);
            Node StudentLink = Course.sc;
            if (StudentLink == null) {
                System.out.println("No one was enrolled in course " + cID);
            } else {
                System.out.println("Students enrolled in course " + cID + ":");
                while (StudentLink != null) {
                    System.out.println("Student ID: " + StudentLink.sID);
                    StudentLink = StudentLink.next;
                }
            }
        }

    }

    public void sortStudentByID(int cID) {
        // we have implemented the sorting inside the enroll method
        listStudentByCourse(cID);
    }

    public void sortCourseByID(int sID) {
        // we have implemented the sorting inside the enroll method
        listCoursesByStudent(sID);
    }
}

class Action {
    private String type; // enroll or remove
    private int sID; // student's details
    private int cID;

    public Action(String type, int sID, int cID) {
        this.type = type;
        this.sID = sID;
        this.cID = cID;
    }

    public String getType() {
        return type;
    }

    public int getsID() {
        return sID;
    }

    public int getcID() {
        return cID;
    }

}

class stack {
    Stack<Action> undostack = new Stack<>();
    Stack<Action> redostack = new Stack<>();

    public void saveAction(Action action) {
        undostack.push(action);
        redostack.clear();
    }

    public void undo(Registration regi) {
        if (undostack.isEmpty()) {
            System.out.println("undo stack is empty ");
            return;
        }
        Action action = undostack.pop();
        switch (action.getType()) { // It is executed in reverse (removing the student from the course)
            case "enroll":
                int test = regi.removEnrollment(action.getsID(), action.getcID());
                if (test != -1) {
                    System.out.println(" Undo last action successfully! ");
                }
                redostack.push(new Action("enroll", action.getsID(), action.getcID()));
                break;

            case "remove":
                regi.enroll(action.getsID(), action.getcID());
                System.out.println("Undo last action successfully! ");
                redostack.push(new Action("remove", action.getsID(), action.getcID()));
                break;
        }

    }

    public void redo(Registration regi) {
        if (redostack.isEmpty()) {
            System.out.println("redo stack is empty ");
            return;
        }
        Action action = redostack.pop();
        switch (action.getType()) {
            case "enroll":
                regi.enroll(action.getsID(), action.getcID());
                System.out.println(" Redo last action successfully! ");
                undostack.push(new Action("enroll", action.getsID(), action.getcID()));
                break;
            case "remove":
                int test = regi.removEnrollment(action.getsID(), action.getcID());
                if (test != -1) {
                    System.out.println("Redo last action successfully! ");

                }
                undostack.push(new Action("remove", action.getsID(), action.getcID()));
                break;

        }

    }

}

public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        student s = new student();
        course c = new course();
        stack record = new stack();
        Registration r = new Registration(s, c, record);

        boolean flag = true;
        while (flag) {
            System.out.println("====================================");
            System.out.println("Hello to our registration system !");
            System.out.println(
                    "Please enter the number of the process you need:\n1. Add student\n2. Add course\n3. Remove student\n4. Remove course\n5. Enroll student in a course\n6. Remove Enrollment\n7. List course by student\n8. List student by course\n9. Get last student added\n10. Get last course added\n11. Is Normal Student\n12. Is Full Course\n13. Sort student by course ID\n14. Sort course by student ID\n15. UNDO\n16. REDO\n17. EXIT");
            System.out.println("====================================");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter the student ID : ");
                    int studentId = input.nextInt();
                    s.addstudent(studentId);
                    break;
                case 2:
                    System.out.println("Please enter the course ID : ");
                    int courseId = input.nextInt();
                    c.addcourse(courseId);
                    break;
                case 3:
                    System.out.println("Please enter the student ID : ");
                    int studentid = input.nextInt();
                    System.out.println("Student " + s.removeStudent(studentid) + " removed successfully");
                    break;
                case 4:
                    System.out.println("Please enter the course ID : ");
                    int courseid = input.nextInt();
                    System.out.println("Course " + c.removeCourse(courseid) + " removed successfully");
                    break;
                case 5:
                    System.out.println("Please enter the student ID : ");
                    int sID = input.nextInt();
                    System.out.println("Please enter the course ID : ");
                    int cID = input.nextInt();
                    r.enroll(sID, cID);
                    break;
                case 6:
                    System.out.println("Please enter the student ID : ");
                    int s_ID = input.nextInt();
                    System.out.println("Please enter the course ID : ");
                    int c_ID = input.nextInt();
                    System.out.println(r.removEnrollment(s_ID, c_ID));
                    break;
                case 7:
                    System.out.println("Please enter the student ID : ");
                    int s_id = input.nextInt();
                    r.listCoursesByStudent(s_id);
                    break;
                case 8:
                    System.out.println("Please enter the course ID : ");
                    int c_id = input.nextInt();
                    r.listStudentByCourse(c_id);
                    break;

                case 9:
                    System.out.println("The last student added has the ID : " + s.getLastStudentAdded());
                    break;
                case 10:
                    System.out.println("The last course added has the ID : " + c.getLastCourseAdded());
                    break;
                case 11:
                    System.out.println("Please enter the student ID : ");
                    int sid = input.nextInt();
                    System.out.println(r.isNormalStudent(sid));
                    break;
                case 12:
                    System.out.println("Please enter the course ID : ");
                    int cid = input.nextInt();
                    System.out.println(r.isFullCourse(cid));
                    break;
                case 13:
                    System.out.println("Please enter the course ID : ");
                    int CID = input.nextInt();
                    r.sortStudentByID(CID);
                    break;
                case 14:
                    System.out.println("Please enter the student ID : ");
                    int SID = input.nextInt();
                    r.sortCourseByID(SID);
                    break;
                case 15:
                    record.undo(r);
                    break;
                case 16:
                    record.redo(r);
                    break;
                case 17:
                    flag = false;
                    break;
                default:
                    System.out.println("INVALID input !\nplease try again");
                    break;
            }
        }
    }

}
