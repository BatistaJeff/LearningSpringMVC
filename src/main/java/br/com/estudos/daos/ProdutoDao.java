package br.com.estudos.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudos.models.Produto;

@Repository
@Transactional
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		
		manager.persist(produto);
	}

	public List<Produto> listar() {

		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {

		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos join p.precos "
				+ "where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}



}
