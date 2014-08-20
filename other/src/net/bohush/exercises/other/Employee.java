package net.bohush.exercises.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Employee implements Comparable<Employee>{

    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name;        
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return id + ".\t" + name + ":\t$" + String.format("%.2f", getAverageMonthlySalary());
    }
    
    public String getData() {
        return id + "\t" + name;
    }
    
    @Override
    public int compareTo(Employee o) {
        double thisSalary = this.getAverageMonthlySalary();
        double thatSalary = o.getAverageMonthlySalary();
        if(thisSalary > thatSalary) {
            return 1;
        } else if(thisSalary < thatSalary) {
            return -1;
        } else {
            return this.name.compareTo(o.name);
        }
    }
    
    public abstract double getAverageMonthlySalary();
    
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new FixedPaymentEmployee("Vasyl Pupkin", 123, 1500));
        employees.add(new FixedPaymentEmployee("Bohdan Shevchenko", 3234, 2000));
        employees.add(new FixedPaymentEmployee("Andriy Kuzmenko", 234, 1700));
        employees.add(new FixedPaymentEmployee("Roman Petrenko", 456, 1200));
        employees.add(new FixedPaymentEmployee("Yaroslav Franko", 173, 1900));
        employees.add(new HourlyWageEmployee("Kiril Koval", 96, 7.5));
        employees.add(new HourlyWageEmployee("Stepan Ivanov", 235, 6.4));
        employees.add(new HourlyWageEmployee("Viktor Bohush", 854, 12.5));
        employees.add(new HourlyWageEmployee("Ostap Bondarenko", 386, 9.3));
        employees.add(new HourlyWageEmployee("Vitaliy Stus", 348, 10.0));
        
        Collections.sort(employees);
        System.out.println("All employees:");    
        for (Employee employee : employees) {
            System.out.println(employee);    
        }
        
        System.out.println("\nFirst five employees:");  
        for (int i = 0; i < 5; i++) {
            System.out.println(employees.get(i));   
        }
        
        System.out.println("\nLast three employees:");  
        for (int i = employees.size() - 3; i < employees.size(); i++) {
            System.out.println(employees.get(i).getId());   
        }
           
        File file = new File("employees.txt");
        try (PrintWriter output = new PrintWriter(file)) {
            for (Employee employee : employees) {
                if(employee instanceof HourlyWageEmployee) {
                    output.println(1 + "\t" + employee.getData());    
                } else {
                    output.println(2 + "\t" + employee.getData());    
                }   
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\nEmployees are written to the file");  
        
        System.out.println("\nEmployees are read from the file:");  
        ArrayList<Employee> employees2;
        try (Scanner input = new Scanner(file)) {
            employees2 = new ArrayList<>();
            while(input.hasNextLine()) {
                String[] newEmployee = input.nextLine().split("\t");
                if(newEmployee.length == 4) {
                    if(newEmployee[0].equals("1")) {
                        employees2.add(new HourlyWageEmployee(newEmployee[2], Integer.parseInt(newEmployee[1]), Double.parseDouble(newEmployee[3])));
                    } else if(newEmployee[0].equals("2")) {
                        employees2.add(new FixedPaymentEmployee(newEmployee[2], Integer.parseInt(newEmployee[1]), Double.parseDouble(newEmployee[3])));
                    }
                }
            }
            for (Employee employee : employees2) {
                System.out.println(employee);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
