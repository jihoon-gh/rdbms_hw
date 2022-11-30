package gov.example.dbms.service;

import gov.example.dbms.domain.Student;
import gov.example.dbms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getStudentsByName(String name) {
        List<Student> studentList = studentRepository.findStudentByName(name);
        return studentList;
    }

    @Override
    public List<Student> getStudentByDegree(String degree) {
        List<Student> studentList = studentRepository.findStudentsByDegree(degree);
        return studentList;
    }
    @Override
    public List<Student> getStudentsByEmail(String email) {
        List<Student> studentList = studentRepository.findStudentsByEmail(email);
        return studentList;
    }

    @Override
    @Transactional
    public void saveNewStudent(Student student) {
        studentRepository.save(student);

    }
    @Override
    @Transactional
    public void saveNewStudentWithInfos(List<String> infos, String degree){
        Student student = new Student(infos,degree);
        studentRepository.save(student);
    }
    @Override
    public Integer getCountByDegree(String degree) {
        return studentRepository.getCountOfDegree(degree).intValue();
    }

    public static void validateStudentsList(List<Student> studentList){
        if(studentList.size()>1){
            throw new IllegalStateException("There are multiple students with the same name. " +
                    "Please provide an email address instead");
        }
        if(studentList.isEmpty()){
            throw new IllegalArgumentException("No such student");
        }
    }

}

