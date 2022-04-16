package repository;

import model.Tag;
import model.TagStatus;

public interface TagRepository extends GenericRepository <Tag, Integer>
{

    TagStatus check(Integer id);
}
