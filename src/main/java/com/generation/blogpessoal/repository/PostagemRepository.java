package com.generation.blogpessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository <PostagemModel, Long> {

	//Como se fizesse select*from tb_postagem WHERE título LIKE "%" no mysql;
	public List<PostagemModel> findAllByTítuloContainingIgnoreCase (String título);
	
	//Como se fizesse select*from tb_postagem WHERE texto LIKE "%" no mysql;
	public List<PostagemModel> findAllByTextoContainingIgnoreCase (String texto);
	

	
}
