package org.kebbe.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.IOUtils;

//import java.util.List;

import org.kebbe.Dao.StudentRepository;
import org.kebbe.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping(value="/Students")
public class StudentControler {
	@Autowired
	StudentRepository studentRepository;
	@Value("${stock.directory")
	private String stockdir;
	
	@RequestMapping(value="/Index")
	private String Index(Model model,
			@RequestParam(name="page",defaultValue="0")int pageNumber,
			@RequestParam(name="name",defaultValue="")String searchedName) {
		
	/*	All students
	 * List<Student> students=studentRepository.findAll();
		model.addAttribute("students",students);*/
		
	/*	students by page
		Page<Student> studentListByPage=studentRepository.findAll(new PageRequest(pageNumber, 10));
		model.addAttribute("students", studentListByPage);
		*/
		
	/*	search by name
	 *  List<Student> studentListBySearchedName=studentRepository.findByName(searchedName);
			model.addAttribute("students",studentListBySearchedName);
		}else{
		
		}*/
		//search by keyword
		Page<Student> studentListBySearchedName=studentRepository.seachByKeyword("%"+searchedName+"%",
				new PageRequest(pageNumber, 5));
		
		
		model.addAttribute("students",studentListBySearchedName);
		int numberOfPages=studentListBySearchedName.getTotalPages();
		if(numberOfPages==0) numberOfPages=1;
		model.addAttribute("totalPages",numberOfPages);
		model.addAttribute("currentPage", pageNumber+1);
		model.addAttribute("name",searchedName);
		
		//model.addAttribute("photoPath",getPhotoPath());  
		
		return "Students";

	}
	
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	private byte[] getPhoto(Long id) throws FileNotFoundException, IOException {
		File file=new File(System.getProperty("user.home")+"/scho/"+id);
		//File file = new File(stockdir+id);  
		if(!file.exists()){
			File defaultFile=new File(System.getProperty("user.home")+"/scho/default.jpg");
			return IOUtils.toByteArray(new FileInputStream(defaultFile));
		}
		return IOUtils.toByteArray(new FileInputStream(file));
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String studentForm(Model model) {
		model.addAttribute("student",new Student());
		return "StudentForm";
	}
	@RequestMapping(value="/Save", method=RequestMethod.POST)
	public String saveStudent(Student student,@RequestParam(name="picture")MultipartFile file)
			throws IllegalStateException, IOException{
	
		studentRepository.save(student);
		
		if(!file.isEmpty()){	
			student.setPhoto(file.getOriginalFilename());
			//file.transferTo(new File("C:/Users/COUTA/scho/"+student.getId())); //not recommended
			file.transferTo(new File(System.getProperty("user.home")+"/scho/"+student.getId()));//it's better to use the application.properties file
			//file.transferTo(new File(stockdir+student.getId()));
			
			studentRepository.save(student);
		}
			
		
		return "redirect:Index";
	}
	@RequestMapping(value="/Delete")
	private String DeleteStudent(Long id) {
		studentRepository.delete(id);
		return  "redirect:Index";
	}
	
	@RequestMapping(value="/EditForm",method=RequestMethod.GET)
	public String editForm(Model model, Long id	) {
		Student s=studentRepository.findOne(id);
		System.out.println("a name" +s.getPhoto());
		model.addAttribute("student",s);
		return "EditForm";
	}
	@RequestMapping(value="/Edit", method=RequestMethod.POST)
	public String editStudent(Student student,@RequestParam(name="picture")MultipartFile file)
			throws IllegalStateException, IOException{
	//	student.setPhoto(file.getOriginalFilename());
		studentRepository.save(student);
		
		if(!file.isEmpty()){			
			//file.transferTo(new File("C:/Users/COUTA/scho/"+student.getId())); //not recommended
			file.transferTo(new File(System.getProperty("user.home")+"/scho/"+student.getId()));//it's better to use the application.properties file
			//file.transferTo(new File(stockdir+student.getId()));
			
		}		
		return "redirect:Index";
	}
	
}
