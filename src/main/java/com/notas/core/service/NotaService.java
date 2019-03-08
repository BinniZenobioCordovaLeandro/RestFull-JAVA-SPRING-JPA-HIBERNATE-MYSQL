package com.notas.core.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notas.core.converter.Convertidor;
import com.notas.core.entity.Nota;
import com.notas.core.model.MNota;
import com.notas.core.repository.NotaRepositorio;

@Service("servicio")
public class NotaService {
	@Autowired
	@Qualifier("repositorio")
	private NotaRepositorio repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	private static final Log logger = LogFactory.getLog(NotaService.class);
	
	public boolean crear(Nota nota) {
		logger.info("CREANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("Nota Creada");
			return true;
		} catch (Exception e) {
			logger.error("Error al crear la Nota");
			return false;
		}
	}
	
	public boolean actualizar(Nota nota) {
		logger.info("ACTUALIZANDO NOTA");
		try {
			repositorio.findById(nota.getId());
			
			repositorio.save(nota);
			logger.info("Actualizando Nota");
			return true;
		} catch (Exception e) {
			logger.info("Error actualizando Nota");
			return false;
		}
	}

	public boolean borrar(String nombre, long id) {
		try {
			Nota nota = repositorio.findByNombreAndId(nombre, id);
			repositorio.delete(nota);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<MNota> obtener() {
		List<MNota> notas = null;
		List<Nota> notasEn = repositorio.findAll();
		notas = convertidor.convertirLista(notasEn);
		return notas;
	}
	
	public MNota obtenerPorNombreTitulo(String nombre, String titulo) {
		return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	public List<MNota> obtenerTitulo(String titulo){	
		return convertidor.convertirLista(repositorio.findByTitulo(titulo));
	}
	
	public List<MNota> obtenerPorPaginacion(Pageable pageable){
		return convertidor.convertirLista(repositorio.findAll(pageable).getContent());	
	}
	
	
}
