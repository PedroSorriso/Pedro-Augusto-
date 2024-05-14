import java.util.ArrayList;
import java.util.List;

class UniversitySystem {
    private static UniversitySystem instance;

    private UniversitySystem() {}

    public static UniversitySystem getInstance() {
        if (instance == null) {
            instance = new UniversitySystem();
        }
        return instance;
    }

    public void displayMessage() {
        System.out.println("University System is running.");
    }
}

abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract void displayInfo();
}

class Student extends Person {
    public Student(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name);
    }
}

class Professor extends Person {
    public Professor(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Professor: " + name);
    }
}

class Staff extends Person {
    public Staff(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Staff: " + name);
    }
}

abstract class PersonFactory {
    public abstract Person createPerson(String name);
}

class StudentFactory extends PersonFactory {
    @Override
    public Person createPerson(String name) {
        return new Student(name);
    }
}

class ProfessorFactory extends PersonFactory {
    @Override
    public Person createPerson(String name) {
        return new Professor(name);
    }
}

class StaffFactory extends PersonFactory {
    @Override
    public Person createPerson(String name) {
        return new Staff(name);
    }
}

interface PaymentSystem {
    void processPayment(double amount);
}

class ExternalPayment {
    public void makePayment(double amount) {
        System.out.println("Payment of " + amount + " made using external system.");
    }
}

class PaymentAdapter implements PaymentSystem {
    private ExternalPayment externalPayment;

    public PaymentAdapter(ExternalPayment externalPayment) {
        this.externalPayment = externalPayment;
    }

    @Override
    public void processPayment(double amount) {
        externalPayment.makePayment(amount);
    }
}

abstract class UniversityMember {
    protected String name;

    public UniversityMember(String name) {
        this.name = name;
    }

    public abstract void displayDetails();
}

class CompositeProfessor extends UniversityMember {
    public CompositeProfessor(String name) {
        super(name);
    }

    @Override
    public void displayDetails() {
        System.out.println("Composite Professor: " + name);
    }
}

class Department extends UniversityMember {
    private List<UniversityMember> members = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    public void addMember(UniversityMember member) {
        members.add(member);
    }

    public void removeMember(UniversityMember member) {
        members.remove(member);
    }

    @Override
    public void displayDetails() {
        System.out.println("Department: " + name);
        for (UniversityMember member : members) {
            member.displayDetails();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        UniversitySystem system = UniversitySystem.getInstance();
        system.displayMessage();

        PersonFactory studentFactory = new StudentFactory();
        Person student = studentFactory.createPerson("John");
        student.displayInfo();

        PersonFactory professorFactory = new ProfessorFactory();
        Person professor = professorFactory.createPerson("Dr. Smith");
        professor.displayInfo();

        PersonFactory staffFactory = new StaffFactory();
        Person staff = staffFactory.createPerson("Mary");
        staff.displayInfo();

        ExternalPayment externalPayment = new ExternalPayment();
        PaymentSystem paymentAdapter = new PaymentAdapter(externalPayment);
        paymentAdapter.processPayment(100);

        CompositeProfessor professor1 = new CompositeProfessor("Dr. Smith");
        CompositeProfessor professor2 = new CompositeProfessor("Dr. Johnson");
        Department department = new Department("Computer Science Department");
        department.addMember(professor1);
        department.addMember(professor2);
        department.displayDetails();
    }
}
