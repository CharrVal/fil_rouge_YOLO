package bll;

import java.time.LocalDate;
import java.util.List;

import bo.Horaire;
import dal.HoraireDAO;
import exceptions.HoraireException;

public class HoraireBLL {
	public List<Horaire> select() {
		HoraireDAO dao = new HoraireDAO();
		return dao.select();
	}

	public Horaire insert(String jour, LocalDate ouverture, LocalDate fermeture) throws Exception {
		Horaire horaire = new Horaire(jour, ouverture, fermeture);
		checkHoraire(horaire);
		
		HoraireDAO dao = new HoraireDAO();
		dao.insert(horaire);
		
		return horaire;
	}
	
	private void checkHoraire(Horaire horaire) throws HoraireException {
		// les if sont à déterminer !
		}
	
	public void update(Horaire horaire) throws HoraireException {
		checkHoraire(horaire);
		
		HoraireDAO dao = new HoraireDAO();
		dao.update(horaire);
	}
	
	public void delete (int id) {
		HoraireDAO dao = new HoraireDAO();
		dao.delete(id);
	}
}
