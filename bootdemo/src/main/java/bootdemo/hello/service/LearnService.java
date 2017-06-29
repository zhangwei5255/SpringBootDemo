package bootdemo.hello.service;

import bootdemo.hello.model.base.LearnResource;

public interface LearnService {
	LearnResource selectByPrimaryKey(Long id);
}
