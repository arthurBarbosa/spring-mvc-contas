package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {

		if (result.hasErrors()) {
			return "formulario";
		}

		System.out.println("salvando " + conta.getDescricao());

		ContaDAO contaDAO = new ContaDAO();

		contaDAO.adiciona(conta);

		return "conta/conta-adicionada";
	}

	@RequestMapping("/pagaConta")
	public void paga(Conta conta, HttpServletResponse reponse) {
		ContaDAO dao = new ContaDAO();
		dao.paga(conta.getId());
		reponse.setStatus(200);
	}

	@RequestMapping("removeConta")
	public String remove(Conta conta) {

		ContaDAO dao = new ContaDAO();

		dao.remove(conta);

		return "redirect:listacontas";
	}

	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model) {

		ContaDAO dao = new ContaDAO();

		model.addAttribute("conta", dao.buscaPorId(id));

		return "conta/mostra";
	}

	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {

		ContaDAO dao = new ContaDAO();

		dao.altera(conta);

		return "redirect:listacontas";
	}

	@RequestMapping("/listacontas ")
	public ModelAndView lista() {

		ContaDAO dao = new ContaDAO();

		List<Conta> contas = dao.lista();

		ModelAndView mv = new ModelAndView("conta/lista");

		mv.addObject("todasContas", contas);

		return mv;
	}
}
