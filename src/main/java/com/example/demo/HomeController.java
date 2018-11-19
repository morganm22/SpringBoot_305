package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String index(Model   model){
        //First let's create a student
        Student student = new Student();
        student.setfName("Morgan");
        student.setlName("Miller");

        //Now let's create a course
        Course course = new Course();
        course.setTitle("Intro to Java");
        course.setCredits(3L);
        course.setDescription("Hands on coding experience...");

        // Add the course to an empty list
        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        // Add the list of courses to the student's course list
        student.setCourses(courses);

        //Save the student to the database
        studentRepository.save(student);

        //Grad all the students from the database and send them to the template
        model.addAttribute("students", studentRepository.findAll());
        return "index";

    }
}