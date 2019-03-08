package com.notas.core.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.notas.core.entity.Nota;
import com.notas.core.model.MNota;

@Component("convertidor")
public class Convertidor {
	public List<MNota> convertirLista(List<Nota> notas){
		List<MNota> mnotas = new ArrayList<>();
		for(Nota nota : notas) {
			mnotas.add(new MNota(nota));
		}
		return mnotas;
	}
}
