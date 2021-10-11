package com.tpa.videogames.controllers;

import com.tpa.videogames.entities.Categoria;
import com.tpa.videogames.entities.Estudio;
import com.tpa.videogames.entities.Videojuego;
import com.tpa.videogames.services.ServicioCategoria;
import com.tpa.videogames.services.ServicioEstudio;
import com.tpa.videogames.services.ServicioVideojuego;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

@Controller
public class ControladorVideojuego {
    @Autowired
    private ServicioVideojuego svcVideojuego;
    @Autowired
    private ServicioCategoria svcCategoria;
    @Autowired
    private ServicioEstudio svcEstudio;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAllByActivo();
            model.addAttribute("videojuegos", videojuegos);

            return "views/inicio";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") long id) {
        try {
            Videojuego videojuego = this.svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego",videojuego);
            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/busqueda")
    public String busquedaVideojuego(Model model, @RequestParam(value ="query",required = false)String q){
        try {
            List<Categoria> categorias = this.svcCategoria.findByName(q);
            List<Estudio> estudios = this.svcEstudio.findByName(q);
            List<Videojuego> videojuegos = this.svcVideojuego.findByTitle(q);

            model.addAttribute("videojuegos", videojuegos);
            model.addAttribute("resultado",q);
            return "views/busqueda";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/crud")
    public String crudVideojuego(Model model){
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAll();
            model.addAttribute("videojuegos",videojuegos);

            List<Categoria> categorias = this.svcCategoria.findAll();
            model.addAttribute("categorias",categorias);

            List<Estudio> estudios = this.svcEstudio.findAll();
            model.addAttribute("estudios",estudios);

            model.addAttribute("categoria", new Categoria());
            model.addAttribute("estudio", new Estudio());

            return "views/crud";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("categorias",this.svcCategoria.findAll());
            model.addAttribute("estudios",this.svcEstudio.findAll());
            if(id==0){
                model.addAttribute("videojuego",new Videojuego());
            }else{
                model.addAttribute("videojuego",this.svcVideojuego.findById(id));
            }
            return "views/formulario/videojuego";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(
            @RequestParam("archivo") MultipartFile archivo,
            @Valid @ModelAttribute("videojuego") Videojuego videojuego,
            BindingResult result,
            Model model,@PathVariable("id")long id
    ) {

        try {
            model.addAttribute("categorias",this.svcCategoria.findAll());
            model.addAttribute("estudios",this.svcEstudio.findAll());
            if(result.hasErrors()){
                return "views/formulario/videojuego";
            }
            String ruta = System.getProperty("user.dir")+ "/src/main/resources/static/images";
            int index = archivo.getOriginalFilename().indexOf(".");
            String extension = "";
            extension = "."+archivo.getOriginalFilename().substring(index+1);
            String nombreFoto = Calendar.getInstance().getTimeInMillis()+extension;
            Path rutaAbsoluta = id != 0 ? Paths.get(ruta + "//"+videojuego.getImagen()) :
                    Paths.get(ruta+"//"+nombreFoto);
            if(id==0){
                if(archivo.isEmpty()){
                    model.addAttribute("imageErrorMsg","La imagen es requerida");
                    return "views/formulario/videojuego";
                }
                if(!this.validarExtension(archivo)){
                    model.addAttribute("imageErrorMsg","La extension no es valida");
                    return "views/formulario/videojuego";
                }
                if(archivo.getSize() >= 15000000){
                    model.addAttribute("imageErrorMsg","El peso excede 15MB");
                    return "views/formulario/videojuego";
                }
                Files.write(rutaAbsoluta,archivo.getBytes());
                videojuego.setImagen(nombreFoto);
                this.svcVideojuego.saveOne(videojuego);
            }else{
                if(!archivo.isEmpty()){
                    if(!this.validarExtension(archivo)){
                        model.addAttribute("imageErrorMsg","La extension no es valida");
                        return "views/formulario/videojuego";
                    }
                    if(archivo.getSize() >= 15000000){
                        model.addAttribute("imageErrorMsg","El peso excede 15MB");
                        return "views/formulario/videojuego";
                    }
                    Files.write(rutaAbsoluta,archivo.getBytes());
                }
                this.svcVideojuego.updateOne(videojuego,id);
            }
            return "redirect:/crud";
        }catch(Exception e){
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("objeto", "videojuego");
            model.addAttribute("objetoId", Long.toString(id));
            return "views/formulario/eliminar";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());

            return "error";
        }
    }

    @PostMapping("/eliminar/videojuego/{id}")
    public String desactivarVideojuego(Model model,@PathVariable("id")long id){
        try {
            this.svcVideojuego.deleteById(id);
            return "redirect:/crud";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            System.out.println(e);
            return "error";
        }
    }

    // CRUD Categoria
    @GetMapping("/formulario/categoria/{id}")
    public String formCategoria(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("categoria",this.svcCategoria.findById(id));
            return "views/formulario/categoria";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/categoria/{id}")
    public String guardarCategoria(
            @Valid @ModelAttribute("categoria") Categoria categoria,
            BindingResult result,
            Model model,@PathVariable("id")long id
    ) {
        try {
            if(result.hasErrors()){
                return "views/formulario/categoria";
            }
            if (id == 0) {
                this.svcCategoria.saveOne(categoria);
            } else {
                this.svcCategoria.updateOne(categoria,id);
            }
            return "redirect:/crud";
        }catch(Exception e){
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/categoria/{id}")
    public String eliminarCategoria(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("objeto", "categoria");
            model.addAttribute("objetoId", Long.toString(id));
            return "views/formulario/eliminar";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());

            return "error";
        }
    }

    @PostMapping("/eliminar/categoria/{id}")
    public String desactivarCategoria(Model model,@PathVariable("id")long id){
        try {
            this.svcCategoria.deleteById(id);
            return "redirect:/crud";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            System.out.println(e);
            return "error";
        }
    }

    // CRUD Estudios
    @GetMapping("/formulario/estudio/{id}")
    public String formEstudio(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("estudio",this.svcEstudio.findById(id));
            return "views/formulario/estudio";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/estudio/{id}")
    public String guardarEstudio(
            @Valid @ModelAttribute("estudio") Estudio estudio,
            BindingResult result,
            Model model,@PathVariable("id")long id
    ) {
        try {
            if(result.hasErrors()){
                return "views/formulario/estudio";
            }
            if (id == 0) {
                this.svcEstudio.saveOne(estudio);
            } else {
                this.svcEstudio.updateOne(estudio,id);
            }
            return "redirect:/crud";
        }catch(Exception e){
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/estudio/{id}")
    public String eliminarEstudio(Model model,@PathVariable("id")long id){
        try {
            model.addAttribute("objeto", "estudio");
            model.addAttribute("objetoId", Long.toString(id));
            return "views/formulario/eliminar";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());

            return "error";
        }
    }

    @PostMapping("/eliminar/estudio/{id}")
    public String desactivarEstudio(Model model,@PathVariable("id")long id){
        try {
            this.svcEstudio.deleteById(id);
            return "redirect:/crud";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            System.out.println(e);
            return "error";
        }
    }


    public boolean validarExtension(MultipartFile archivo){
        try {
            ImageIO.read(archivo.getInputStream()).toString();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}