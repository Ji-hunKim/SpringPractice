package first.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")  // 웹 어플리케이션에서 /hello라고 들어오면 HelloController 메서드 호출
    public String hello(Model model) {   // Model View Controller (MVC)
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // 위에서는 string을 직접 받음
    // 여기서는 @RequestParam을 이용,
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name, Model model) {
        return "hello" + name;
    }

    @GetMapping("hello-class")
    @ResponseBody
    public Hello helloAip(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


