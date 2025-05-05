package presentation.controllers;

import application.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presentation.dto.CoordenadorDto;

import java.util.List;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {
    @Autowired
    private CoordenadorService coordenadorService;

    @GetMapping
    public ResponseEntity<List<CoordenadorDto>> listarCoordenadores(){
        return ResponseEntity.ok(coordenadorService.listar());
    }
}
