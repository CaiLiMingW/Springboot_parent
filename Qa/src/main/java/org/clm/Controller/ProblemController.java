package org.clm.Controller;


import org.clm.Bean.Problem;
import org.clm.Client.LabelClient;
import org.clm.Service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;

/**
 * <p>
 * 问题 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private IProblemService iProblemService;
    @Autowired
    private LabelClient labelClient;

    @RequestMapping("/label/{labelId}")
    public ServerResponse getLabelById(@PathVariable("labelId")String labelId){
        ServerResponse serverResponse = labelClient.findById(labelId);
        return serverResponse;
    }

    @PostMapping
    public ServerResponse addProblem(@RequestBody Problem problem){
        return ServerResponse.CreateBySuccessMessage(iProblemService.save(problem));
    }

    @GetMapping
    public ServerResponse getProblemList(){
        return ServerResponse.CreateBySuccessMessage(iProblemService.list(null));
    }

    @GetMapping("/{id}")
    public ServerResponse getProblemByid(@PathVariable("id")String id){
        return ServerResponse.CreateBySuccessMessage(iProblemService.getById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateProblemByid(@PathVariable("id")String id,@RequestBody Problem problem){
        problem.setId(id);
        return ServerResponse.CreateBySuccessMessage(iProblemService.save(problem));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteProblemByid(@PathVariable("id")String id){

        return ServerResponse.CreateBySuccessMessage(iProblemService.removeById(id));
    }

    /**
     * 条件查询问题
     * @param problem
     * @return
     */
    @PostMapping("/search")
    public ServerResponse searchByKey(@RequestBody Problem problem){
        return iProblemService.selectByKeyPage(null,null,problem);
    }

    /**
     * 问题分页
     * @param page
     * @param size
     * @param problem
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public ServerResponse searchByKey(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Problem problem){
        return iProblemService.selectByKeyPage(page,size,problem);
    }

    /**
     * 最新问题列表
     * @param page
     * @param size
     * @param label
     * @return
     */
    @GetMapping("/search/newlist/{label}/{page}/{size}")
    public ServerResponse searchNewProblemListPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,
                                                   @PathVariable("label")String label ){
        return iProblemService.selectNewList(page,size,label);
    }

    /**
     * 热门问题列表
     * @param page
     * @param size
     * @param label
     * @return
     */
    @GetMapping("/search/hotlist/{label}/{page}/{size}")
    public ServerResponse searchHotProblemListPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,
                                                   @PathVariable("label")String label ){
        return iProblemService.selectHotList(page,size,label);
    }

    /**
     * 待解答问题列表
     * @param page
     * @param size
     * @param labelid
     * @return
     */
    @GetMapping("/search/waitlist/{labelid}/{page}/{size}")
    public ServerResponse searchWitProblemListPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,
                                                   @PathVariable("labelid")String labelid ){
        //todo 待解问题
        return iProblemService.selectWaitList(page,size,labelid);
    }

    @PostMapping("/all/{labelid}/{page}/{size}")
    public ServerResponse allProblemPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@PathVariable("labelid")String label){
        //todo
        return ServerResponse.CreateBySuccessMessage();
    }
}
