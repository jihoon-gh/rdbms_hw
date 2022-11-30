package gov.example.dbms;

import gov.example.dbms.domain.Student;
import gov.example.dbms.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    public void 이름을_통한_학생_조회_테스트(){
        //given
        Student student = new Student("test","test",1,"test");
        studentRepository.save(student); //테이블 변경 발생 -> tx필요
        em.flush();

        //when
        List<Student> students = studentRepository.findStudentByName("test");

        //then
        assertThat(students.size()).isEqualTo(1);
    }

    @Test
    public void 학위를_통한_학생_조회_테스트(){

        //given
        Student student = new Student("test","test",1,"test");
        studentRepository.save(student);//테이블 변경 발생 -> tx필요
        em.flush();

        //when
        List<Student> test = studentRepository.findStudentsByDegree("test");

        //then
        assertThat(test.size()).isEqualTo(1);
    }



}
