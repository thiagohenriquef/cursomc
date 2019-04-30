package com.thiagohenrique.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thiagohenrique.cursomc.domain.Cidade;
import com.thiagohenrique.cursomc.domain.Estado;
import com.thiagohenrique.cursomc.dto.CidadeDTO;
import com.thiagohenrique.cursomc.dto.EstadoDTO;
import com.thiagohenrique.cursomc.services.CidadeService;
import com.thiagohenrique.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listaDTO = list.stream().map(el -> new EstadoDTO(el)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listaDTO = list.stream().map(el -> new CidadeDTO(el)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
}
