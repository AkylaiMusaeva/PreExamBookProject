package peaksoft.service.serviceImpl;

import peaksoft.entity.Reader;
import peaksoft.repository.ReaderRepository;
import peaksoft.repository.repositoryImpl.ReaderRepoImpl;
import peaksoft.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    ReaderRepository readerRepository=new ReaderRepoImpl();
    @Override
    public String saveReader(Reader reader) {
        readerRepository.saveReader(reader);
        return "Reader with name = "+reader.getName()+" is successfully saved!";
    }

    @Override
    public String updateReaderById(Long id, Reader reader) {
        readerRepository.updateReaderById(id,reader);
        return "Reader with id = "+id+" is successfully updated!";
    }

    @Override
    public Reader getReaderByBookId(Long id) {
        return readerRepository.getReaderByBookId(id);
    }

    @Override
    public String deleteReaderById(Long id) {
        readerRepository.deleteReaderById(id);
        return "Reader with id = "+id +" is successfully deleted!";
    }

    @Override
    public String assignReaderToBook(Long readerId, Long bookId) {
        readerRepository.assignReaderToBook(readerId,bookId);
        return "Successfully assigned reader to book!";
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long authorId) {
        return readerRepository.getReadersByAuthorId(authorId);
    }
}
