package todoapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import todoapp.config.AppConfig;

@Controller
public class IndexController {

    @SuppressWarnings("UnusedDeclaration")
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("assetHost", appConfig.getAssetHost());
        return "index";
    }
}
