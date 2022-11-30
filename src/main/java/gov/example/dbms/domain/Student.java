package gov.example.dbms.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Integer sid;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    @Column(columnDefinition = "varchar(100)")
    private String email;

    private Integer graduation;
    @Column(columnDefinition = "varchar(100)")
    private String degree;

    public Student(){}
    public Student(String name, String email, Integer graduation, String degree){
        this.name=name;
        this.email=email;
        this.graduation=graduation;
        this.degree=degree;
    }

    public Student(List<String> infos, String degree){
        this.name = infos.get(0);
        this.email = infos.get(1);
        this.graduation = Integer.parseInt(infos.get(2));
        this.degree = degree;
    }
    public String getName(){
        return name;
    }
    public String getDegree(){
        return degree;
    }

    public String getEmail(){
        return email;
    }

    public void setSid(Integer sid){
        this.sid=sid;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGraduation(Integer graduation) {
        this.graduation = graduation;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setEmail(String email){
        this.email=email;
    }
}
