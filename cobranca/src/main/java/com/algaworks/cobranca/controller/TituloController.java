package com.algaworks.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.algaworks.cobranca.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		// Lógica para salvar
		System.out.println("Descricão do titulo: " + titulo.getCodigo());
		System.out.println("Descricão do titulo: " + titulo.getDescricao());
		
		System.out.println("Descricão do titulo: " + titulo);
		
		return "CadastroTitulo";
	}

}
