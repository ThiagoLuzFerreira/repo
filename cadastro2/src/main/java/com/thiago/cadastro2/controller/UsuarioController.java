package com.thiago.cadastro2.controller;

import com.thiago.cadastro2.entity.Dependente;
import com.thiago.cadastro2.entity.Usuario;
import com.thiago.cadastro2.service.DependenteService;
import com.thiago.cadastro2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private DependenteService dpService;

    @RequestMapping(value = "/adicionar", method = RequestMethod.GET)
    public String linkForm(@ModelAttribute("usuario") Usuario usuario){
        return "/user/add";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar (@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return "/user/add";
        }
        service.save(usuario);
        attr.addFlashAttribute("message", "Usuário salvo com sucesso");
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ModelAndView listar(ModelMap model){
        model.addAttribute("usuarios", service.findAll());
        return new ModelAndView("user/list", model);
    }

    @RequestMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        service.delete(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso");
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("usuario", service.findById(id));
        return new ModelAndView("/user/add", model);
    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String upDate(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return "/user/add";
        }
        service.upDate(usuario);
        attr.addFlashAttribute("message", "Usuário atualizado com sucesso");
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value ="/buscanome", method = RequestMethod.GET)
    public ModelAndView buscaNome(@RequestParam("buscaNome") String buscaNome){
        if(buscaNome == null){
            return new ModelAndView("/user/list");
        }
        return new ModelAndView("/user/list", "usuarios", service.findByNomeOuSobrenome(buscaNome));
    }

    @RequestMapping(value ="/buscaemail", method = RequestMethod.GET)
    public ModelAndView buscaEmail(@RequestParam("buscaEmail") String buscaEmail){
        if(buscaEmail == null){
            return new ModelAndView("/user/list");
        }
        return new ModelAndView("/user/list", "usuarios", service.findByEmail(buscaEmail));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesUsuario(@PathVariable("id") Long id, Dependente dependente, ModelMap model){
        model.addAttribute("usuario", service.findById(id));
        model.addAttribute("dependentes", dpService.findByUsuario(id));
        //ModelAndView mv = new ModelAndView("/user/userDetail", model);
        //List<Dependente> dependentes = dpService.findByUsuario(id);
        //mv.addObject("dependentes", dependentes);
        return new ModelAndView("/user/userDetail", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String detalhesUsuarioPost(@PathVariable("id") Long id, @Valid Dependente dependente, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            attr.addFlashAttribute("message", "Verifique os campos.");
            return "redirect:/usuarios/{id}";
        }
        Usuario usuario = service.findById(id);
        dependente.setUsuario(usuario);
        dpService.save(dependente);
        attr.addFlashAttribute("message", "Salvo com sucesso.");
        return "redirect:/usuarios/{id}";
    }

    @RequestMapping("/excluirDependente/{id}")
    public String excluirDependente(@PathVariable("id") Long id){
        Dependente dependente = dpService.findById(id);
        dpService.delete(id);
        Usuario usuario = dependente.getUsuario();
        Long idLong = usuario.getId();
        String usuarioId = "" + idLong;

        return "redirect:/usuarios/" + usuarioId;
    }
}
