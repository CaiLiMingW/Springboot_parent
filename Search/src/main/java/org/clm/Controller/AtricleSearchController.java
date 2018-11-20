package org.clm.Controller;

import org.clm.Bean.Atricle;
import org.clm.Service.IArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/19 0019 下午 1:38
 */
@RestController
@RequestMapping("/article")
public class AtricleSearchController {
    @Autowired
    private IArticleSearchService iArticleSearchService;

    @PostMapping
    public ServerResponse saveAtricle(@RequestBody Atricle atricle){
        iArticleSearchService.addArticleSeatch(atricle);
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping("/search/{keyword}/{page}/{size}")
    public ServerResponse findAtricleByTitleOrContent(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@PathVariable("keyword")String keyword){
        Page<Atricle> atricles = iArticleSearchService.findByTitleOrContent(keyword, page, size);
        atricles.getTotalElements();
        return ServerResponse.CreateBySuccessMessage(atricles.getContent());
    }

    @GetMapping
    public ServerResponse findAll(){
        return ServerResponse.CreateBySuccessMessage(iArticleSearchService.fingAll());
    }

    @GetMapping("/{id}")
    public ServerResponse findbyId(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iArticleSearchService.findById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateById(@PathVariable("id")String id,@RequestBody Atricle atricle){
        atricle.setId(id);
        iArticleSearchService.updateArticle(atricle);
        return ServerResponse.CreateBySuccessMessage();
    }

}
