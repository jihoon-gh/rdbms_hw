package gov.example.dbms;

import gov.example.dbms.controller.StudentController;
import gov.example.dbms.domain.Student;
import gov.example.dbms.repository.StudentRepository;
import gov.example.dbms.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudentControllerTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentController studentController;

    @Test
    public void 동명이인_에러_메세지_체크(){
        //given
        String name = "Snow White";
        String email = "swhite@hongik.ac.kr";
        Student student = new Student(name,email,2030,"Master");
        studentRepository.save(student);

        //when
        String res1 = studentController.getDegreeOfStudentWithSpecificName("Snow White");
        String res2 = studentController.getEmailOfStudentWithSpecificName("Snow White");

        //then
        assertThat(res1).isEqualTo("There are multiple students with the same name. Please provide an email address instead.");
        assertThat(res2).isEqualTo("There are multiple students with the same name. Please contact the administrator by phone.");
    }

    @Test
    public void 특정_이름의_학생이_존재하지_않는_경우_테스트() {
        //given
        String name = "Baek Jihoon";

        //when
        String res1 = studentController.getEmailOfStudentWithSpecificName(name);
        String res2 = studentController.getDegreeOfStudentWithSpecificName(name);

        //then
        String test = "No such student";
        assertThat(res1).isEqualTo(test);
        assertThat(res2).isEqualTo(test);
    }

    @Test
    public void 학생이_추가됐을_때_Count_변화_테스트(){
        //given
        Integer beforeAdd1 = studentService.getCountByDegree("PhD");
        Integer beforeAdd2 = studentService.getCountByDegree("Master");
        Integer beforeAdd3 = studentService.getCountByDegree("Undergrad");

        //when
        Student student1 = new Student("test1","test1@gmail.com",2023,"PhD");
        Student student2 = new Student("test2","test1@gmail.com",2023,"Master");
        Student student3 = new Student("test3","test1@gmail.com",2023,"Undergrad");

        studentService.saveNewStudent(student1);
        studentService.saveNewStudent(student2);
        studentService.saveNewStudent(student3);

        //then
        Integer AfterAdd1 = studentService.getCountByDegree("PhD");
        Integer AfterAdd2 = studentService.getCountByDegree("Master");
        Integer AfterAdd3 = studentService.getCountByDegree("Undergrad");

        assertThat(AfterAdd1).isEqualTo(beforeAdd1+1);
        assertThat(AfterAdd2).isEqualTo(beforeAdd2+1);
        assertThat(AfterAdd3).isEqualTo(beforeAdd3+1);

    }

    @Test
    public void 새_학생_등록_관련_테스트(){
        //given
        String name = "Snow White";
        String email = "swhite@hongik.ac.kr";
        String rname = "Baek Jihoon";
        String remail = "jihoon806@gmail.com";
        //when
        String res = studentController.createNewStudent(name, email, "2023", "phd");
        String success = studentController.createNewStudent(rname,remail,"2024","undergrad");
        //then
        assertThat(res).isEqualTo("Already registered");
        assertThat(success).isEqualTo("Registration successful");
    }
}
