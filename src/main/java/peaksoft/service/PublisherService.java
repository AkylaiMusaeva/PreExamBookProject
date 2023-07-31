package peaksoft.service;

import peaksoft.entity.Publisher;

import java.util.List;

public interface PublisherService {
    String savePublisher(Publisher publisher);

    Publisher getPublisherById(Long id);

    List<Publisher> getAllPublishers(String ascOrDesc);

    String updatePublisher(Long id,Publisher publisher);

    String deletePublisherByName(String name);
}
