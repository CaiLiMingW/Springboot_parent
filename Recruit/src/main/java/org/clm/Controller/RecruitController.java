package org.clm.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.clm.Bean.Recruit;
import org.clm.Service.IRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * <p>
 * 职位 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
//    @Autowired
//    private RecruitMapper recruitMapper;
    @Autowired
    private IRecruitService iRecruitService;

    // todo 全部修改
    @PostMapping
    public ServerResponse addRectuit(@RequestBody Recruit recruit){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.save(recruit));
    }

    @GetMapping
    public ServerResponse getAllRectuit(){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.list(null));
    }

    @GetMapping("/{id}")
    public ServerResponse getRectuitById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.getById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateRectuitById(@PathVariable("id")String id,@RequestBody Recruit recruit){
        recruit.setId(id);
        return ServerResponse.CreateBySuccessMessage(iRecruitService.updateById(recruit));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteRectuitById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.removeById(id));
    }

    @PostMapping("/search")
    public ServerResponse getRectuitByKey(@RequestBody Recruit recruit){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.selectByKey(null,null,recruit));
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse getRectuitByKey(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Recruit recruit){
        return ServerResponse.CreateBySuccessMessage(iRecruitService.selectByKey(page,size,recruit));
    }

    @GetMapping("/search/recommend")
    public ServerResponse getRectuitByrecommend(){
        //todo 推荐岗位
        Recruit recruit = new Recruit();
        recruit.setState("2");
        QueryWrapper queryWrapper = new QueryWrapper(recruit);

//        queryWrapper.orderByDesc("createtime");
//        queryWrapper.eq("state","2");
        return ServerResponse.CreateBySuccessMessage(iRecruitService.list(queryWrapper));
    }

    @GetMapping("/search/newlist")
    public ServerResponse getNewRectuit(){
        //todo 最新岗位
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("createtime");
        queryWrapper.eq("state","0");
        return ServerResponse.CreateBySuccessMessage(iRecruitService.list(queryWrapper));
    }

}
