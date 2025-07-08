import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Testing {

    @Test public void testFilteringBooks() {
        try {
            Inventory inventory = new Inventory();
            Book outdatedPb = new Paperbook(25, 10, LocalDate.now().minusYears(20), "123456", "outdated paper book");
            Book eB = new Ebook(10, LocalDate.now(), "123457", "Ebook", "Eb@domain.com", "pdf");
            Book pb = new Paperbook(10, 10, LocalDate.now(), "123458", "paper book");
            Book showcase = new Showcasebook(LocalDate.now(), "123459", "showcase book");
            Book outdatedEbook = new Ebook(10, LocalDate.now().minusYears(15), "123460", "outdated Ebook", "Eb@domain.com", "pdf");
            Book regularPbook = new Paperbook(10, 10, LocalDate.now().minusYears(5), "123461", "regular paper book");

            inventory.addBook(outdatedPb);
            inventory.addBook(eB);
            inventory.addBook(pb);
            inventory.addBook(showcase);
            inventory.addBook(outdatedEbook);
            inventory.addBook(regularPbook);
            inventory.filterOutdatedBooks();
            ArrayList<Book> outdatedBooks = inventory.getOutdatedBooks();

            ArrayList<Book> updatedBooks = inventory.getBooks();
            for (int i = 0; i < outdatedBooks.size(); i++) {
                assert (!updatedBooks.contains(outdatedBooks.get(i)));
                System.out.println(outdatedBooks.get(i).getTitle());
            }
            for (int i = 0; i < updatedBooks.size(); i++) {
                assert (!outdatedBooks.contains(updatedBooks.get(i)));
                System.out.println(updatedBooks.get(i).getTitle());
            }
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testBuyBook() {
        try {
            Inventory inventory = new Inventory();
            Book ebook = new Ebook(10, LocalDate.now(), "123457", "Ebook", "test@email.com", "pdf");
            Book paperbook = new Paperbook(5, 20, LocalDate.now(), "123458", "Paper Book");
            Book showcaseBook = new Showcasebook(LocalDate.now(), "123459", "Showcase Book");

            inventory.addBook(ebook);
            inventory.addBook(paperbook);
            inventory.addBook(showcaseBook);

            // Test Ebook (should have quantity 1)
            assertEquals(10.0, inventory.buyBook("123457", 3), 0.001);

            // Test Paperbook (should return price * quantity)
            assertEquals(60.0, inventory.buyBook("123458", 3), 0.001);

            // Test Showcase book (should return -1)
            assertEquals(-1.0, inventory.buyBook("123459", 1), 0.001);

            // Test invalid book (should return -2)
            assertEquals(-2.0, inventory.buyBook("999999", 1), 0.001);

            // Test buying more than available quantity for paperbook (should return -2)
            assertEquals(-2.0, inventory.buyBook("123458", 25), 0.001);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testBuyOutdatedBook() {
        try {
            Inventory inventory = new Inventory();
            Book outdatedBook = new Paperbook(5, 20, LocalDate.now().minusYears(15), "123460", "Outdated Book");
            inventory.addBook(outdatedBook);
            assertEquals(-2.0, inventory.buyBook("123460", 1), 0.001);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveBook() {
        try {
            Inventory inventory = new Inventory();
            Book book = new Paperbook(5, 20, LocalDate.now(), "123460", "Test Book");

            inventory.addBook(book);
            assertTrue(inventory.getBooks().contains(book));

            inventory.removeBook("123460");
            assertFalse(inventory.getBooks().contains(book));

            // Test removing non-existent book
            inventory.removeBook("999999");
            assertEquals(0, inventory.getBooks().size());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testReduceBookQuantityNonZero() {
        try {
            Inventory inventory = new Inventory();
            Book book = new Paperbook(5, 20, LocalDate.now(), "123460", "Test Book");

            inventory.addBook(book);
            inventory.buyBook("123460", 3);

            assertTrue(inventory.getBooks().contains(book));
            assertEquals(2, ((Paperbook) inventory.getBooks().get(0)).quantity);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testReduceBookQuantityToZero() {
        try {
            Inventory inventory = new Inventory();
            Book book = new Paperbook(5, 20, LocalDate.now(), "123460", "Test Book");

            inventory.addBook(book);
            inventory.buyBook("123460", 5);

            assertFalse(inventory.getBooks().contains(book));
            assertEquals(0, inventory.getBooks().size());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testBookSettersAndGetters() {
        try {
            Book book = new Book();
            book.setTitle("New Title");
            book.setIsbn("987-654");
            book.setPrice(29.99);
            LocalDate date = LocalDate.of(2024, 1, 1);
            book.setPublish_Year(date);

            assertEquals("New Title", book.getTitle());
            assertEquals("987-654", book.getIsbn());
            assertEquals(29.99, book.getPrice(), 0.001);
            assertEquals(date, book.getPublish_Year());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testPaperbookConstructor() {
        try {
            LocalDate date = LocalDate.now();
            Paperbook newPaperbook = new Paperbook(5, 50, date, "111-222", "Test Title");

            assertEquals("Test Title", newPaperbook.getTitle());
            assertEquals("111-222", newPaperbook.getIsbn());
            assertEquals(50.0, newPaperbook.getPrice(), 0.001);
            assertEquals(date, newPaperbook.getPublish_Year());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }


}
