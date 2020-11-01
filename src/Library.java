/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to
 * be able to check out books, if a copy of the requested book is available.
 */

class Library {
    int NEGATIVE_NUMBER = -1;
    /**
     * The maximal number of books this library can hold.
     */
    final int maxBookCapacity;
    /**
     * The maximal number of books this library allows a single patron to borrow at the same time.
     */
    final int maxBorrowedBooks;
    /**
     * The maximal number of registered patrons this library can handle.
     */
    final int maxPatronCapacity;
    /**
     * An array of all the books in the library.
     */
    Book[] bookArray;
    /**
     * An array of all the patrons in the library.
     */
    Patron[] patronArray;


    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     *
     * @param libraryMaxBookCapacity   The maximal number of books this library can hold.
     * @param libraryMaxBorrowedBooks  The maximal number of books this library allows a single patron to
     *                                 borrow at the same time.
     * @param libraryMaxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int libraryMaxBookCapacity, int libraryMaxBorrowedBooks, int libraryMaxPatronCapacity) {
        maxBookCapacity = libraryMaxBookCapacity;
        maxBorrowedBooks = libraryMaxBorrowedBooks;
        maxPatronCapacity = libraryMaxPatronCapacity;
        // allocates memory for the amount of books the library can hold
        bookArray = new Book[maxBookCapacity];
        // allocates memory for the amount of patrons the library can hold
        patronArray = new Patron[maxPatronCapacity];
    }
    /*----=  Instance Methods  =-----*/

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book - The book to add to this library.
     * @returns a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        for (int i = 0; i < maxBookCapacity; i++) {
            if (bookArray[i] == book) {
                return i;
            }
            if (bookArray[i] == null) {
                bookArray[i] = book;
                return i;
            }
        }
        return NEGATIVE_NUMBER;
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId - The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        if (bookId < maxBookCapacity && bookId >= 0 && bookArray[bookId] != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book - The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i < maxBookCapacity; i++) {
            if (bookArray[i] == book) {
                return i;
            }
            if (bookArray[i] == null) {
                break;
            }
        }
        return NEGATIVE_NUMBER;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId - The id number of the book to check.
     * @returns true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if (isBookIdValid(bookId) && bookArray[bookId].getCurrentBorrowerId() == NEGATIVE_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron - The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     * registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        for (int i = 0; i < maxPatronCapacity; i++) {
            if (patronArray[i] == patron) {
                return i;
            }
            if (patronArray[i] == null) {
                patronArray[i] = patron;
                return i;
            }
        }
        return NEGATIVE_NUMBER;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId - The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        if (patronId < maxPatronCapacity && patronId >= 0 && patronArray[patronId] != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     *
     * @param patron - The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i < maxPatronCapacity; i++) {
            if (patronArray[i] == patron) {
                return i;
            }
            if (patronArray[i] == null) {
                break;
            }
        }
        return NEGATIVE_NUMBER;
    }

    /**
     * that counts the amount of books the given patron (using patron ID) is borrowing.
     *
     * @param patronId - The id number of the patron that will borrow the book.
     * @return the amount of books the given patron already borrowed.
     */
    int books_at_patron(int patronId) {
        int count = 0;
        for (int i = 0; i < maxBookCapacity; i++) {
            if (bookArray[i] != null && bookArray[i].getCurrentBorrowerId() == patronId) {
                count++;
            }
        }
        return count;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
     * book is available, the given patron isn't already borrowing the maximal number of books allowed, and if
     * the patron will enjoy this book.
     *
     * @param bookId   - The id number of the book to borrow.
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if (!isBookIdValid(bookId) || !isPatronIdValid(patronId)) {
            return false;
        }
        Patron patron = patronArray[patronId];
        Book book = bookArray[bookId];
        if (isBookAvailable(bookId) && patron.willEnjoyBook(book) && books_at_patron(patronId) < maxBorrowedBooks) {
            book.setBorrowerId(patronId);
            return true;
        }
        return false;
    }

    /**
     * Return the given book.
     *
     * @parm bookId - The id number of the book to return.
     */
    void returnBook(int bookId) {
        bookArray[bookId].setBorrowerId(NEGATIVE_NUMBER);
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
     * will enjoy, if any such exist.
     *
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     * @parm patronId - The id number of the patron to suggest the book to.
     */
    Book suggestBookToPatron(int patronId) {
        Patron patron = patronArray[patronId];
        int max_score = 0;
        int id_best_book = 0;
        for (int i = 0; i < maxBorrowedBooks; i++) {
            if (patron.willEnjoyBook(bookArray[i]) && patron.getBookScore(bookArray[i]) > max_score && isBookAvailable(i)) {
                max_score = patron.getBookScore(bookArray[i]);
                id_best_book = i;
            }
        }
        if (max_score > 0) {
            return bookArray[id_best_book];
        } else {
            return null;
        }
    }
}