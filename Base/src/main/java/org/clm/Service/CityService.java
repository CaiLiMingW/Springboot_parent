package org.clm.Service;

import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Dao.CityMapper;
import org.clm.bean.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ServerResponse;

import java.nio.channels.Pipe;
import java.util.List;

/**
 * @author Ccc
 * @date 2018/11/16 0016 下午 2:24
 */
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    public void insertCity(City city) {
        cityMapper.insertSelective(city);
    }

    public List<City> selectAll() {
        return cityMapper.selectAll();
    }

    public void updateCity(City city) {
        cityMapper.updateByPrimaryKeySelective(city);
    }

    public void deleteCity(String id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    public City selectById(String id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public ServerResponse  selectByKey(City city, Integer page, Integer size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%").append(city.getName()).append("%");
        city.setName(String.valueOf(stringBuilder));

        if (page!=null&&size!=null){
            PageHelper.startPage(page,size);
            List<City> cities = cityMapper.selectByKeyword(city);
            PageInfo pageInfo = new PageInfo(cities);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }

        return ServerResponse.CreateBySuccessMessage(cityMapper.selectByKeyword(city));
    }
}
