package org.kebbe.Dao;

import org.kebbe.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import antlr.collections.List;

public interface StudentRepository extends JpaRepository<Student, Long>{
	public java.util.List<Student> findByName(String n);
	@Query("select s from Student s where s.name like :x")
	public Page<Student> seachByKeyword(@Param("x")String keyword,Pageable p);
	
}
