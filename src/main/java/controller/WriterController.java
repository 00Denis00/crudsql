package controller;

import model.Writer;
import repository.WriterRepository;

import java.util.List;

public class WriterController
{
    WriterRepository writerRepository = new WriterRepository(){};
    public void deleteById(Integer id)
    {
        writerRepository.deleteById(id);
    }
    public Writer getById(Integer id)
    {
        Writer writer = writerRepository.getById(id);
        return writer;
    }
    public List<Writer> getAll()
    {
        List<Writer> writers = writerRepository.getAll();
        return writers;
    }
    public void save(Writer writer)
    {
        writerRepository.save(writer);
    }
}
