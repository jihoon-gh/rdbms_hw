package gov.example.dbms;

import gov.example.dbms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService studentService;
}
