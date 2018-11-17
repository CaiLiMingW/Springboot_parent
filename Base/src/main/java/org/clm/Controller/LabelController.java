package org.clm.Controller;

import org.clm.Service.LabelService;
import org.clm.bean.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

import java.util.List;

/**
 * @author Ccc
 * @date 2018/11/15 0015 下午 8:40
 */
@RestController
@RequestMapping("/label")
@CrossOrigin//开启跨域访问的支持
public class LabelController {
    @Autowired
    private LabelService labelService;

    @PostMapping
    public ServerResponse addLabel(@RequestBody Label label){
        labelService.insertLable(label);
        return ServerResponse.CreateBySuccessMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ServerResponse<List<Label>> getAllAble(){
        return ServerResponse.CreateBySuccessMessage(labelService.selectAllLable());
    }

//    @GetMapping("/toplist")
//    public ServerResponse getRecommendList(){
//
//    }
//
//    @GetMapping("/list")
//    public ServerResponse getLabelList(){
//
//    }

    @GetMapping("/{labelId}")
    public ServerResponse<Label> getLabelList(@PathVariable("labelId")String id){
        return ServerResponse.CreateBySuccessMessage(labelService.selectByIdLable(id));
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public ServerResponse updateLabelById(@RequestBody Label label,@PathVariable("labelId")String id){
        label.setId(id);
        labelService.updateLable(label);
        return ServerResponse.CreateBySuccess();
    }

    @DeleteMapping("/{labelId}")
    public ServerResponse deleteLabelById(@PathVariable("labelId")String id){
        labelService.deleteByIdLable(id);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse searchByPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Label label){
        return labelService.selectByIdLable(page,size,label);
    }

    @PostMapping("/search/}")
    public ServerResponse searchByLabel(@RequestBody Label label){
        return labelService.selectByIdLable(null,null,label);
    }
    //todo 异常处理 toplist list

}
