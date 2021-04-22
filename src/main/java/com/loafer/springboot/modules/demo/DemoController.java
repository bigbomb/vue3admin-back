package com.loafer.springboot.modules.demo;

import com.loafer.springboot.common.utils.HttpUtils;
import com.loafer.springboot.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("get1")
    public R get1() {

        return R.success(HttpUtils.doGet("http://localhost:8888/loafer/demo/get2"));
    }

    @GetMapping("get2")
    public R get2() {
        return R.success("get2");
    }

}
