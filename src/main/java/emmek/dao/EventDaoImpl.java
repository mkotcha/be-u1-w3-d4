package emmek.dao;

import emmek.entities.*;
import emmek.enumType.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventDaoImpl implements EventDao {

    private final EntityManager em;

    public EventDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Event event) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(event);
        tx.commit();
        System.out.println("Event " + event.getTitle() + " saved");
    }

    @Override
    public Event getById(long id) {
        return em.find(Event.class, id);
    }

    @Override
    public void delete(Event event) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(event);
        tx.commit();
        System.out.println("Event " + event.getTitle() + " deleted");
    }

    @Override
    public void refresh(Event event) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(event);
        tx.commit();
        System.out.println("Event refreshed");
    }

    public List<Concert> getStreamingConcert() {
        TypedQuery<Concert> query = em.createQuery("SELECT c FROM Concert c WHERE c.isStreaming = true", Concert.class);
        return query.getResultList();
    }

    public List<Concert> getConcertsByGenre(Genre genre) {
        TypedQuery<Concert> query = em.createQuery("SELECT c FROM Concert c WHERE c.genre = :genre", Concert.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    public List<FootballMatch> getWinHome() {
        TypedQuery<FootballMatch> query = em.createNamedQuery("winHome", FootballMatch.class);
        return query.getResultList();
    }

    public List<FootballMatch> getWinAway() {
        TypedQuery<FootballMatch> query = em.createNamedQuery("winAway", FootballMatch.class);
        return query.getResultList();
    }

    public List<FootballMatch> getDraw() {
        TypedQuery<FootballMatch> query = em.createNamedQuery("draw", FootballMatch.class);
        return query.getResultList();
    }

    public List<AthleticsCompetition> getWinByAthlete(Person person) {
        TypedQuery<AthleticsCompetition> query = em.createNamedQuery("winByAthlete", AthleticsCompetition.class);
        query.setParameter("winner", person);
        return query.getResultList();
    }

    public List<Person> getPartecipateByAthlete(Person person) {
        TypedQuery<Person> query = em.createNamedQuery("partecipateByAthlete", Person.class);
        query.setParameter("athlete", person);
        return query.getResultList();
    }

}
