package com.projeto.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.ads.model.Professor;
import com.projeto.ads.repository.ProfessorRepository;
import com.projeto.ads.service.ServiceProfessor;

@Controller
public class ProfessorController {

	@Autowired
	ServiceProfessor serviceProfessor;
	@Autowired
	ProfessorRepository professorRepository;
	
	@GetMapping("/professor/inserir")
	public ModelAndView inserirProfGet() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professor", new Professor());
		mv.setViewName("Professor/inserirProfessor");
		return mv;
	}//fim metodo
	
	@PostMapping("/professor/inserir")
	public ModelAndView inserirProfPost(Professor professor) {
		
		ModelAndView mv = new ModelAndView();
		String saida= serviceProfessor.cadastrarProfessor(professor);
		if(saida!=null) {
			mv.addObject("professor", professor);
			mv.addObject("msg", saida);
			mv.setViewName("Professor/inserirProfessor");
			return mv;
		}
		else {//aplicação salvou professor no banco
			mv.setViewName("redirect:/professor/listar");
			return mv;
		}
	}//fim metodo
	
	@GetMapping("/professor/listar")
	public ModelAndView GetListarProfessors() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professorRepository.findAllOrderedById());
		mv.setViewName("Professor/listarProfessores");
		return mv;
	}//fim metodo
	
	@GetMapping("/professor/editar")
	public ModelAndView getListAlterarProfessor() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professorRepository.findAllOrderedById());
		mv.setViewName("Professor/listarEditarProfessor");
		return mv;
	}//fim metodo
	
	@GetMapping("/professor/alterar/{id}")
	public ModelAndView getAlterarProfessors(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Professor prof= professorRepository.findById(id).get();
		mv.addObject("professor", prof);
		mv.setViewName("Professor/alterarProfessor");
		return mv;
	}//fim metodo
	
	@PostMapping("/professor/alterar")
	public ModelAndView getAlterarProfessors(Professor professor) {
		ModelAndView mv = new ModelAndView();
		String msg= serviceProfessor.atualizarProfessor(professor);
		if(msg != null) {
			mv.addObject("msg", msg);
			mv.addObject("professor", professor);
			mv.setViewName("Professor/alterarProfessor");
		}else {// conseguimos atualizar o professor
			mv.setViewName("redirect:/professor/editar");
		}//fim else
		
		return mv;
	}//fim metodo
	
	@GetMapping("/professor/deletar")
	public ModelAndView getListDeletarProfessor() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professorRepository.findAllOrderedById());
		mv.setViewName("Professor/listarDeletarProfessor");
		return mv;
	}//fim metodo
	
	@GetMapping("/professor/excluir/{id}")
	public String excluirProfessor(@PathVariable("id") Long id) {
		professorRepository.deleteById(id);
		return "redirect:/professor/deletar";
	}//fim metodo
	
}//fim class
