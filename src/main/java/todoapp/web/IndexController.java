package todoapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import todoapp.AppConfig;

@Controller
public class IndexController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("assetHost", appConfig.getAssetHost());
        return "index";
    }
}
