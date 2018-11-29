package org.clm.Controller;


import org.clm.Bean.Admin;
import org.clm.Service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;
import utils.RolesUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RolesUtil rolesUtil;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ServerResponse addAdmin(@RequestBody Admin admin){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return iAdminService.addAdmin(admin);
    }

    @GetMapping
    public ServerResponse getAllAdmin(){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return ServerResponse.CreateBySuccessMessage(iAdminService.list(null));
    }

    @GetMapping("/{id}")
    public ServerResponse getAdminById(@PathVariable("id")String id){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return ServerResponse.CreateBySuccessMessage(iAdminService.getById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateAdminById(@PathVariable("id")String id,@RequestBody Admin admin){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        admin.setId(id);
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        boolean res = iAdminService.updateById(admin);
        if (res){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorMessage();
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteAdminById(@PathVariable("id")String id){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        boolean res = iAdminService.removeById(id);
        if (res){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorMessage();
    }

    @PostMapping("/search/{page}/{size}")
    public ServerResponse getAllByPage(@RequestBody Admin admin,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        //todo 分页
        return ServerResponse.CreateBySuccessMessage();
    }

    @PostMapping("/login")
    public ServerResponse login(String loginname,String password){
       return iAdminService.login(loginname,password);
    }

}
