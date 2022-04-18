package controller;

import model.Writer;
import repository.Jdbc.JdbcWriterRepositoryImpl;
import repository.WriterRepository;

import java.util.List;

public class WriterController
{
    WriterRepository writerRepository = new JdbcWriterRepositoryImpl(){};

    public void deleteById(Integer id)
    {
        writerRepository.deleteById(id);
    }
    public Writer getById(Integer id)
    {
        return writerRepository.getById(id);
    }
    public List<Writer> getAll()
    {
        return writerRepository.getAll();
    }
    public Writer update(Writer writer)
    {
        return writerRepository.update(writer);
    }
    public Writer save(Writer writer)
    {
        return writerRepository.save(writer);
    }
}
