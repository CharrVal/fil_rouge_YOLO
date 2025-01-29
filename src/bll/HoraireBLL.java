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
	
	
	public List<Horaire> insert(List<Horaire> horaires,int idRestaurant) throws HoraireException{
		for(Horaire heure:horaires) {
			checkHoraire(heure);
		}
		HoraireDAO dao = new HoraireDAO();
		dao.insert(horaires,idRestaurant);
		return horaires;
			
	}
	
	
	public Horaire update(String jour,LocalTime ouverture,LocalTime fermeture) throws HoraireException {
		Horaire horaire=new Horaire(jour,ouverture,fermeture);
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
		    if (horaire.getOuverture().isAfter(horaire.getFermeture())) {
		        throw new HoraireException("L'heure d'ouverture doit être avant l'heure de fermeture");
		    }
			
	}
}
	

