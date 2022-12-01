package gov.example.dbms.controller;

import gov.example.dbms.domain.Student;
import gov.example.dbms.service.StudentService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping("/degree")
    public String getDegreeOfStudentWithSpecificName(@RequestParam("name") String name){
        List<Student> studentsByName = studentService.getStudentsByName(name);
        if(studentsByName.size()==0){
            return "No such student";
        }
        if(studentsByName.size()>1){
            return "There are multiple students with the same name. Please provide an email address instead.";
        }
        Student student = studentsByName.get(0);
        return student.getName()+ " : "+student.getDegree();
    }

    @GetMapping("/email")
    public String getEmailOfStudentWithSpecificName(@RequestParam("name") String name){
        List<Student> studentsByName = studentService.getStudentsByName(name);
        if(studentsByName.size()==0){
            return "No such student";
        }
        if(studentsByName.size()>1){
            return "There are multiple students with the same name. Please contact the administrator by phone.";
        }
        Student student = studentsByName.get(0);
        return student.getName()+ " : "+student.getEmail();
    }

    @GetMapping("/stat")
    public String getCountsOfDegrees(@RequestParam("degree") String degree){
        degree=getUpperDegree(degree);
        Integer count = studentService.getCountByDegree(degree);
        String res = "Number of "+degree+"'s student : "+count.toString();
        return res;
    }

    @PutMapping("/register")
    public String createNewStudent(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("graduation") String graduation,
                                   @RequestParam("degree") String degree){
        List<Student> studentsByName = studentService.getStudentsByName(name);
        if(studentsByName.size()>0){
            return "Already registered";
        }
        List<Student> studentsByEmail = studentService.getStudentsByEmail(email);
        if(studentsByEmail.size()>0){
            return "Already registered";
        }
        Student newStudent = new Student(name, email, Integer.parseInt(graduation), getUpperDegree(degree));
        studentService.saveNewStudent(newStudent);
        return "Registration successful";
    }

    public static String getUpperDegree(String degree){
        if(degree.equals("phd")){
            return "PhD";
        }
        if(degree.equals("master")){
            return "Master";
        }
        return "Undergrad";
    }

}
