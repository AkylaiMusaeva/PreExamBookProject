package peaksoft.repository;

import peaksoft.entity.Author;

import java.util.List;
import java.util.Map;

public interface AuthorRepository {
    void saveAuthor(Author author);

    void updateAuthorById(Long id, Author author);

    Author getAuthorById(Long id);

    void assignAuthorToPublisher(Long publisherId,Long authorId);

     Map<String,List<Author>> getAuthorsByPublisherId(Long publisherId);

    void deleteAuthorById(Long id);
}
