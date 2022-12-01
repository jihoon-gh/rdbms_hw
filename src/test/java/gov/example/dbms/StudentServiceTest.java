package gov.example.dbms;

import gov.example.dbms.domain.Student;
import gov.example.dbms.repository.StudentRepository;
import gov.example.dbms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    /*
    현재 service 계층에서 핵심 비즈니스 로직,Exception 처리 등을 하지 않고
    오직 repository 객체에 접근하고, Transaction 권한과 관련된 부분만을 담당하는 상태라,
    service 계층과 관련하여 어떠한 테스트를 진행하지는 않았습니다.
    비워 둔 상태입니다.
    */

}
