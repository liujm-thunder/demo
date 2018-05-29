package com.appchina.collect.design.daili;

public class StudentsProxy implements Person{

    private Student student;

    public StudentsProxy(Student student) {
        if (student.getClass()==Student.class){
            this.student = student;
        }
    }

    @Override
    public void giveMoney() {
        student.giveMoney();
    }
}
