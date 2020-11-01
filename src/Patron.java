/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of books.
 */
public class Patron {
    /**
     * The first name of this patron.
     */
    final String first_name;
    /**
     * The last name of this patron.
     */
    final String last_name;
    /**
     * The weight this patron assigns to the comic aspects of books.
     */
    int comicTendency;
    /**
     * The weight this patron assigns to the dramatic aspects of books.
     */
    int dramaticTendency;
    /**
     * The weight this patron assigns to the educational aspects of books.
     */
    int educationalTendency;
    /**
     * The minimal literary value a book must have for this patron to enjoy it.
     */
    int enjoymentThreshold;


    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     *
     * @param patronFirstName            The first name of the patron.
     * @param patronLastName             The last name of the patron.
     * @param patronComicTendency        The weight the patron assigns to the comic aspects of books.
     * @param patronDramaticTendency     The weight the patron assigns to the dramatic aspects of books.
     * @param patronEducationalTendency  The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold   The minimal literary value a book must have for the patron to enjoy it.
     */
    Patron(String patronFirstName, String patronLastName, int patronComicTendency, int patronDramaticTendency,
           int patronEducationalTendency, int patronEnjoymentThreshold){
        first_name = patronFirstName;
        last_name = patronLastName;
        comicTendency = patronComicTendency;
        dramaticTendency = patronDramaticTendency;
        educationalTendency = patronEducationalTendency;
        enjoymentThreshold = patronEnjoymentThreshold;

    }
    /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name, separated by a
     * single white space. For example, if the patron's first name is "Ricky" and his last name is "Bobby", this method
     * will return the String "Ricky Bobby".
     *
     * @return the String representation of this patron.
     */
    String stringRepresentation(){
        return first_name + " "+ last_name;
    }
    /**
     * Returns the literary value this patron assigns to the given book.
     *
     * @param book - The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book){
        return book.comicValue*comicTendency + book.dramaticValue*dramaticTendency + book.educationalValue*educationalTendency;
    }
    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book - The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book){
        if (getBookScore(book) >= enjoymentThreshold){
            return true;
        }
        else{
            return false;
        }

    }
}
