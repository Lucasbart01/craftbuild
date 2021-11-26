package br.com.craftbuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import br.com.craftbuild.beans.Usuario;
import br.com.craftbuild.dao.UsuarioDAO;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	//Chamando página de cadastro
		@GetMapping("/cadastro")
		public String cadastro() {
		
			return "index";
		}
		
	//Chamando página de login
	@GetMapping("/login")
	public String index() {
	
		return "login";
	}
	

	@PostMapping("/login/entry")
	public ResponseEntity<Usuario> getAllByEmailAndSenha(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Senha: " + usuario.getSenha());
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

	
	//Select
	 @GetMapping("/user")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("users", dao.findAll());
	        return "area";
	    }
	 
	 //Create
	 @PostMapping("/add")
	 public String Cadastro(@ModelAttribute("user") Usuario user) { 
		 user.save(user);
		 return "redirect:/index";
	 }
	 
	
	
	
//	 @GetMapping("/user/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//		 
//        model.addAttribute("users", dao.findById(id));
//        return "area";
//    }
//


	 //Delete
	 @GetMapping("delete/{id}")
	    public String deleteStudent(@PathVariable("id") int id, Model model) {
	        Usuario usuario = dao.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido:" + id));
	        dao.delete(usuario);
	        model.addAttribute("users", dao.findAll());
	        return "area";
	    }
	 
	
//////////////////////////////////
////////COMANDOS PARA TESTE///////
	@GetMapping("/usuarios/{userId}")
	public ResponseEntity<Usuario> getAllById(@PathVariable("userId") int id) {
		Usuario listId = dao.findById(id).orElse(null);

		if (listId == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(listId);
	}
	

	@PostMapping("/loginPostman")
	public ResponseEntity<Usuario> getAllByEmailAndSenha1(@RequestBody Usuario usuario) {
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

		if (resposta == null) {
			return ResponseEntity.status(404).build();
		}
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

}
