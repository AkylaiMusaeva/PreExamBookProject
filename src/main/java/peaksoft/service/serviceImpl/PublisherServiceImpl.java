package peaksoft.service.serviceImpl;

import peaksoft.entity.Publisher;
import peaksoft.repository.PublisherRepository;
import peaksoft.repository.repositoryImpl.PublisherRepoImpl;
import peaksoft.service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    PublisherRepository publisherRepository=new PublisherRepoImpl();
    @Override
    public String savePublisher(Publisher publisher) {
        System.out.println(publisherRepository.savePublisher(publisher));
        return "Successfully saved publisher with name "+publisher.getName();
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.getPublisherById(id);
    }

    @Override
    public List<Publisher> getAllPublishers(String ascOrDesc) {
        return publisherRepository.getAllPublishers(ascOrDesc);
    }

    @Override
    public String updatePublisher(Long id,Publisher publisher) {
        publisherRepository.updatePublisher(id,publisher);
        return "Publisher with id = "+id+" is successfully updated!";
    }

    @Override
    public String deletePublisherByName(String name) {
        publisherRepository.deletePublisherByName(name);
        return name+" publisher is successfully deleted!";
    }
}
