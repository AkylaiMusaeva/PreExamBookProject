package peaksoft.service;

import peaksoft.entity.Author;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    String saveAuthor(Author author);

    String updateAuthorById(Long id, Author author);

    Author getAuthorById(Long id);

    String assignAuthorToPublisher(Long publisherId, Long authorId);

    Map<String,List<Author>> getAuthorsByPublisherId(Long publisherId);

    String deleteAuthorById(Long id);
}
