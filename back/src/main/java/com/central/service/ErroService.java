package com.central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.entity.Erro;
import com.central.exception.ResourceNotFoundException;
import com.central.repository.ErroRepository;
import com.central.service.interfaces.ErroServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ErroService implements ErroServiceInterface{

	 @Autowired(required=false)
	 private ErroRepository repository;
	
	@Override
	public Erro save(Erro object) {
		return repository.save(object);
	}

	@Override
	public Optional<Erro> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Integer frequenciaErros(String erro) {
		return repository.frequenciaErros(erro);
	}

	@Override
	public List<Erro> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}

	@Override
	public List<Erro> findByOrigem(String origem) {
		return repository.findByOrigem(origem);
	}

	@Override
	public List<Erro> findByLevel(String level) {
		return repository.findByLevel(level);
	}

	@Override
	public List<Erro> findArquivados() {
		return repository.findArquivados();
	}

	@Override
	public List<Erro> findNaoArquivados() {
		return repository.findNaoArquivados();
	}
	
	@Override
	public List<Erro> getAllErros() {
		return repository.findAll();
	}

	public ResponseEntity<Erro> atualizaErro(Long erroId, Erro novoErro) throws ResourceNotFoundException {

		Erro erro = repository.findById(erroId).orElseThrow(() -> new ResourceNotFoundException("Erro não encontrado para o id " + erroId));
        erro.setTitulo(novoErro.getTitulo());
        erro.setDetalhes(novoErro.getDetalhes());
        erro.setOrigem(novoErro.getOrigem());
        erro.setNivel(novoErro.getNivel());
        erro.setAmbiente(novoErro.getAmbiente());
        erro.setArquivado(novoErro.isArquivado());
        erro.setUsuario(novoErro.getUsuario());
        final Erro erroAtualizado = repository.save(erro);
        
        return ResponseEntity.ok(erroAtualizado);
		
	}

}
