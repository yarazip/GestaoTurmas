package com.projeto.ads.service;

import java.util.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.ads.model.Turma;
import com.projeto.ads.repository.TurmaRepository;

@Service
public class ServiceTurma {

	@Autowired
	TurmaRepository turmaRepository;
	
	public String gerarCodigo(String curso, String periodo, String turno) {
		Date data= new Date();
		Calendar calendario= Calendar.getInstance();
		calendario.setTime(data);
		int ano= calendario.get(Calendar.YEAR);
		Turma turma= turmaRepository.findLastInsertedTurma();
		if(turma==null) {//nao existe nenhuma turma na base de dados
			return curso+"-"+periodo+"-"+turno+"-"+ano+"1";//ADS-2-M-20251	
		}
		else {
			return curso+"-"+periodo+"-"+turno+"-"+ano+""+turma.getId()+1;//ADS-2-M-20252
		}
		
		}
}
