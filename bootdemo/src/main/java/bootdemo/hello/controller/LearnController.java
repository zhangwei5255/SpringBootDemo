package bootdemo.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootdemo.hello.model.base.LearnResource;
import bootdemo.hello.service.LearnService;

@RestController
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    private LearnService learnService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String learn(){
        return "learn-resource";
    }

    @RequestMapping("/selectByPK")
    public LearnResource selectByPrimaryKey() {
        return learnService.selectByPrimaryKey(1000L);
    }
}