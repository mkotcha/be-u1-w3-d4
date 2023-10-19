package emmek;

import emmek.dao.EventDaoImpl;
import emmek.dao.LocationDaoImpl;
import emmek.dao.ParticipationDaoImpl;
import emmek.dao.PersonDaoImpl;
import emmek.entities.*;
import emmek.enumType.EventType;
import emmek.enumType.Genre;
import emmek.enumType.Sex;
import emmek.utils.JpaUtil;
import net.datafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.concurrent.TimeUnit;

public class Application {
    private static final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();
        Location location;
        LocationDaoImpl locationDao = new LocationDaoImpl(em);
        EventDaoImpl eventDao = new EventDaoImpl(em);
        location = new Location(faker.address().cityName(), faker.address().city());
        locationDao.save(location);

        FootballMatch match = new FootballMatch(faker.book().title(),
                faker.date().future(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(),
                faker.lorem().paragraph(),
                faker.options().option(EventType.class),
                faker.number().numberBetween(10, 200),
                location,
                faker.address().city(),
                faker.address().city());

        match.setAwayScore(faker.number().numberBetween(0, 4));
        match.setHomeScore(faker.number().numberBetween(0, 4));

        eventDao.save(match);

//        eventDao.getStreamingConcert().forEach(elm -> System.out.println(elm.toString()));
//        eventDao.getConcertsByGenre(Genre.ROCK).forEach(elm -> System.out.println(elm.toString()));
//        eventDao.getWinHome().forEach(elm -> System.out.println(elm.getResult()));
        eventDao.getWinAway().forEach(elm -> System.out.println(elm.getResult()));
        eventDao.getDraw().forEach(elm -> System.out.println(elm.getResult()));

    }

    public void fakerize() {

        EntityManager em = emf.createEntityManager();
        Event event;
        Location location;
        Person person = null;
        Participation participation;
        Faker faker = new Faker();
        EventDaoImpl eventDao = new EventDaoImpl(em);
        LocationDaoImpl locationDao = new LocationDaoImpl(em);
        PersonDaoImpl personDao = new PersonDaoImpl(em);
        ParticipationDaoImpl participationDao = new ParticipationDaoImpl(em);
        try {
            for (int i = 0; i < 10; i++) {

                location = new Location(faker.address().cityName(), faker.address().city());
                locationDao.save(location);
                Concert concert = new Concert(faker.book().title(),
                        faker.date().future(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(),
                        faker.lorem().paragraph(),
                        faker.options().option(EventType.class),
                        faker.number().numberBetween(10, 200),
                        location,
                        faker.options().option(Genre.class),
                        faker.bool().bool());
                eventDao.save(concert);
//                event = new Event(faker.book().title(),
//                        faker.date().future(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(),
//                        faker.lorem().paragraph(),
//                        faker.options().option(EventType.class),
//                        faker.number().numberBetween(10, 200),
//                        location);
//                eventDao.save(event);
                person = new Person(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.date().birthday().toLocalDateTime().toLocalDate(),
                        faker.options().option(Sex.class));
                personDao.save(person);
//                participation = new Participation(person, event, faker.options().option(ParticipationState.class));
//                participationDao.save(participation);
            }
            System.out.println();

            person.getParticipations().forEach(System.out::println);
            System.out.println();
//            eventDao.delete(eventDao.getById(42));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }


    }

}