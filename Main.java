import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import java.util.List;
import java.util.logging.Level;


  public class Main {

    public static void main(String[] args) {

      SessionFactory sessionFactory = generateSessionFactory();

      Session session = sessionFactory.openSession();

      session.beginTransaction();

      // Create a 1st Owner

      Owner firstOwner = new Owner();
      firstOwner.setFirstName("Tom");
      firstOwner.setLastName("Riddle");

      // Save the 1st Owner to the session
      session.save(firstOwner);

      // Create a 1st Pet
      Pet firstPet = new Pet();
      firstPet.setName("Spike");
      firstPet.setSpecies("dog");
      firstPet.setOwner(firstOwner);
      session.save(firstPet);

      // Create a 2nd Pet for the 1st Owner

      Pet secondPet = new Pet();
      secondPet.setName("Brutus");
      secondPet.setSpecies("Rat");
      secondPet.setOwner(firstOwner);

      // Create a 2nd Owner

      Owner secondOwner = new Owner();
      secondOwner.setFirstName("Alexander");
      secondOwner.setLastName("Hamilton");

      // Transfer the 1st Pet to the 2nd owner

      firstPet.setOwner(secondOwner);

      // Create a 3rd Pet assigned to the 1st owner

      Pet thirdPet = new Pet();
      thirdPet.setName("Smurf");
      thirdPet.setSpecies("cartoon");
      thirdPet.setOwner(firstOwner);

      //delete  1st pet
      session.delete(firstPet);


      // Change the 2nd Pet's name
      secondPet.setName("Jerry");

      // Save all your objects
      session.save(firstOwner);
      session.save(secondOwner);
      session.save(secondPet);
      session.save(thirdPet);

      //Print out all the owners and pets 

      System.out.println("1st Owner: " + firstOwner.getFirstName() + " " + firstOwner.getLastName());

      System.out.println("2nd Owner: " + secondOwner.getFirstName() + " " 

  + secondOwner.getLastName());;

      System.out.println("1st owners first pet: " + secondPet.getName());

      System.out.println("1st owners second pet: " + thirdPet.getName());

      // Commit transaction and close out connections

      session.getTransaction().commit();

      session.close();

      sessionFactory.close();

    }

    // Don't touch this method!
    private static SessionFactory generateSessionFactory() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:postgresql://" + System.getenv("DATABASE_URL"));
        props.setProperty("hibernate.connection.username", System.getenv("DATABASE_USERNAME"));
        props.setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        Configuration config = new Configuration();
        config.setProperties(props);
        config.addAnnotatedClass(Owner.class);
        config.addAnnotatedClass(Pet.class);
        return config.buildSessionFactory();
    }
}