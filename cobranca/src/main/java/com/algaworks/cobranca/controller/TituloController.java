package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired
	private TituloRepository tituloRepository;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		tituloRepository.save(titulo);

		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = tituloRepository.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;

	}
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		tituloRepository.deleteById(codigo);;
		attributes.addFlashAttribute("mensagem", "Titulo excluido com sucesso!");
		return "redirect:/titulos";
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
