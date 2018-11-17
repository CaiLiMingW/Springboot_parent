package org.clm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.clm.Bean.Enterprise;
import org.clm.Dao.EnterpriseMapper;
import org.clm.Service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * <p>
 * 企业 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private IEnterpriseService iEnterpriseService;

    @RequestMapping
    public ServerResponse getAllEnterprise(){
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.list(null));
    }

    @PostMapping
    public ServerResponse addEnterprise(@RequestBody Enterprise enterprise){
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.save(enterprise));
    }

    @GetMapping("/{id}")
    public ServerResponse getEnterpriseById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.getById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateEnterpriseById(@PathVariable("id")String id,@RequestBody Enterprise enterprise){
        enterprise.setId(id);
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.updateById(enterprise));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteEnterpriseById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.removeById(id));
    }

    @PostMapping("/search")
    public ServerResponse searchByKeyWord(@RequestBody Enterprise enterprise){
        return iEnterpriseService.selectKeyWithPage(null,null,enterprise);
    }

    @GetMapping("/search/hotlist")
    public ServerResponse searchByHot(){
        Enterprise enterprise = new Enterprise();
        enterprise.setIshot("1");
        return ServerResponse.CreateBySuccessMessage(iEnterpriseService.list(new QueryWrapper<>(enterprise)));
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse searchByKeyWordPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Enterprise enterprise){
        return iEnterpriseService.selectKeyWithPage(page,size,enterprise);
    }





}
