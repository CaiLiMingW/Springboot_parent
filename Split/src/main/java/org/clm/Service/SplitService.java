package org.clm.Service;

import org.clm.Bean.Split;
import org.clm.Dao.SplitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author Ccc
 * @date 2018/11/17 0017 下午 4:32
 */
@Service
public class SplitService {
    @Autowired
    private SplitDao splitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Split> findAll(){
        return splitDao.findAll();
    }

    public Split findById(String id){
        return splitDao.findById(id).get();
    }

    public void add(Split split){
        split.set_id(idWorker.nextId()+"");
        split.setPublishtime(new Date());
        split.setVisits(0);//浏览量
        split.setShare(0);//分享数
        split.setThumbup(0);//点赞数
        split.setComment(0);//回复数
        split.setState("1");//状态
        if(split.getParentid()!=null && !"".equals(split.getParentid())){
            //如果存在上级ID,评论
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(split.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"split");
        }
        splitDao.save(split);
    }

    public void update(Split split){
        splitDao.save(split);
    }

    public void delete(String id){
        splitDao.deleteById(id);
    }

    public void updatethumbup(String id) {
        Split split = splitDao.findById(id).get();
        split.setThumbup(split.getThumbup()+1);
        splitDao.save(split);
    }

    public List<Split> getByKey(Split split){
       return splitDao.findAll(Example.of(split));
    }


    public Page<Split> getByKeyPage(Split split, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return splitDao.findAll(Example.of(split),pageRequest);
    }

    public Page<Split> getSplitByParentId(String id, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Split split = new Split();
        split.setParentid(id);
        return splitDao.findAll(Example.of(split),pageRequest);
    }
}
