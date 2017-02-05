package br.com.estudos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estudos.daos.ProdutoDao;
import br.com.estudos.models.Produto;

@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/produtos/form")
	public String form() {
		System.out.println("AQUI /produtos/form");
		
		return "produtos/form";
	}

	@RequestMapping("/produtos")
	public String grava(Produto produto) {
		System.out.println("AQUI /produtos");
	
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
}
