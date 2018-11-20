package org.clm.Controller;

import org.clm.Bean.Split;
import org.clm.Service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/17 0017 下午 4:39
 */
@RestController
@RequestMapping("/spit")
public class SplitController {
    @Autowired
    private SplitService splitService;

    @GetMapping
    public ServerResponse getAllSplit(){
        return ServerResponse.CreateBySuccessMessage(splitService.findAll());
    }

    @GetMapping("/{id}")
    public ServerResponse getSplitByid(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(splitService.findById(id));
    }

    @PostMapping
    public ServerResponse addSplitByid(@RequestBody Split split){
        splitService.add(split);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PutMapping("/{id}")
    public ServerResponse updateSplit(@PathVariable("id")String id,@RequestBody Split split){
        split.set_id(id);
        splitService.update(split);
        return ServerResponse.CreateBySuccessMessage();
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteSplit(@PathVariable("id")String id){
        splitService.delete(id);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PutMapping("/thumbup/{id}")
    public ServerResponse thumbup(@PathVariable("id")String id){
        splitService.updatethumbup(id);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PostMapping("/search")
    public ServerResponse getSplitByKey(@RequestBody Split split){
        return ServerResponse.CreateBySuccessMessage( splitService.getByKey(split));
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse getSplitByKeyPage(@RequestBody Split split,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        return ServerResponse.CreateBySuccessMessage( splitService.getByKeyPage(split,page,size));
    }

    @GetMapping("/comment/{id}/{page}/{size}")
    public ServerResponse getSplitByParentId(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage( splitService.getSplitByParentId(id,page,size));
    }
    //todo 根据parentId查找

}
