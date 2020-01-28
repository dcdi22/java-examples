package com.company.rsvpedge;

public class Example {

    public abstract class Employee {
        abstract void setSalary(int salary);

        abstract int getSalary();

        abstract void setGrade(String grade);

        abstract String getGrade();

        // Concrete method hidden from end user means encapsulation, add private modifier
        private void label() {
            System.out.println("Employee's data:\n");
        }
    }

    public class Engineer extends Employee {
        private int salary;

        private String grade;


        @Override
        void setSalary(int salary) {

        }

        @Override
        int getSalary() {
            return 0;
        }

        @Override
        void setGrade(String grade) {

        }

        @Override
        String getGrade() {
            return null;
        }
    }

    public class Manager extends Employee {
        private int salary;

        private String grade;


        @Override
        void setSalary(int salary) {

        }

        @Override
        int getSalary() {
            return 0;
        }

        @Override
        void setGrade(String grade) {

        }

        @Override
        String getGrade() {
            return null;
        }
    }

}
