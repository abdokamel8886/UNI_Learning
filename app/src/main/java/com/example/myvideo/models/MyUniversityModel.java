package com.example.myvideo.models;

public class MyUniversityModel {


    private Integer Grade;
    private Integer department;

    public MyUniversityModel() {
    }

    public MyUniversityModel(Integer Grade, Integer department, Integer term) {
        this.Grade = Grade;
        this.department = department;
        this.term = term;
    }

    private Integer term;



    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        this.Grade = grade;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
