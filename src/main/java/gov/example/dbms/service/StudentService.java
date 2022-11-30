package gov.example.dbms.service;

import gov.example.dbms.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    List<Student> getStudentsByName(String name);

    List<Student> getStudentByDegree(String degree);

    List<Student> getStudentsByEmail(String email);

    void saveNewStudent(Student student);

    void saveNewStudentWithInfos(List<String> info, String degree);

    Integer getCountByDegree(String degree);
}
