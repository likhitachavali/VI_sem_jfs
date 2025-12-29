package com.skillnext1;

import javax.persistence.*;

@Entity
@Table(name = "student")
   
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int sem;
    private String dept;

    public Student() {}

    public Student(String name, int sem, String dept) {
        this.name = name;
        this.sem = sem;
        this.dept = dept;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSem() { return sem; }
    public void setSem(int sem) { this.sem = sem; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
}
