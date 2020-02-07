package org.kebbe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kebbe.Dao.StudentRepository;
import org.kebbe.Entities.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException {
		ApplicationContext appcontext=SpringApplication.run(Application.class, args);
		StudentRepository sr=appcontext.getBean(StudentRepository.class);
		
	
	/*	String[] names={"Coco","Adam","Mathieu","Baoluo","zhang","Ruida","Maoloud",
				"Fadel","Lina","Lilian","Kader","Aboubacar","Manar","Hafith","Lary",
				"Samuel","Moussa","Sadia","Bahdon"}; 
		for(int i=0;i<names.length;i++){
			//System.out.println(names[i]);
			DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			sr.save(new Student(names[i],dateFormat.parse("1990-11-10"),names[i]+"@tju.edu.com",names[i]+".jpg" ));
		}*/
	}
}
