package br.com.estudos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.estudos.daos.ProdutoDao;
import br.com.estudos.models.CarrinhoCompras;
import br.com.estudos.models.CarrinhoItem;
import br.com.estudos.models.Produto;
import br.com.estudos.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		
		carrinho.add(carrinhoItem);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
		
		return new ModelAndView("carrinho/itens");
	}
	
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		
		Produto produto = produtoDao.find(produtoId);
		
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		
		return carrinhoItem;
	}
	
	@RequestMapping(value="/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		
		carrinho.remover(produtoId, tipoPreco);
		
		
		return new ModelAndView("redirect:/carrinho");
	}
	
	
}
