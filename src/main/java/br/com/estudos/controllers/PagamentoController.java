package br.com.estudos.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.estudos.models.CarrinhoCompras;
import br.com.estudos.models.DadosPagamentos;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	final protected String uri = "http://book-payment.herokuapp.com/payment";

	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {

		return() -> {
			String reponse = null;
			
			try {
				reponse = restTemplate.postForObject(uri, new DadosPagamentos(carrinho.getTotal()), String.class);
				model.addFlashAttribute("sucesso", reponse);
				return new ModelAndView("redirect:/produtos");
			}
			catch (Exception e) {
				e.getMessage();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

}
