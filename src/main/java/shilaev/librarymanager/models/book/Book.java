package shilaev.librarymanager.models.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    @NotEmpty(message = "can't be empty")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
            message = "must be in ISBN format")
    private String isbn;

    @NotEmpty(message = "can't be empty")
    private String title;

    @NotNull(message = "can't be null")
    private int authorId;

    @NotEmpty(message = "can't be empty")
    private String authorLastName;

    @NotNull(message = "can't be null")
    @Range(min = 0, max = Integer.MAX_VALUE,
            message = "must be greater then 0")
    private int yearOfWriting;

    @NotNull(message = "can't be null")
    @Range(min = 0, max = Integer.MAX_VALUE,
            message = "must be greater then 0")
    private int unitsInStock;

    // CONSTRUCTORS
    public Book(String isbn, String title, String authorName, int yearOfWriting, int unitsInStock) {
        this.isbn = isbn;
        this.title = title;
        this.authorLastName = authorName;
        this.yearOfWriting = yearOfWriting;
        this.unitsInStock = unitsInStock;
    }

    public Book(String isbn, String title, int authorId, int yearOfWriting, int unitsInStock) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
        this.yearOfWriting = yearOfWriting;
        this.unitsInStock = unitsInStock;
    }
}
