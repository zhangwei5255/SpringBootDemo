package bootdemo.hello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootdemo.hello.dao.base.LearnResourceDao;
import bootdemo.hello.model.base.LearnResource;
import bootdemo.hello.service.LearnService;

@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnResourceDao learnResourceDao;

	@Override
	public LearnResource selectByPrimaryKey(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return learnResourceDao.selectByPrimaryKey(id);
	}


}
