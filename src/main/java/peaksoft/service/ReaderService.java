package peaksoft.service;

import peaksoft.entity.Reader;

import java.util.List;

public interface ReaderService {
    String saveReader(Reader reader);

    String updateReaderById(Long id, Reader reader);

    Reader getReaderByBookId(Long id);

    String deleteReaderById(Long id);

    String assignReaderToBook(Long readerId, Long bookId);

    List<Reader> getReadersByAuthorId(Long authorId);
}

