package ${classpath}.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
* @Title:
* @author
* @since   JDK1.8
* @history
* @description  处罚台帐信息配置
*/
@Controller
@RequestMapping("//")
public class Controller{
//业务service接口
@Autowired
private ICftzPzService cftzPzService;

        /**
        * 列表页面跳转
        * @param model
        * @return
        */
        @RequestMapping("/cftzpzlist")
        public String cftzpzList(ModelMap model){
            this.setToken();
            return "/xzcf/config/cftzpzlist";
        }

        /**
        * 新增页跳转
        * @param model
        * @return
        */
        @RequestMapping("/cftzpzadd")
        public String cftzpzAdd(ModelMap model)  {
            return "/xzcf/config/cftzpzedit";
        }

        /**
        * 更新页面跳转
        * @param XH
        * @param model
        * @return
        * @throws Exception
        */
        @RequestMapping("/cftzpzedit/{XH}")
        public String cftzpzEdit(@PathVariable("XH")String XH,ModelMap model)  throws CftzPzException  {
            Map<String, Object> t = new HashMap<String, Object>();
            t.put("XH", XH);
            model.addAttribute("cftzPz", cftzPzService.get(t));
            return "/xzcf/config/cftzpzedit";
        }

        /**
        * 查询处罚台帐配置业务集合信息
        * @param param 请求参数
        * @return
        */
        @RequestMapping(value = "/findcftzpzs", method = { RequestMethod.POST })
        @ResponseBody
        public PageInfo<Map<String,Object>> findcftzpzs(@RequestBody Map<String,Object> param)  {
        //排序值
            String orderByStr = "";
            @SuppressWarnings("unchecked")
            List<Map<String,Object>> orderList = (List<Map<String, Object>>) param.get("order");
            if(CollectionUtils.isNotEmpty(orderList)){
                orderByStr = orderList.get(0).get("field")+" "+ orderList.get(0).get("direction");
            }else{
             orderByStr = "CJSJ DESC";
            }
            Integer pageNum = (Integer)param.get("pageNum") == null ? 1 : (Integer)param.get("pageNum");
            Integer pageSize = (Integer)param.get("pageSize") == null ? 10 : (Integer)param.get("pageSize");
            PageInfo<Map<String,Object>> list= cftzPzService.findListByPage(pageNum, pageSize, param,orderByStr);
            return list;
        }

        /**
        * 查询处罚台帐配置业务集合信息
        * @param param 请求参数
        * @return
        */
        @RequestMapping(value = "/findallcftzpzs", method = { RequestMethod.POST })
        @ResponseBody
        public AjaxJson findAllcftzpzs(@RequestBody Map<String,Object> param)  {
            //排序值
            AjaxJson j = new AjaxJson();
            try{
                List<Map<String,Object>> allCftzpzs = cftzPzService.findTableByCdt("T_XZCF_TZPZB", "LCDYBH='"+param.get("LCDYBH")+"' order by PXZ");
                for(Map<String,Object> data : allCftzpzs){
                    data.put("open", true);
                }
                j.setResult(allCftzpzs);
            }catch(Exception e){
                LoggerUtil.error(this.getClass(), e.toString());
                j.setCode(-1);
            }
            return j;
        }



        /**
        * 查询处罚台帐配置某个业务信息
        * @param    params 业务集合
        * @return
        * @throws   CftzPzException
        */
        @RequestMapping(value = "/getcftzpz", method = { RequestMethod.POST })
        @ResponseBody
        public AjaxJson getCftzPz(@RequestBody Map<String, Object> param)  {
            AjaxJson j = new AjaxJson();
            try {
                 j.setResult(cftzPzService.get(param));
            } catch (Exception ex) {
                j.setCode(-1);
                ex.printStackTrace();
                LoggerUtil.error(this.getClass(), "获取数据异常"+ex, ex);
            }
            return j;
        }

        /**
        * 保存处罚台帐配置业务信息
        * @param  params 业务Map集合
        * @return
        * @
        */
        @RequestMapping(value = "/savecftzpz", method = { RequestMethod.POST })
        @ResponseBody
        public AjaxJson saveCftzPz(@RequestBody Map<String, Object> params)  {
            AjaxJson j = new AjaxJson();
            try{
                DepartmentVO dept = UserHelper.getUserDept(this.getCurrUser().getYhid());
                params.put("ORGID", dept.getSjqx());//获取系统账户所在部门数据权限
                //响应数据
                int count = 0 ;
                Map<String,Object> resData = new HashMap<String,Object>();
                cftzPzService.saveOrUpdate(params, "XH");
                resData.put("XH", params.get("XH"));
                resData.put("count", count);
                j.setResult(resData);
            }catch(Exception ex){
                j.setCode(-1);
                LoggerUtil.error(this.getClass(), "保存数据异常", ex);
            }
            return j;
        }

        /**
        * 删除处罚台帐配置业务信息
        * @param  params 业务集合
        * @return  返回操作标识符
        * @
        */
        @RequestMapping(value = "/deletecftzpz", method = { RequestMethod.POST })
        @ResponseBody
        public AjaxJson deleteCftzPz(@RequestBody Map<String,Object> param) {
            AjaxJson j = new AjaxJson();
            try{
                String xhs = (String)param.get("xhs");
                String[] pksArr = xhs.split(",");
                int count = 0;
                for(int i=0;i<pksArr.length;i++){
                    Map<String,Object> delObj = new HashMap<String,Object>();
                    delObj.put("XH", pksArr[i]);
                    count += cftzPzService.delete(delObj);
                 }
                j.setResult(count);
            }catch(Exception ex){
                j.setCode(-1);
                ex.printStackTrace();
                LoggerUtil.error(this.getClass(), "保存数据异常", ex);
            }
            return j;
        }

        /**
        * 获取行政处罚附件列表 下拉选择控件数据接口
        * @return
        */
        @RequestMapping(value = "/findfjlist")
        @ResponseBody
        public List<Map<String,Object>> findFjList(){
            List<Map<String,Object>> list = cftzPzService.findTableByCdt("t_platform_wj_wjlx", "LXDM LIKE 'XZCF_%'");
            List<Map<String,Object>> datas = new ArrayList<>();
            for(Map<String,Object> m : list){
                Map<String,Object> mm = new HashMap<String,Object>();
                mm.put("label", m.get("LXMC"));
                mm.put("value", m.get("LXDM"));
                datas.add(mm);
            }
            return datas;
        }

        /**
        * 获取固态表单配置打印的相关信息
        * @param params
        * @return
        */
        @RequestMapping(value = "/getprintconfig")
        @ResponseBody
        public List<Map<String, Object>> getPrintConfig() {
            List<Map<String,Object>> list = cftzPzService.getPrintConfig();
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : list) {
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("label", map.get("DZMC"));
                m.put("value", map.get("DYLX"));
                listMap.add(m);
            }
            return listMap;
        }

}























