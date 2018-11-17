package org.clm.Controller;

import org.clm.Bean.Article;
import org.clm.Dao.ArticleMapper;
import org.clm.Service.IArticleService;
import org.clm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;
import result.StatusCode;

/**
 * <p>
 * 企业 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private IArticleService iArticleService;

    @GetMapping
    public ServerResponse getAllArticle(){
        return ServerResponse.CreateBySuccessMessage(iArticleService.list(null));
    }

    @PostMapping
    public ServerResponse addArticle(@RequestBody Article Article){
        if (iArticleService.save(Article)){
            return ServerResponse.CreateBySuccessMessage();
        }else{
            return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode());
        }
    }

    @GetMapping("/{id}")
    public ServerResponse getArticleById(@PathVariable("id")String id){
        Article article = redisUtil.get("article", id);
        if (article==null){
            article = iArticleService.getById(id);
            redisUtil.set("article", id,article);
        }
        return ServerResponse.CreateBySuccessMessage(article);
    }

    @PutMapping("/{id}")
    public ServerResponse updateArticleById(@PathVariable("id")String id,@RequestBody Article Article){
        Article.setId(id);
        return ServerResponse.CreateBySuccessMessage(iArticleService.updateById(Article));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteArticleById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iArticleService.removeById(id));
    }

    @PostMapping("/search")
    public ServerResponse searchByKeyWord(@RequestBody Article Article){
        return iArticleService.selectKeyWithPage(null,null,Article);
    }

    @PutMapping("/thumbup/{id}")
    public ServerResponse addthumbup(@PathVariable("id")String id){
        //todo 点赞
        articleMapper.thumbupArticle(id);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse searchByKeyWordPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Article Article){
        return iArticleService.selectKeyWithPage(page,size,Article);
    }


    @PostMapping("/channel/{id}/{page}/{size}")
    public ServerResponse searchByChannelIdPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@PathVariable("id")String id){
        Article article = new Article();
        article.setChannelid(id);
        return iArticleService.selectKeyWithPage(page,size,article);
    }

    @PostMapping("/column/{id}/{page}/{size}")
    public ServerResponse searchByColumnPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@PathVariable("id")String id){
        Article article = new Article();
        article.setColumnid(id);
        return iArticleService.selectKeyWithPage(page,size,article);
    }

    @PutMapping("/examine/{id}")
    public ServerResponse examineArticle(@PathVariable("id")String id){
        //todo 过审
        iArticleService.examineArticle(id);
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping("/top")
    public ServerResponse getTop(){
        return ServerResponse.CreateBySuccessMessage(iArticleService.selectTop());
    }




}
