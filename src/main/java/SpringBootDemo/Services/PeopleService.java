package SpringBootDemo.Services;

import SpringBootDemo.Entities.People;

public interface PeopleService {
	People[] getPeoples();
	People getPeople(int id);
	boolean createPeople(People People);
	boolean updatePeople(People People);
	boolean deletePeople(int id);
}