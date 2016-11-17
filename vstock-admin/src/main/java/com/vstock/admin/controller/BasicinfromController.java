package com.vstock.admin.controller;

import com.vstock.admin.service.BasicinformationService;
import com.vstock.admin.service.BrandService;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.PictureUpload;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Brand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/basicinfrom")
public class BasicinfromController extends BaseController{

    Logger logger = Logger.getLogger(getClass());

    @Autowired
    BasicinformationService basicinformationService;

    @Autowired
    BrandService brandService;

//    @RequestMapping("updateData")
//    public void updateData(HttpServletRequest request){
//        String brand = request.getParameter("brand");
//        int num = Integer.parseInt(request.getParameter("num"));
//        Basicinformation b = new Basicinformation();
//        b.setBrand(brand);
//        List<Basicinformation> basicinformationList = basicinformationService.findAll(b);
//        for (Basicinformation ba : basicinformationList) {
//            ba.setBid(num);
//            basicinformationService.updatesicinState(ba);
//            num++;
//        }
//    }

    /**
     * 鞋库基本数据查询
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("shoeLibraryFile")
    public String shoeLibraryFile(Basicinformation record, HttpServletRequest request, ModelMap model){
        //初始化变量
        int i = 0;
        record.setState("1");
        String startCsaledates = "";
        String endCsaledates = "";
        String startEsaledates = "";
        String endEsaledates ="";
        String pageNow = request.getParameter("pageNow");
        String startCsaledate = request.getParameter("startCsaledate");
        if (startCsaledate != null && !"".equals(startCsaledate)){
            startCsaledate = DateUtils.dateTime(startCsaledate);
        }
        String endCsaledate = request.getParameter("endCsaledate");
        if (endCsaledate != null && !"".equals(endCsaledate)){
            endCsaledate = DateUtils.dateTime(endCsaledate);
        }
        String startEsaledate = request.getParameter("startEsaledate");
        String endEsaledate = request.getParameter("endEsaledate");
        String startCreatetime = request.getParameter("startCreatetime");
        String endCreatetime = request.getParameter("endCreatetime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String linkAddress = request.getRequestURI();
        //判断是否为空
        if(record.getId() != null && !"".equals(record.getId())){//不为空拼接跳转地址
            linkAddress = linkAddress +"?id="+record.getId();
            //获取是否存在？
            i = linkAddress.indexOf("?");
        }else{//为空
            //直接获取返回的ID参数
            String id = request.getParameter("id");
            //判断是否为空
            if(id != null && !"".equals(id)){
                record.setId(id);
                linkAddress = linkAddress +"?id="+id;
                i = linkAddress.indexOf("?");
            }
        }
        if(record.getBrand() != null && !"".equals(record.getBrand())){
            //判断是否有？
            if (i > 0) {//有
                linkAddress = linkAddress + "&brand=" + record.getBrand();
            }else {//没有
                linkAddress = linkAddress + "?brand=" + record.getBrand();
            }
            //获取？所在的位置
            i = linkAddress.indexOf("?");
        }else {
            String brand = request.getParameter("brandName");
            if(brand != null && !"".equals(brand)){
                record.setBrand(brand);
                if (i > 0) {
                    linkAddress = linkAddress +"$brand="+brand;
                }else {
                    linkAddress = linkAddress +"?brand="+brand;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if(record.getName() != null && !"".equals(record.getName() )){
            if (i > 0) {
                linkAddress = linkAddress + "&name=" + record.getName() ;
            }else {
                linkAddress = linkAddress + "?name=" + record.getName() ;
            }
            i = linkAddress.indexOf("?");
        }else {
            String name = request.getParameter("name");
            if(name != null && !"".equals(name)){
                record.setName(name);
                if (i > 0) {
                    linkAddress = linkAddress +"name="+name;
                }else {
                    linkAddress = linkAddress +"?name="+name;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if(record.getArtNo() != null && !"".equals(record.getArtNo())){
            if (i > 0) {
                linkAddress = linkAddress + "&artNo=" + record.getArtNo();
            }else {
                linkAddress = linkAddress + "?artNo=" + record.getArtNo();
            }
            i = linkAddress.indexOf("?");
        }else {
            String artNo = request.getParameter("artNo");
            if(artNo != null && !"".equals(artNo)){
                record.setArtNo(artNo);
                if (i > 0) {
                    linkAddress = linkAddress +"artNo="+artNo;
                }else {
                    linkAddress = linkAddress +"?artNo="+artNo;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if (record.getCofferprice() != null && !"".equals(record.getCofferprice())){
            if (i > 0){
                linkAddress = linkAddress + "&cofferprice=" + record.getCofferprice();
            }else {
                linkAddress = linkAddress + "?cofferprice=" + record.getCofferprice();
            }
            i = linkAddress.indexOf("?");
        }
        if (record.getCofferprices() != null && !"".equals(record.getCofferprices())){
            if (i > 0){
                linkAddress = linkAddress + "&cofferprices=" + record.getCofferprices();
            }else {
                linkAddress = linkAddress + "?cofferprices=" + record.getCofferprices();
            }
            i = linkAddress.indexOf("?");
        }
        if (record.getBscofferprice() != null && !"".equals(record.getBscofferprice())){
            if (i > 0){
                linkAddress = linkAddress + "&bscofferprice=" + record.getBscofferprice();
            }else {
                linkAddress = linkAddress + "?bscofferprice=" + record.getBscofferprice();
            }
            i = linkAddress.indexOf("?");
        }
        if (record.getChineselogo() != null && !"".equals(record.getChineselogo())){
            if (i > 0){
                linkAddress = linkAddress + "&chineselogo=" + record.getChineselogo();
            }else {
                linkAddress = linkAddress + "?chineselogo=" + record.getChineselogo();
            }
            i = linkAddress.indexOf("?");
        }
        if (record.getChineselogos() != null && !"".equals(record.getChineselogos())){
            if (i > 0){
                linkAddress = linkAddress + "&chineselogos=" + record.getChineselogos();
            }else {
                linkAddress = linkAddress + "?chineselogos=" + record.getChineselogos();
            }
            i = linkAddress.indexOf("?");
        }
        if (record.getCsaledate() != null && !"".equals(record.getCsaledate())){
            if (i > 0){
                linkAddress = linkAddress + "&csaledate=" + record.getCsaledate();
            }else {
                linkAddress = linkAddress + "?csaledate=" + record.getCsaledate();
            }
            i = linkAddress.indexOf("?");
        }
        if (startCsaledate != null){
            if (!startCsaledate.equals("")){
                startCsaledates = startCsaledate;
                startCsaledate = startCsaledate +" 00:00:00";
                if (i > 0){
                    linkAddress = linkAddress + "&startCsaledate=" + startCsaledates;
                }else {
                    linkAddress = linkAddress + "?startCsaledate=" + startCsaledates;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if (endCsaledate != null){
            if (!endCsaledate.equals("")){
                endCsaledates = endCsaledate;
                endCsaledate = endCsaledate +" 23:59:59";
                if (i > 0){
                    linkAddress = linkAddress + "&endCsaledates=" + endCsaledates;
                }else {
                    linkAddress = linkAddress + "?endCsaledates=" + endCsaledates;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if (startEsaledate != null){
            if (!startEsaledate.equals("")){
                startEsaledates = startEsaledate;
                if (i > 0){
                    linkAddress = linkAddress + "&startEsaledates=" + startEsaledates;
                }else {
                    linkAddress = linkAddress + "?startEsaledates=" + startEsaledates;
                }
                i = linkAddress.indexOf("?");
            }
        }
        if (endEsaledate != null){
            if (!endEsaledate.equals("")){
                endEsaledates = endEsaledate;
                if (i > 0){
                    linkAddress = linkAddress + "&endEsaledates=" + endEsaledates;
                }else {
                    linkAddress = linkAddress + "?endEsaledates=" + endEsaledates;
                }
            }
        }if (startCreatetime != null){
            if (!startCreatetime.equals("")){
                startCreatetime = startCreatetime +" 00:00:00";
            }
        }
        if (endCreatetime != null){
            if (!endCreatetime.equals("")){
                endCreatetime = endCreatetime +" 23:59:59";
            }
        }
        Long totalCount = basicinformationService.findLimitCount(record,startCsaledate,endCsaledate,startEsaledate,endEsaledate,startCreatetime,endCreatetime);
        Page page = new Page(totalCount.intValue(),pageNow);
        List<Basicinformation> basicinformationList = basicinformationService.findbasicAll(record,page,startCsaledate,endCsaledate,startEsaledate,endEsaledate,startCreatetime,endCreatetime);
        Brand brand = new Brand();
        List<Brand> brandList = brandService.findAllList(brand);
        model.addAttribute("brandList",brandList);
        model.addAttribute("basicinformationList",basicinformationList);
        model.addAttribute("basicinformation",record);
        model.addAttribute("startCsaledate",startCsaledates);
        model.addAttribute("endCsaledate",endCsaledates);
        model.addAttribute("startEsaledate",startEsaledates);
        model.addAttribute("endEsaledate",endEsaledates);
        model.addAttribute("page",page);
        model.addAttribute("linkAddress",linkAddress);
        return "admin/shoellibraryfile/list";
    }

    /**
     * 跳转新增、修改界面
     * @param request
     * @param model
     * @return
    */
    @RequestMapping("insertbasicinfromjsp")
    public String insertbasicinfromjsp(HttpServletRequest request,ModelMap model) {
        String id = request.getParameter("id");
        String pageNow = request.getParameter("pageNow");
        String brid = request.getParameter("brid");
        String startCsaledate = request.getParameter("startCsaledate");
        String endCsaledate = request.getParameter("endCsaledate");
        String startEsaledate = request.getParameter("startEsaledate");
        String endEsaledate = request.getParameter("endEsaledate");
        String brandName = request.getParameter("brand");
        String name = request.getParameter("name");
        String artNo = request.getParameter("artNo");
        String chineselogo = request.getParameter("chineselogo");
        String chineselogos = request.getParameter("chineselogos");
        String cofferprice = request.getParameter("cofferprice");
        String cofferprices = request.getParameter("cofferprices");
        String bscofferprice = request.getParameter("bscofferprice");
        String csaledates = request.getParameter("csaledate");
        Brand brand = new Brand();
        List<Basicinformation> list = new ArrayList<Basicinformation>();
        Basicinformation basicinformation = new Basicinformation();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
        List<Brand> brandList = brandService.findAllList(brand);
        if (id != null && !"".equals(id)){
            basicinformation.setId(id);
            list = basicinformationService.findAll(basicinformation);
            if (list.size() > 0){
                basicinformation = list.get(0);
                try {
                    Calendar now = Calendar.getInstance();
                    //年份
                    String year = String.valueOf( now.get(Calendar.YEAR));
                    int nowDate = Integer.parseInt(year.substring(2,4));
                    //对国外发售日期进行格式化处理
                    if(basicinformation.getCsaledate() != null){
                        String csaledate = dateFormater.format(dateFormater.parse(basicinformation.getCsaledate()));
                        int csdate = Integer.parseInt(csaledate.substring(0,2));
                        csaledate = csaledate.substring(2,csaledate.length());
                        if(csdate > nowDate){
                            csaledate = "19"+csaledate;
                        }else{
                            csaledate = "20"+csaledate;
                        }
                        basicinformation.setCsaledate(csaledate);
                    }
                    if(basicinformation.getEsaledate() != null){
                        String esaledate = dateFormater.format(dateFormater.parse(basicinformation.getEsaledate()));
                        int esdate = Integer.parseInt(esaledate.substring(2,4));
                        esaledate = esaledate.substring(2,esaledate.length());
                        if(esdate > nowDate){
                            esaledate = "19"+esaledate;
                        }else{
                            esaledate = "20"+esaledate;
                        }
                        basicinformation.setEsaledate(esaledate);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }
        }
        basicinformation.setState(request.getParameter("state"));
        model.addAttribute("basicinformation",basicinformation);
        model.addAttribute("brandList",brandList);
        model.addAttribute("pageNow",pageNow);
        model.addAttribute("brid",brid);
        model.addAttribute("startCsaledate",startCsaledate);
        model.addAttribute("endCsaledate",endCsaledate);
        model.addAttribute("startEsaledate",startEsaledate);
        model.addAttribute("endEsaledate",endEsaledate);
        model.addAttribute("brandName",brandName);
        model.addAttribute("name",name);
        model.addAttribute("artNo",artNo);
        model.addAttribute("chineselogo",chineselogo);
        model.addAttribute("chineselogos",chineselogos);
        model.addAttribute("cofferprice",cofferprice);
        model.addAttribute("cofferprices",cofferprices);
        model.addAttribute("bscofferprice",bscofferprice);
        model.addAttribute("csaledates",csaledates);
        return "admin/shoellibraryfile/saveOrUpdate";
    }
    /**
     * 新增和修改方法
     * @param request
     * @param basicinformation
     * @return
     */
    @RequestMapping("insertbasicinfrom")
    public String insertbasicinfrom(Basicinformation basicinformation,HttpServletRequest request) {
        String state = request.getParameter("state");
        MultipartRequest multipartRequest = (MultipartRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        MultipartFile multipartFiles = multipartRequest.getFile("files");
        if(multipartFile != null){
            if (multipartFile.getOriginalFilename() != null && !"".equals(multipartFile.getOriginalFilename())) {
                basicinformation.setImgUrl(PictureUpload.localUploadImg(multipartFile,multipartFile.getOriginalFilename(), 0));
            }
        }
        if(multipartFiles != null){
            if (multipartFiles.getOriginalFilename() != null && !"".equals(multipartFiles.getOriginalFilename())) {
                basicinformation.setSmallImgUrl(PictureUpload.localUploadImg(multipartFiles,multipartFile.getOriginalFilename(), 1));
            }
        }
        String pageNow = request.getParameter("pageNow");
        String brid = request.getParameter("brid");
        String startCsaledate = request.getParameter("bstartCsaledate");
        String endCsaledate = request.getParameter("bendCsaledate");
        String startEsaledate = request.getParameter("bstartEsaledate");
        String endEsaledate = request.getParameter("bendEsaledate");
        String brandName = request.getParameter("bbrandName");
        String name = request.getParameter("bname");
        String artNo = request.getParameter("bartNo");
        String chineselogo = request.getParameter("bchineselogo");
        String chineselogos = request.getParameter("bchineselogos");
        String cofferprice = request.getParameter("bcofferprice");
        String cofferprices = request.getParameter("bcofferprices");
        String bscofferprice = request.getParameter("bscofferprice");
        String csaledates = request.getParameter("bscsaledate");
        //拼接参数
        String Url = "admin/shoellibraryfiles";
        String NewUrl = "/basicinfrom/shoeLibraryFile";
        if (pageNow != null && !"".equals(pageNow)){
            Url = Url+"?pageNow="+pageNow;
            NewUrl = NewUrl+"?pageNow="+pageNow;
        }
        if (brid != null && !"".equals(brid)){
            Url = Url+"&id="+brid;
            NewUrl = NewUrl+"&id="+brid;
        }
        if (startCsaledate != null && !"".equals(startCsaledate)){
            Url = Url+"&startCsaledate="+startCsaledate;
            NewUrl = NewUrl+"&startCsaledate="+startCsaledate;
        }
        if (endCsaledate != null && !"".equals(endCsaledate)){
            Url = Url+"&endCsaledate="+endCsaledate;
            NewUrl = NewUrl+"&endCsaledate="+endCsaledate;
        }
        if (startEsaledate != null && !"".equals(startEsaledate)){
            Url = Url+"&startEsaledate="+startEsaledate;
            NewUrl = NewUrl+"&startEsaledate="+startEsaledate;
        }
        if (endEsaledate != null && !"".equals(endEsaledate)){
            Url = Url+"&endEsaledate="+endEsaledate;
            NewUrl = NewUrl+"&endEsaledate="+endEsaledate;
        }
        if (brandName != null && !"".equals(brandName)){
            Url = Url+"&brandName="+brandName;
            NewUrl = NewUrl+"&brandName="+brandName;
        }
        if (name != null && !"".equals(name)){
            Url = Url+"&name="+name;
            NewUrl = NewUrl+"&name="+name;
        }
        if (artNo != null && !"".equals(artNo)){
            Url = Url+"&artNo="+artNo;
            NewUrl = NewUrl+"&artNo="+artNo;
        }
        if (chineselogo != null && !"".equals(chineselogo)){
            Url = Url+"&chineselogo="+chineselogo;
            try {
                NewUrl = NewUrl+"&chineselogo="+ URLEncoder.encode(chineselogo,"UTF-8");
            }catch (Exception e){
                log.debug(e.getMessage());
            }
        }
        if (chineselogos != null && !"".equals(chineselogos)){
            Url = Url+"&chineselogos="+chineselogos;
            NewUrl = NewUrl+"&chineselogos="+chineselogos;
        }
        if (cofferprice != null && !"".equals(cofferprice)){
            Url = Url+"&cofferprice="+cofferprice;
            NewUrl = NewUrl+"&cofferprice="+cofferprice;
        }
        if (cofferprices != null && !"".equals(cofferprices)){
            Url = Url+"&cofferprices="+cofferprices;
            NewUrl = NewUrl+"&cofferprices="+cofferprices;
        }
        if (bscofferprice != null && !"".equals(bscofferprice)){
            Url = Url+"&bscofferprice="+bscofferprice;
            NewUrl = NewUrl+"&bscofferprice="+bscofferprice;
        }
        if (csaledates != null && !"".equals(csaledates)){
            Url = Url+"&csaledate="+csaledates;
            NewUrl = NewUrl+"&csaledate="+csaledates;
        }

        int resultCount = 0;
        if (state != null && !"".equals(state)){
            basicinformation.setState(state);
            resultCount = basicinformationService.updatebasicinfrom(basicinformation);
        }else {
            if (basicinformation.getId() == null || basicinformation.getId().equals("")) {
                basicinformation.setState("1");
                resultCount = basicinformationService.insertbasicinfrom(basicinformation);
            } else {
                resultCount = basicinformationService.updatebasicinfrom(basicinformation);
            }
        }
        if (resultCount > 0){
            return "redirect:"+NewUrl;
        }
        return Url;
    }

    /**
     * 获取鞋库所有鞋子名称
     * @param request
     * @return
     */
    @RequestMapping("getNames")
    @ResponseBody
    public List<Basicinformation> getNames(HttpServletRequest request){
        String artNo = request.getParameter("artNo");
        List<Basicinformation> nameLists = basicinformationService.findNames(artNo);
        return nameLists;
    }

    /**
     * 获取鞋库所有鞋子货号
     * @param request
     * @return
     */
    @RequestMapping("getGirard")
    @ResponseBody
    public List<Basicinformation> getGirard(HttpServletRequest request){
        String productName = request.getParameter("productName");
        List<Basicinformation> nameLists = basicinformationService.findGirard(productName);
        return nameLists;
    }
}
