package com.mkmmm.spring6webapp.bootstrap;

import com.mkmmm.spring6webapp.domain.Author;
import com.mkmmm.spring6webapp.domain.Book;
import com.mkmmm.spring6webapp.domain.Publisher;
import com.mkmmm.spring6webapp.repositories.AuthorRepository;
import com.mkmmm.spring6webapp.repositories.BookRepository;
import com.mkmmm.spring6webapp.repositories.publisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoostrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final publisherRepository publisherRepository;

    public BoostrapData(AuthorRepository authorRepository, BookRepository bookRepository, publisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Initialize data
        Author mac = new Author();
        mac.setFirstName("Maciej");
        mac.setLastName("Lalala");

        Book someBook = new Book();
        someBook.setTitle("Really Good Book title");
        someBook.setIsbn("12341234");

        Publisher somePublisher = new Publisher();
        somePublisher.setPublisherName("Some stupid publisher's name");
        somePublisher.setAddress("123 Some Street");
        somePublisher.setCity("Broken Arrow");
        somePublisher.setState("Oklahoma");
        somePublisher.setZip(12334);




        // Create save interfaces to repos
        // xxxxRepositories are instances of the class which extends the CRUDRepo
        //  is a method provided by CrudRepository (or its sub-interfaces) that
        //  saves an entity to the database. When you call bookRepository.save(someBook),
        //  it takes the someBook object (an instance of Book) as an argument
        Author macSaved = authorRepository.save(mac);
        Book bookSaved = bookRepository.save(someBook);
        Publisher pubSaved = publisherRepository.save(somePublisher);


        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Blablablab");

        Book noEJB = new Book();
        noEJB.setTitle("Stupid Book title");
        noEJB.setIsbn("65346346534563456");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);


        // Create the associations between the entities
        macSaved.getBooks().add(bookSaved);
        rodSaved.getBooks().add(noEJBSaved);

        // Add persistance to the associations!!!!
        authorRepository.save(macSaved);
        authorRepository.save(rodSaved);

        publisherRepository.save(pubSaved);

        System.out.println("In Boostrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
