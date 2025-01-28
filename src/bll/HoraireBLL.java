package bll;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import bo.Horaire;
import dal.HoraireDAO;
import exceptions.HoraireException;

public class HoraireBLL {
	private static final List<String> SEMAINE = Arrays.asList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche");
	public List<Horaire> select() {
		HoraireDAO dao = new HoraireDAO();
		return dao.select();
	}
	
	
	public Horaire insert(String nom, LocalTime ouverture,LocalTime fermeture) throws HoraireException{
		
		Horaire horaire=new Horaire(nom,ouverture,fermeture);
		checkHoraire(horaire);
		HoraireDAO dao = new HoraireDAO();
		dao.insert(horaire);
		return horaire;
			
	}
	
	
	public Horaire update(String nom, LocalTime ouverture,LocalTime fermeture) throws HoraireException {
		
		Horaire horaire=new Horaire(nom,ouverture,fermeture);
		checkHoraire(horaire);
		HoraireDAO dao = new HoraireDAO();
		dao.update(horaire);
		return horaire;
			
	}
	
	public void delete(int id) {
		HoraireDAO dao = new HoraireDAO();
		dao.delete(id);
	}
	
	
	
	public void checkHoraire(Horaire horaire) throws HoraireException{
		
		if(!(SEMAINE.contains(horaire.getJour()))) {
			throw new HoraireException("Entrer un jour de semaine valide");
		}
		
		
	}
	
	

	
	

}
