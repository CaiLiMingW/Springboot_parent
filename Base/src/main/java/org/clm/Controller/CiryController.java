package org.clm.Controller;

import org.clm.Service.CityService;
import org.clm.bean.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/16 0016 下午 2:23
 */
@RestController
@RequestMapping("/city")
public class CiryController {
    @Autowired
    private CityService cityService;

    @PostMapping
    public ServerResponse addCity(@RequestBody City city){
        cityService.insertCity(city);
        return ServerResponse.CreateBySuccess();
    }

    @GetMapping
    public ServerResponse getAllCity(){
        return ServerResponse.CreateBySuccessMessage(cityService.selectAll());
    }

    @PutMapping("/{id}")
    public ServerResponse updateCity(@PathVariable("id")String id,@RequestBody City city){
        city.setId(id);
        cityService.updateCity(city);
        return ServerResponse.CreateBySuccessMessage();
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteCity(@PathVariable("id")String id){
        cityService.deleteCity(id);
        return ServerResponse.CreateBySuccessMessage();
    }
    @GetMapping("/{id}")
    public ServerResponse getCityById(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage( cityService.selectById(id));
    }

    @PostMapping("/search")
    public ServerResponse updateCity(@RequestBody City city){
        return cityService.selectByKey(city,null,null);
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse updateCity(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody City city){
        return cityService.selectByKey(city,page,size);
    }

}
