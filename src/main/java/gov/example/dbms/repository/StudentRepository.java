package gov.example.dbms.repository;

import gov.example.dbms.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from students s where s.name = :name",nativeQuery = true)
    List<Student> findStudentByName(String name);

    @Query(value = "select * from students s where s.degree = :degree",nativeQuery = true)
    List<Student> findStudentsByDegree(String degree);

    @Query(value="select * from students s where s.email = :email",nativeQuery = true)
    List<Student> findStudentsByEmail(String email);

    @Query(value = "select count(s.sid) from students s where s.degree = :degree",nativeQuery = true)
    Long getCountOfDegree(String degree);
}
