package com.wuzhiaite.javaweb.base.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wuzhiaite.javaweb.base.multidatabase.DataSourceConfigure;
import com.wuzhiaite.javaweb.base.multidatabase.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lpf
 * @description 代码生成器工具类
 * @since 20200424
 */
@Component
@Slf4j
public class CodeGeneratorUtil {
    /**
     * 获取数据库配置信息
     */
    private static DataSourceConfigure configure;
    /**
     * 自动生成器
     */
    private static AutoGenerator generator = null ;
    /**
     * 生成路径
     */
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/main/java";
    /**
     * 生成包路径
     */
    private static final String PACKAGE_URL = "com.wuzhiaite.javaweb.module";
    /**
     * 全局配置
     */
    private static GlobalConfig gc = new GlobalConfig();
    /**
     * 包配置
      */
    private static PackageConfig pc = new PackageConfig();
    /**
     * 模板配置
     */
    private static TemplateConfig templateConfig = new TemplateConfig();
    /**
     * 策略配置
     */
    private static StrategyConfig strategy = new StrategyConfig();
    /**
     * 自动注入数据源配置信息
     * @param dataSourceConfigure
     */
    @Autowired(required = true)
    public void setDataSourceConfigure( DataSourceConfigure dataSourceConfigure){
        CodeGeneratorUtil.configure = dataSourceConfigure;
    }
    static {
        generator = new AutoGenerator();
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        initParam();
    }
    //初始化配置
    private static void initParam() {
        //全局配置
        gc.setOutputDir(OUTPUT_DIR);
        gc.setOpen(false);
        gc.setSwagger2(true);
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setXml(null);
        // 策略配置
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id","update_time","create_time","update_user","create_user");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
    }
    /**
     * 列信息
     * @return
     * @return
     */
    public static List<TableInfo> tableInfoList(){
        return generator.getConfig().getTableInfoList();
    }

    public static void generatorCode(Map<String,Object> params){
        //设置数据源
        setDataSource();
        //作者
        String author = MapUtil.getString(params, "author");
        gc.setAuthor(author);
        generator.setGlobalConfig(gc);
        //包配置
        String pagckName = MapUtil.getString(params, "packageName");
        String url = StringUtils.isEmpty(pagckName) ? PACKAGE_URL : PACKAGE_URL+StringPool.DOT+pagckName ;
        pc.setParent(url);
        //模块名
        String module = MapUtil.getString(params, "module");
        pc.setModuleName(module);
        generator.setPackageInfo(pc);
        generator.setTemplate(templateConfig);
        setInjectionConfig();
        String tablenames = MapUtil.getString(params, "tablenames");
        strategy.setInclude(tablenames.split(","));
        generator.setStrategy(strategy);
        generator.execute();
    }

    private static void setInjectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                HashMap<String, Object> tempMap = new HashMap<>();
                tempMap.put("abc",this.getConfig().getGlobalConfig().getAuthor() +"--mp");
                this.setMap(tempMap);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String packageName = pc.getParent().replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
                String path = OUTPUT_DIR +File.separator + packageName
                        + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML ;
                log.info(path);
                return path;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
    }

    // 数据源配置
    private static void setDataSource() {
        String key = DynamicDataSourceContextHolder.getDataSource();
        key = StringUtil.isBlank(key) ? DataSourceConfigure.DEFAULT_DATASOURCE : key ;
        Map<String, Map<String, String>> dbsource = configure.getDbsource();
        Map<String, String> datasource = dbsource.get(key);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MapUtil.getString(datasource,"url"));
        dsc.setDriverName(MapUtil.getString(datasource,"driver-class-name"));
        dsc.setUsername(MapUtil.getString(datasource,"username"));
        dsc.setPassword(MapUtil.getString(datasource,"password"));
        generator.setDataSource(dsc) ;
    }


}
