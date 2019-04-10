package com.thiagohenrique.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagohenrique.cursomc.domain.Cidade;
import com.thiagohenrique.cursomc.domain.Cliente;
import com.thiagohenrique.cursomc.domain.Endereco;
import com.thiagohenrique.cursomc.domain.enums.TipoCliente;
import com.thiagohenrique.cursomc.dto.ClienteDTO;
import com.thiagohenrique.cursomc.dto.ClienteNewDTO;
import com.thiagohenrique.cursomc.repositories.ClienteRepository;
import com.thiagohenrique.cursomc.repositories.EnderecoRepository;
import com.thiagohenrique.cursomc.services.exception.DataIntegrityException;
import com.thiagohenrique.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionados");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null,
				clienteNewDTO.getNome(),
				clienteNewDTO.getEmail(),
				clienteNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteNewDTO.getTipo())
				);
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
		Endereco address = new Endereco(
				null,
				clienteNewDTO.getLogradouro(),
				clienteNewDTO.getNumero(),
				clienteNewDTO.getComplemento(),
				clienteNewDTO.getBairro(),
				clienteNewDTO.getCep(),
				cliente,
				cidade);
		
		cliente.getEnderecos().add(address);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		
		if (clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if (clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
