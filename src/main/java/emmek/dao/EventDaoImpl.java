package emmek.dao;

import emmek.entities.Concert;
import emmek.entities.Event;
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

}
