package com.examplm.camp.controller;

import com.examplm.camp.dto.BoardDTO;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;
import com.examplm.camp.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register......");

        if(bindingResult.hasErrors()){

            log.info("has error......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        long boardId = boardService.resister(boardDTO);

        redirectAttributes.addFlashAttribute("result", boardId);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long boardId, PageRequestDTO pageRequestDTO, Model model){

        BoardDTO boardDTO = boardService.readOne(boardId);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("board modify post......" + boardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors......");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("bnum", boardDTO.getBoardId());

            return "redirect:/board/modify?"+link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());  //bno -> bnum 수정

        return "redirect:/board/read";
    }
    @PostMapping("/remove")
    public String remove(Long boardId, RedirectAttributes redirectAttributes){

        log.info("remove post..." + boardId);

        boardService.remove(boardId);

        redirectAttributes.addFlashAttribute("result","removed");

        return "redirect:/board/list";

    }
}
