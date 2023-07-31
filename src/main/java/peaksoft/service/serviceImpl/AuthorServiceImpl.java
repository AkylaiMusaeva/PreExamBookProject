package peaksoft.service.serviceImpl;

import peaksoft.entity.Author;
import peaksoft.repository.AuthorRepository;
import peaksoft.repository.repositoryImpl.AuthorRepoImpl;
import peaksoft.service.AuthorService;

import java.util.List;
import java.util.Map;

public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository=new AuthorRepoImpl();
    @Override
    public String saveAuthor(Author author) {
        authorRepository.saveAuthor(author);
        return "Successfully saved author with id = "+author.getId();
    }

    @Override
    public String updateAuthorById(Long id, Author author) {
        authorRepository.updateAuthorById(id,author);
        return "Author with id = "+id +" is successfully updated!";
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public String assignAuthorToPublisher(Long publisherId, Long authorId) {
        authorRepository.assignAuthorToPublisher(publisherId,authorId);
        return "Successfully assigned author to publisher!";
    }

    @Override
    public Map<String,List<Author>> getAuthorsByPublisherId(Long publisherId) {
        return authorRepository.getAuthorsByPublisherId(publisherId);
    }

    @Override
    public String deleteAuthorById(Long id) {
        authorRepository.deleteAuthorById(id);
        return "Author with id = "+id+" is successfully deleted! ";
    }
}
