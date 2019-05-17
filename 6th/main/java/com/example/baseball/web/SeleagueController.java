package com.example.baseball.web;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.baseball.repository.Seleague;
import com.example.baseball.repository.SeleagueRepository;

@Controller
public class SeleagueController {
  final static Logger logger = LoggerFactory.getLogger(SeleagueController.class);

  @Autowired
  SeleagueRepository seleagueRepository;

  @Autowired
  MessageSource msg;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

  @RequestMapping(value = "/seleague", method = RequestMethod.GET)
  public String index(Model model) {
    logger.debug("Seleague + index");
    return "index";
  }

//  @RequestMapping(value = "/seleague/show", method = RequestMethod.GET)
//  public ModelAndView show(Model model) throws Exception {
//    logger.debug("Seleague + show");
//    ModelAndView mv = new ModelAndView();
////    Main main = new Main();
////    main.main();
//    List<Seleague> list = seleagueRepository.findTeamsByRank();
//    mv.addObject("list", list);
//    mv.setViewName("seleague/show");
//    return mv;
//  }

  @RequestMapping(value = "/seleague/show", method = RequestMethod.GET)
  public ModelAndView show(Model model) throws Exception {
    logger.debug("Seleague + show");
    ModelAndView mv = new ModelAndView();
    Main main = new Main();
    main.main();
    List<Seleague> list = seleagueRepository.findTeamsByRank();
    mv.addObject("list", list);
    mv.setViewName("show");
    return mv;
  }


  /**
   * for debug.
   */
  private void modelDump(Model model, String m) {
    logger.debug(" ");
    logger.debug("Model:{}", m);
    Map<String, Object> mm = model.asMap();
    for (Entry<String, Object> entry : mm.entrySet()) {
      logger.debug("key:{}", entry.getKey());
    }
  }

}