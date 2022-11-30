package gov.example.dbms;

import gov.example.dbms.service.StudentService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class afterInitBeans implements CommandLineRunner {

    private final String url = "https://apl.hongik.ac.kr/lecture/dbms";

    private final Connection conn = Jsoup.connect(url);

    private final StudentService studentService;

    public afterInitBeans(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Document document = conn.get();
        Elements elements = document.getElementsByClass("n8H08c UVNKR");
        for (int i = 0; i < elements.size(); i++) {
            Elements temp = elements.get(i).getElementsByTag("li");
            for (int j = 0; j < temp.size(); j++) {
                List<String> info = Arrays.stream(temp.get(j)
                                .text()
                                .split(", "))
                        .toList();
                studentService.saveNewStudentWithInfos(info,getDegreeByNum(i));
            }
        }
    }
    public static String getDegreeByNum(Integer num){
        if(num.equals(0)){
            return "PhD";
        }
        if(num.equals(1)){
            return "Master";
        }
        return "Undergrad";
    }
}
