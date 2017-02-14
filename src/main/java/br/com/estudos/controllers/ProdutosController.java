package br.com.estudos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.estudos.daos.ProdutoDao;
import br.com.estudos.models.Produto;
import br.com.estudos.models.TipoPreco;

@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		System.out.println("AQUI /produtos/form");

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}

	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String grava(Produto produto) {
		System.out.println("AQUI /produtos");
	
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos",produtos);
		
		return modelAndView;
	}
	
	
}
